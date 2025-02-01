package com.duckfox.duckpokemonbingo.tradegui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


//为了监听 所以要写个接口实现函数
//通过监听 判断 执行实现函数
public interface GUI extends InventoryHolder {
    default void onGUIClick(final Player whoClicked, final int slot, final Inventory clickedInventory, final ItemStack clickedItem) {
    }

    default void onGUIClose(final Player player) {
    }

    default void onGUIOpen(final Player player) {
    }

    default void onPlayerInventoryClick(final Player player, final int slot,int counts) {
    }

}
