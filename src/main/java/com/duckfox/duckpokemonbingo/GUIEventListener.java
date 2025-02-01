package com.duckfox.duckpokemonbingo;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIEventListener implements Listener {
    private DoubleJump plugin;

    public GUIEventListener(DoubleJump plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
       // System.out.println(event.getView().getTitle());
        if (event.getView().getTitle().equals(ChatColor.GRAY +"双跳状态")) {
            event.setCancelled(true); // 防止玩家将物品拖出GUI
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)
                return;

            if (event.getSlot() == 4) { // 确认点击的是中间的开关项
                Player player = (Player) event.getWhoClicked();
                boolean currentState = plugin.getPlayerSwitchState(player);
                plugin.setPlayerSwitchState(player, !currentState); // 切换状态
                player.closeInventory(); // 关闭GUI
                player.openInventory(new GUIManager().createSwitchGUI(plugin, player)); // 重新打开GUI以更新显示
            }
        }
    }
}
