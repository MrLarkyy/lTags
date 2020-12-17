package ltags;

import ltags.Managers.StorageManager;
import ltags.objects.Tag;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class LTags extends JavaPlugin {

    private File dataFile;
    private File configFile;
    private YamlConfiguration modifyConfigFile;
    private YamlConfiguration modifyDataFile;
    StorageManager storage = new StorageManager(this);


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&b[lTags]&f Plugin has been &aEnabled&f!"));

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        saveDefaultConfig();

        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage.loadTags();
        getCommand("tags").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&b[lTags]&f Plugin has been &cDisabled&f!"));
    }

    public YamlConfiguration getDataFile() {
        return modifyDataFile;
    }

    public YamlConfiguration getConfigFile() {
        return modifyConfigFile;
    }
    public File getCFile() {
        return configFile;
    }

    public File getFile() {
        return dataFile;
    }

    public void loadFiles() throws IOException {
        dataFile = new File(getDataFolder(),"data.yml");
        configFile = new File(getDataFolder(),"config.yml");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        modifyDataFile = YamlConfiguration.loadConfiguration(dataFile);
        modifyConfigFile = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveTag(String identifier, String display, List<String> lore) {
        Tag tag = new Tag(display,identifier,lore);
        storage.addTag(tag);
    }

    public void sendMessage(Player p, String message) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
    }


}
