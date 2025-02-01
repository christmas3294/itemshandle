package com.duckfox.duckpokemonbingo.tradegui;

import com.duckfox.duckpokemonbingo.EntityClickEvent;
import com.duckfox.duckpokemonbingo.ZdziszkeeTrade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIListener implements Listener {
    /**
     * Events needed for GUI to work
     *
     * @param e event
     */
    @EventHandler
    public void onInvClick(final InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof GUI) {
            e.setCancelled(true);
            final GUI gui = (GUI) e.getInventory().getHolder();
            gui.onGUIClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getClickedInventory(), e.getCurrentItem());
            //EconomyUtils.getBalance()
        }

    }

    @EventHandler
    public void onPlayerInvClick(final InventoryClickEvent e) {
        if (e.getWhoClicked().getOpenInventory().getTopInventory().getHolder() instanceof GUI) {
            if (e.getClickedInventory() != null) {
                if (e.getClickedInventory().getType() == InventoryType.PLAYER) {


                    e.setCancelled(true);
                    final GUI gui = (GUI) e.getInventory().getHolder();
                    gui.onPlayerInventoryClick((Player) e.getWhoClicked(), e.getSlot(),1);
             //EconomyUtils.getBalance()
                }
            }
        }
    }

    @EventHandler
    public void onInvClose(final InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof GUI) {
            final GUI gui = (GUI) e.getInventory().getHolder();
            gui.onGUIClose((Player) e.getPlayer());
        }
    }

    @EventHandler
    public void onInvOpen(final InventoryOpenEvent e) {
        if (e.getInventory().getHolder() instanceof GUI) {
            final GUI gui = (GUI) e.getInventory().getHolder();
           // gui.onGUIOpen((Player) e.getPlayer());
        }
    }

    @EventHandler
    public void Send(final PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            openPlayerListGUI(e.getPlayer());
        }

    }

    public void openPlayerListGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN +ZdziszkeeTrade.install.title);

        int slot = 0;
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            //System.out.println("onlinePlayer"+onlinePlayer);
            // 创建显示在线玩家的物品
            ItemStack item = new ItemStack(Material.JACK_O_LANTERN);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + ZdziszkeeTrade.install.playertitle+onlinePlayer.getName());
            List<String> lore = new ArrayList<>();
          //  lore.add("点击与 " + onlinePlayer.getName() + " 进行互动");
            meta.setLore(lore);
            item.setItemMeta(meta);

            // 将物品放到界面中
            inv.setItem(slot, item);
            slot++;
        }

        player.openInventory(inv);
    }


    // 监听点击事件
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

      //  System.out.println("event.getInventory().getTitle()"+event.getInventory().getTitle());
       // System.out.println("event.getInventory().getType()"+ZdziszkeeTrade.install.title));
        if (event.getInventory().getTitle().toString().equals(ChatColor.GREEN +ZdziszkeeTrade.install.title)) {
           // System.out.println("ZdziszkeeTrade.install.title");
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta()) {
                return;
            }
            Player player = (Player) event.getWhoClicked();
            String string = ChatColor.GREEN + ZdziszkeeTrade.install.playertitle;

            String replace = clickedItem.getItemMeta().getDisplayName().replace(string, "");
//            System.out.println(replace);
//            System.out.println(1);
            String clickedPlayerName = replace;
//            System.out.println(2);
            if (clickedPlayerName != null) {
              //  System.out.println(3);
                Player clickedPlayer = Bukkit.getPlayer(clickedPlayerName);
                if (clickedPlayer != null) {
                //    System.out.println(4);
                    if (!clickedPlayer.getName().equals(event.getWhoClicked().getName()) && clickedPlayer.getInventory().getItemInMainHand().getType() == Material.AIR) {

                            new EntityClickEvent(ZdziszkeeTrade.install).onEntityClickschose(player,clickedPlayer);


                        //player.sendMessage("你选择了与 " + clickedPlayer.getName() + " 互动！");
                       // ZdziszkeeTrade
//                        ZdziszkeeTrade.install

                    }


                    // 这里可以添加与玩家互动的逻辑，例如打开交易、发送消息、等
                }
            }
        }
    }

    // 监听点击事件
    @EventHandler
    public void onAsyncPlayerChatEvent (AsyncPlayerChatEvent event) {
        Player player = (Player) event.getPlayer();


        String message = event.getMessage();
        if (message.equalsIgnoreCase("list")) {
            event.setCancelled(true);  // 取消聊天消息的广播
            Bukkit.getScheduler().runTask(ZdziszkeeTrade.install, () -> openPlayerListGUI(event.getPlayer()));
        }
    }
    private void setPlayer(Player player) {
        // 计算在线玩家数并创建相应大小的 Inventory
        int size = ((Bukkit.getOnlinePlayers().size() - 1) / 9 + 1) * 9;
        Inventory gui = Bukkit.createInventory(null, size, ChatColor.BLUE + "Online Players");

        // 遍历每个在线玩家，创建一个头颅物品代表他们
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.JACK_O_LANTERN, 1);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(onlinePlayer);
            meta.setDisplayName(ChatColor.GREEN + onlinePlayer.getName());
            head.setItemMeta(meta);
            gui.addItem(head);
        }

        // 打开 GUI
        player.openInventory(gui);
    }

}
