package fr.gregderiz.fileapi;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileBuilder {
    private final File file;
    private final FileConfiguration configuration;

    public FileBuilder(File parent, String name, FileExtension fileExtension) {
        this.file = new File(parent, name + fileExtension.getName());
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileBuilder set(String name, Object value) {
        this.configuration.set(name, value);
        return this;
    }

    public FileBuilder list(String name, List<Object> list) {
        this.configuration.set(name, list);
        return this;
    }

    public FileBuilder section(String name, Map<String, Object> map) {
        ConfigurationSection section = this.configuration.createSection(name);
        map.forEach(section::set);
        return this;
    }

    public File save() throws IOException {
        this.configuration.save(this.file);
        return this.file;
    }
}
