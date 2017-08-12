package org.concordion.ext.apidoc;


import org.concordion.ext.apidoc.parsing.CodeBlockDocumentParsingListener;
import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public final class ExecutableSpecExtension implements ConcordionExtension {
    public static final String NAMESPACE = "urn:concordion-extensions:2010";

    public static final String ATTRIBUTE_LANGUAGE = "lang";
    public static final String ATTRIBUTE_RUN = "run";
    public static final String ATTRIBUTE_RESULT_VARIABLE = "result";

    @Override
    public void addTo(ConcordionExtender extender) {
        extender.withCommand(NAMESPACE, ATTRIBUTE_LANGUAGE, new RunGroovyCommand())
                .withDocumentParsingListener(new CodeBlockDocumentParsingListener())
                .withLinkedCSS("/highlightjs.org/styles/solarized-dark.css", new Resource("/highlightjs.org/solarized-dark.css"))
                .withLinkedJavaScript("/highlightjs.org/highlight.pack.js", new Resource("/highlightjs.org/highlight.pack.js"))
                .withLinkedJavaScript("/highlightjs.org/initHighlighting.js", new Resource("/highlightjs.org/initHighlighting.js"));
    }

}
