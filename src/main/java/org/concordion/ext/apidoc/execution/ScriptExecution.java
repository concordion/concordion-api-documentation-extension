package org.concordion.ext.apidoc.execution;

import org.concordion.api.Evaluator;

public interface ScriptExecution {
     ScriptResult run(String script, Evaluator evaluator);
}
