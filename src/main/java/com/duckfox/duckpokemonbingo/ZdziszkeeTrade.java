package com.duckfox.duckpokemonbingo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ZdziszkeeTrade extends JavaPlugin {

  public static ZdziszkeeTrade install;

    @Override
    public void onEnable() {
        // 初始化经济服务
        if (!EconomyUtils.setupEconomy()) {
            getLogger().warning("Vault经济插件未找到或未正确安装！");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
      //  Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityClickEvent(), this);

    }



    @Override
    public void onDisable() {
    }

}
