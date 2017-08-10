package name.neuhalfen.concordion.extension.executablespec;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.concordion.api.*;
import org.concordion.internal.FailFastException;
import org.concordion.internal.ImplementationStatusChecker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class RunGroovyCommand extends AbstractCommand {

    public List<CommandCall> getExamples(CommandCall command) {
        return Arrays.asList(command);
    }

    @Override
    public void execute(CommandCall node, Evaluator evaluator, ResultRecorder resultRecorder) {
        String exampleName = getExampleName(node);
        SCRIPTING_LANGUAGE language = SCRIPTING_LANGUAGE.fromString(node.getExpression());

        resultRecorder.setSpecificationDescription(exampleName);
        String script = (String) evaluator.getVariable("#TEXT");


        ScriptResult scriptResult = null;
        try {
            switch (language) {
                case GROOVY:
                    scriptResult = runGroovy(script, evaluator);
                    break;
                default:
                    scriptResult = ScriptResult.forUnsupportedLanguage();
            }
        } catch (FailFastException f) {
            // Ignore - it'll be re-thrown later by the implementation status checker if necessary.
        }
        node.getElement().addAttribute("id", exampleName);

        publishResultToTestRun(node, evaluator, scriptResult);

        resultRecorder.setImplementationStatus(scriptResult.implementationStatus);

        emitCallResult(node, scriptResult, scriptResult.implementationStatus);
    }

    private void publishResultToTestRun(CommandCall node, Evaluator evaluator, ScriptResult scriptResult) {
        final String setResultTo = node.getParameter(ExecutableSpecExtension.ATTRIBUTE_RESULT_VARIABLE);
        if (setResultTo != null) {
            evaluator.setVariable(setResultTo, scriptResult.returnValue);
        }
    }

    /*
     * Insert the result of the call into the generated document
     */
    private void emitCallResult(CommandCall node, ScriptResult scriptResult, ImplementationStatus implementationStatus) {

        // let's be really nice and add the implementation status text into the element itself.
        ImplementationStatusChecker checker = ImplementationStatusChecker.implementationStatusCheckerFor(implementationStatus);

        final String note = checker.printNoteToString();
        final Element outputElement = node.getElement();

        if (note != null) {
            Element fixtureNode = new Element("p");
            fixtureNode.appendText(note);

            outputElement.appendChild(fixtureNode);
        }

        if (scriptResult.output != null && scriptResult.output.length() > 0) {

            Element stdOut = new Element("code");
            stdOut.appendText(scriptResult.output);

            stdOut.addStyleClass("shell");

            // The structure is (normally):
            //     pre code {groovy}, {stdout}
            outputElement.appendSister(stdOut);
        }
    }

    private ScriptResult runGroovy(String groovy, Evaluator evaluator) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        final PrintStream oldOut = System.out;
        final PrintStream oldErr = System.err;

        Object returnValue;
        try (
                final PrintStream newOut = new PrintStream(output);
        ) {
            Binding binding = new Binding();

            // Bind System.out|err to "output"
            System.setOut(newOut);
            System.setErr(newOut);

            GroovyShell shell = new GroovyShell(binding);
            returnValue = shell.evaluate(groovy);
        } finally {
            System.setOut(oldOut);
            System.setErr(oldErr);
        }

        String outputString = output.toString();

        return ScriptResult.forSupportedLanguage(returnValue, outputString);
    }


    private String getExampleName(CommandCall node) {
        String exampleName = node.getExpression();

        // use the contents of the example if there is no name.
        if ("".equals(exampleName) && node.getElement().isNamed("td")) {
            exampleName = node.getElement().getText();
        }
        return exampleName;
    }

}