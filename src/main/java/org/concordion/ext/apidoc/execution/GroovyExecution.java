package org.concordion.ext.apidoc.execution;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.concordion.api.Evaluator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GroovyExecution implements ScriptExecution{

    @Override
    public ScriptResult run(String script, Evaluator evaluator) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        final PrintStream oldOut = System.out;
        final PrintStream oldErr = System.err;

        Object returnValue;
        try (
                final PrintStream newOut = new PrintStream(output)
        ) {
            Binding binding = new Binding();

            // Bind System.out|err to "output"
            System.setOut(newOut);
            System.setErr(newOut);

            GroovyShell shell = new GroovyShell(binding);
            returnValue = shell.evaluate(script);
        } finally {
            System.setOut(oldOut);
            System.setErr(oldErr);
        }

        String outputString = output.toString();

        return ScriptResult.forSupportedLanguage(returnValue, outputString);
    }
}
