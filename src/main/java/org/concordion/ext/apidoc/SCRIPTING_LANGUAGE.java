package org.concordion.ext.apidoc;

import org.concordion.ext.apidoc.execution.GroovyExecution;
import org.concordion.ext.apidoc.execution.NoExecution;
import org.concordion.ext.apidoc.execution.ScriptExecution;

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
