package name.neuhalfen.concordion.extension.executablespec.execution;

import org.concordion.api.ImplementationStatus;

public final class ScriptResult {
    public Object getReturnValue() {
        return returnValue;
    }

    public String getOutput() {
        return output;
    }

    public ImplementationStatus getImplementationStatus() {
        return implementationStatus;
    }

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
     * - ExpectedToPass for supported languages
     * - Unimplemented for unsupported languages
     */
    final ImplementationStatus implementationStatus;


    private ScriptResult(Object returnValue, String output, ImplementationStatus implementationStatus) {
        this.returnValue = returnValue;
        this.output = output != null ? output : "";
        this.implementationStatus = implementationStatus;
    }

    public static ScriptResult forUnsupportedLanguage() {
        return new ScriptResult(null, null, ImplementationStatus.UNIMPLEMENTED);
    }

    public static ScriptResult forSupportedLanguage(Object returnValue, String output) {
        return new ScriptResult(returnValue, output, ImplementationStatus.EXPECTED_TO_PASS);
    }

    public static ScriptResult forDisabledRunning() {
        return new ScriptResult(null, null, ImplementationStatus.UNIMPLEMENTED);
    }
}
