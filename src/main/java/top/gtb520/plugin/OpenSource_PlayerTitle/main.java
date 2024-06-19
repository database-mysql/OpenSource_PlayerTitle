package top.gtb520.plugin.OpenSource_PlayerTitle;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import top.gtb520.plugin.OpenSource_PlayerTitle.commands.command_list;
import top.gtb520.plugin.OpenSource_PlayerTitle.commands.title;
import top.gtb520.plugin.OpenSource_PlayerTitle.unity.PlaceholderAPI;
import top.gtb520.plugin.OpenSource_PlayerTitle.unity.YamlFile_Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static top.gtb520.plugin.OpenSource_PlayerTitle.unity.tools.GetLoggerPlus;

public final class main extends JavaPlugin implements Listener {
    public static main instance;
    public static YamlFile_Utils Yaml;
    private static PluginDescriptionFile descriptionFile;

    public static PluginDescriptionFile getDescriptionFile() {
        return descriptionFile;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        descriptionFile = getDescription();
        GetLoggerPlus("\n" +
                "   ___                 ___                      \n" +
                "  / _ \\ _ __  ___ _ _ / __| ___ _  _ _ _ __ ___ \n" +
                " | (_) | '_ \\/ -_) ' \\\\__ \\/ _ \\ || | '_/ _/ -_)\n" +
                "  \\___/| .__/\\___|_||_|___/\\___/\\_,_|_| \\__\\___|\n" +
                "       |_|                                      \n");
        GetLoggerPlus("&d===============================Title插件===============================");
        GetLoggerPlus("&2插件开始加载");

        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        boolean Enable = main.instance.getConfig().getBoolean("Server.Enable");

        if (placeholderAPI == null) {
            // 使用日志方法记录找不到插件的信息，并优雅地关闭服务器
            GetLoggerPlus("&4找不到PlaceholderAPI,请安装PlaceholderAPI后才能使用本插件");
            try {
                Thread.sleep(2300);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GetLoggerPlus("&e已自动禁用此插件，不影响后续插件运行");
            Bukkit.getServer().getPluginManager().disablePlugins();
        } else {
            // 已检测到PlaceholderAPI，记录日志
            GetLoggerPlus("&2已检测到PlaceholderAPI前置");
        }

        File Folder = new File(String.valueOf(getDataFolder()));
        File Config_File = new File(getDataFolder(), "config.yml");
        Yaml = new YamlFile_Utils();
        if (!Folder.exists() || !Config_File.exists()) {
            Folder.mkdirs();
            Yaml.saveYamlFile(getDataFolder().getPath(), "config.yml", "config.yml",true);
        }

        List<File> Folders = new ArrayList<>();
        Folders.add(new File(getDataFolder().getPath()));
        Makedirs(Folders);

        saveDefaultConfig();
        reloadConfig();

        if (!Enable) {
            GetLoggerPlus("&d插件配置文件未启用此插件");
            Bukkit.getPluginManager().disablePlugins();
        }

        GetLoggerPlus("&2&l插件加载完成！");
        GetLoggerPlus("&d===============================Title插件===============================");
        GetLoggerPlus("&d插件开始注册命令和事件监听器");

//        Bukkit.getPluginManager().registerEvents(this, this);
        new PlaceholderAPI(this).register();
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        Objects.requireNonNull(getCommand("OpenSourceTitle")).setExecutor(new title());
        Objects.requireNonNull(getCommand("OpenSourceTitle")).setTabCompleter(new command_list());

        GetLoggerPlus("&2&l插件注册命令和事件监听器完成！");
        GetLoggerPlus("&d===============================Title插件===============================");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance =null;
    }
    private static void Makedirs(List<File> Folders) {
        for (File EachFolder : Folders) {
            if (!EachFolder.exists()) {
                EachFolder.mkdirs();
            }
        }
    }
}
