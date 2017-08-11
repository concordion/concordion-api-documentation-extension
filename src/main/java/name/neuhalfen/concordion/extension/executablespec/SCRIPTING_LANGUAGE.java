package name.neuhalfen.concordion.extension.executablespec;

enum SCRIPTING_LANGUAGE {
    GROOVY("groovy"),
    UNKNOWN("unknown");

    private final String name;

    SCRIPTING_LANGUAGE(String name) {
        this.name = name;
    }

    public static SCRIPTING_LANGUAGE fromString(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        name = name.trim().toLowerCase();
        if (GROOVY.name.equals(name)) return GROOVY;
        return UNKNOWN;
    }
}
