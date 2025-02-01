package com.duckfox.duckpokemonbingo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager {

    public Inventory createSwitchGUI(DoubleJump plugin, Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.GRAY+"双跳状态"); // 创建一个9格的GUI

        ItemStack switchItem = new ItemStack(Material.LEVER, 1); // 使用杠杆作为图标
        ItemMeta meta = switchItem.getItemMeta();
        boolean state = plugin.getPlayerSwitchState(player); // 获取当前状态
        meta.setDisplayName("(当前状态 " + (state ? "ON" : "OFF") + ")");
        switchItem.setItemMeta(meta);

        gui.setItem(4, switchItem); // 将物品放在GUI中间

        return gui;
    }
}
