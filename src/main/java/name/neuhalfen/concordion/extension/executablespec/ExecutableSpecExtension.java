package name.neuhalfen.concordion.extension.executablespec;


import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class ExecutableSpecExtension implements ConcordionExtension {
    public static final String NAMESPACE = "http://neuhalfen.name/concordion/extensions/run";

    public static final String ATTRIBUTE_LANGUAGE = "lang";
    public static final String ATTRIBUTE_RESULT_VARIABLE = "result";

    @Override
    public void addTo(ConcordionExtender extender) {
        extender.withCommand(NAMESPACE, ATTRIBUTE_LANGUAGE, new RunGroovyCommand());
    }
}
