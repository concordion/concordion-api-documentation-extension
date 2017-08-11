package name.neuhalfen.concordion.extension.executablespec;

import name.neuhalfen.concordion.extension.executablespec.execution.GroovyExecution;
import name.neuhalfen.concordion.extension.executablespec.execution.NoExecution;
import name.neuhalfen.concordion.extension.executablespec.execution.ScriptExecution;

enum SCRIPTING_LANGUAGE {
    GROOVY("groovy") {
        @Override
        public ScriptExecution scriptRunner() {
            return new GroovyExecution();
        }
    },
    UNKNOWN("unknown") {
        @Override
        public ScriptExecution scriptRunner() {
            return new NoExecution();
        }
    };

    private final String name;

    SCRIPTING_LANGUAGE(String name) {
        this.name = name;
    }


    public abstract ScriptExecution scriptRunner();


    public static SCRIPTING_LANGUAGE fromString(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        name = name.trim().toLowerCase();
        if (GROOVY.name.equals(name)) return GROOVY;
        return UNKNOWN;
    }
}
