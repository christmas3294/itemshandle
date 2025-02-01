package com.duckfox.duckpokemonbingo;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.entity.Player;

public class EconomyUtils {
    private static Economy econ = null;

    // 初始化 Vault Economy 服务
    public static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            econ = rsp.getProvider();
        }
        return econ != null;
    }

    // 获取玩家的余额
    public static double getBalance(Player player) {
        if (econ != null) {
            return econ.getBalance(player);
        }
        return 0.0;
    }

    // 给玩家添加余额
    public static boolean deposit(Player player, double amount) {
        player.sendMessage("获取 " + amount +"金币");
        //System.out.println("给玩家添加余额"+amount);
        if (econ != null) {
            return econ.depositPlayer(player, amount).transactionSuccess();
        }
        return false;
    }

    // 从玩家余额中扣除金额
    public static boolean withdraw(Player player, double amount) {
        player.sendMessage("扣除 " + amount +"金币");
       // System.out.println("从玩家余额中扣除金额"+amount);
        if (econ != null) {
            return econ.withdrawPlayer(player, amount).transactionSuccess();
        }
        return false;
    }
}

