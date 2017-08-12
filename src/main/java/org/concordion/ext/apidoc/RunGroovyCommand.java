package org.concordion.ext.apidoc;

import org.concordion.ext.apidoc.execution.ScriptResult;
import org.concordion.api.*;
import org.concordion.internal.ImplementationStatusChecker;

import java.util.Arrays;
import java.util.List;

final class RunGroovyCommand extends AbstractCommand {

    public List<CommandCall> getExamples(CommandCall command) {
        return Arrays.asList(command);
    }

    @Override
    public void execute(CommandCall node, Evaluator evaluator, ResultRecorder resultRecorder) {
        String exampleName = getExampleName(node);
        SCRIPTING_LANGUAGE language = SCRIPTING_LANGUAGE.fromString(node.getExpression());

        resultRecorder.setSpecificationDescription(exampleName);
        String script = (String) evaluator.getVariable("#TEXT");

        final boolean isRunScript = isRunScript(node);


        final ScriptResult scriptResult;
        if (isRunScript) {
                 scriptResult = language.scriptRunner().run(script,evaluator);
        }else{
            scriptResult = ScriptResult.forDisabledRunning();
        }
        node.getElement().addAttribute("id", exampleName);

        publishResultToTestRun(node, evaluator, scriptResult);

        resultRecorder.setImplementationStatus(scriptResult.getImplementationStatus());

        emitCallResult(node, scriptResult, scriptResult.getImplementationStatus());
    }

    private boolean isRunScript(CommandCall node) {
        final String runParameter = node.getParameter(ExecutableSpecExtension.ATTRIBUTE_RUN);
        return runParameter == null || "true".equalsIgnoreCase(runParameter) || "yes".equalsIgnoreCase(runParameter);
    }

    private void publishResultToTestRun(CommandCall node, Evaluator evaluator, ScriptResult scriptResult) {
        final String setResultTo = node.getParameter(ExecutableSpecExtension.ATTRIBUTE_RESULT_VARIABLE);
        if (setResultTo != null) {
            evaluator.setVariable(setResultTo, scriptResult.getReturnValue());
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

        if (scriptResult.getOutput().length() > 0) {

            Element stdOut = new Element("code");
            stdOut.appendText(scriptResult.getOutput());

            stdOut.addStyleClass("shell");

            // The structure is (normally):
            //     pre code {groovy}, {stdout}
            outputElement.appendSister(stdOut);
        }
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