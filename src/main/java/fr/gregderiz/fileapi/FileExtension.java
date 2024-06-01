package fr.gregderiz.fileapi;

public enum FileExtension {
    YML(".yml"),
    TXT(".txt");

    private final String name;

    FileExtension(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
