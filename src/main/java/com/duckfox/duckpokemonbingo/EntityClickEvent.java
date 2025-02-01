package com.duckfox.duckpokemonbingo;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;
import java.util.Set;

import static org.bukkit.Material.*;

public class EntityClickEvent  implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        ItemStack itemStack = event.getItem().getItemStack();

        switch (itemStack.getType()) {
            case DIAMOND: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*1000);
                break;
            }
            case EMERALD: {
                event.getItem().getItemStack().getAmount();
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*800);
                break;
            }
            case IRON_INGOT: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*40);
                event.getItem().getItemStack().getAmount();
                break;
            }
            case GOLD_INGOT: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*200);
                event.getItem().getItemStack().getAmount();
                break;
            }
            case REDSTONE: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*10);
                event.getItem().getItemStack().getAmount();
                break;
            }
            case LAPIS_ORE: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*80);
                event.getItem().getItemStack().getAmount();
                break;
            }
            case COAL: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*4);
                event.getItem().getItemStack().getAmount();
                break;
            }
            case QUARTZ: {
                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*12);
                event.getItem().getItemStack().getAmount();
                break;
            }
            default:{

                int amount1 = event.getItem().getItemStack().getAmount();
                EconomyUtils.deposit(event.getPlayer(),amount1*1);
                event.getItem().getItemStack().getAmount();
            }
                break;
        }

        // event.getPlayer()
//        int amount = event.getItem().getItemStack().getAmount();
//        EconomyUtils.deposit(event.getPlayer(),amount*1);
        event.setCancelled(true); // 取消物品拾取
        event.getItem().remove(); // 销毁物品实体
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        Material itemType = droppedItem.getType();

        // 检查是否允许丢弃
            event.setCancelled(true); // 取消丢弃事件
            player.sendMessage(ChatColor.RED + "⚠ 请使用物品交易 " + itemType.name() + "！");

    }
}
