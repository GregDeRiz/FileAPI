package fr.gregderiz.fileapi;

import java.io.*;

@SuppressWarnings("all")
public final class FileController {
    private final FileManager fileManager;

    public FileController(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public File create(File parent, String name) {
        if (!parent.exists()) parent.mkdirs();

        File file = new File(parent, name);
        if (!file.exists()) file.mkdir();

        this.fileManager.addDirectory(parent);
        return file;
    }

    public File create(FileBuilder fileBuilder) throws IOException {
        File file = fileBuilder.save();
        File parent = file.getParentFile();
        if (!parent.exists()) parent.mkdirs();
        if (!file.exists()) file.createNewFile();

        this.fileManager.addDirectory(parent);
        return file;
    }

    public void copy(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) destination.mkdirs();

            String[] files = source.list();
            if (files == null) return;

            for (String file : files) {
                File newSource = new File(source, file);
                File newDestination = new File(destination, file);
                copy(newSource, newDestination);
            }
        } else {
            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];

            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();
        }
    }

    public void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File child : files) delete(child);
        }

        file.delete();
        this.fileManager.addDirectory(file.getParentFile());
    }
}
