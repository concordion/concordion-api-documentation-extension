package name.neuhalfen.concordion.extension.executablespec;


import name.neuhalfen.concordion.extension.executablespec.parsing.CodeBlockDocumentParsingListener;
import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public final class ExecutableSpecExtension implements ConcordionExtension {
    public static final String NAMESPACE = "http://neuhalfen.name/concordion/extensions/run";

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
