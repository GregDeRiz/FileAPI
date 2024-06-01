package fr.gregderiz.fileapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

public class FileReader {
    private final FileConfiguration configuration;

    public FileReader(File file) {
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public Object get(String name) {
        return this.configuration.get(name);
    }

    public List<?> list(String name) {
        return this.configuration.getList(name);
    }

    public Map<?, ?> section(String name) {
        return (Map<?, ?>) this.configuration.getConfigurationSection(name);
    }

    public void remove(String name) {
        this.configuration.set(name, null);
    }
}
