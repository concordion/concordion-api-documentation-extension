package name.neuhalfen.concordion.extension.executablespec.execution;

import org.concordion.api.Evaluator;

public interface ScriptExecution {
     ScriptResult run(String script, Evaluator evaluator);
}
