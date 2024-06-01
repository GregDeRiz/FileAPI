package fr.gregderiz.fileapi;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public final class FileManager {
    private final FileController fileController;
    private final Set<File> directories;

    @SuppressWarnings("all")
    public FileManager(File file) {
        this.fileController = new FileController(this);
        this.directories = new HashSet<>();

        if (!file.exists()) file.mkdir();
        addDirectory(file);
    }

    public void addDirectory(File directory) {
        this.directories.removeIf(file -> file.getName().equalsIgnoreCase(directory.getName()));
        this.directories.add(directory);
    }

    public void removeDirectory(File directory) {
        if (!this.directories.contains(directory)) return;

        this.directories.remove(directory);
    }

    public Optional<File> findDirectoryByName(String name) {
        return this.directories.stream().filter(file -> file.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<File> findDirectoryInFolderByName(File folder, String name) {
        File[] files = folder.listFiles();
        if (files == null) return Optional.empty();

        return Arrays.stream(files).filter(File::isDirectory).filter(file -> file.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<File> findFileInFolderByName(File folder, String name) {
        File[] files = folder.listFiles();
        if (files == null) return Optional.empty();

        return Arrays.stream(files).filter(File::isFile).filter(file -> file.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void destroy() {
        this.directories.clear();
    }

    public FileController getFileController() {
        return this.fileController;
    }

    public Set<File> getDirectories() {
        return this.directories;
    }
}
