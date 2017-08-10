package name.neuhalfen.concordion.extension.executablespec;

import org.concordion.api.ImplementationStatus;

final class ScriptResult {
    /**
     * Whatever the script returned (e.g. via 'return')
     */
    final Object returnValue;

    /**
     * Stdout && Stdin
     */
    final String output;

    /**
     * The implementation status:
     *  - ExpectedToPass for supported languages
     *  - Unimplemented for unsupported languages
     */
    final ImplementationStatus implementationStatus;


    private ScriptResult(Object returnValue, String output, ImplementationStatus implementationStatus) {
        this.returnValue = returnValue;
        this.output = output;
        this.implementationStatus = implementationStatus;
    }

    public static ScriptResult forUnsupportedLanguage() {
        return new ScriptResult(null, null, ImplementationStatus.UNIMPLEMENTED);
    }

    public static ScriptResult forSupportedLanguage(Object returnValue, String output) {
        return new ScriptResult(returnValue, output, ImplementationStatus.EXPECTED_TO_PASS);
    }
}
