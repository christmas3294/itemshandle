package com.duckfox.duckpokemonbingo.tradegui;

import com.duckfox.duckpokemonbingo.EconomyUtils;
import com.duckfox.duckpokemonbingo.ZdziszkeeTrade;
import com.duckfox.duckpokemonbingo.configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;
import java.time.LocalDateTime;
import java.util.HashMap;


public class TradeGUI implements GUI{
    private final ZdziszkeeTrade main;
    private final Inventory inventory;
    private final Player thisPlayer;
    private final Player otherPlayer;
    private final HashMap<Integer, ItemStack> myItems = new HashMap<>();
    private boolean isAccepted;
    private boolean isClosed;
    private final Config config;
    private final FileConfiguration fileConfiguration;
    private final int index;


    public TradeGUI(final Player thisPlayer, final Player otherPlayer, final ZdziszkeeTrade main,int index) {
        this.main = main;
        this.thisPlayer = thisPlayer;
        this.otherPlayer = otherPlayer;
        this.index = index;
        //初始化界面槽数
        this.inventory = Bukkit.createInventory(this, 36, "§7§l "+ZdziszkeeTrade.install.tradetitle + otherPlayer.getName());
        this.isAccepted = false;
        this.isClosed = false;
        this.config = main.getTradeLogs();
        this.fileConfiguration = config.getConfig();
        fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), " 已启动与…的交流 " + otherPlayer.getName());
        config.saveConfig();
    }

    @Override
    public void onGUIClick(final Player whoClicked, final int slot, final Inventory clickedInventory, final ItemStack clickedItem) {
        // Handling trade confirm button, and trade finalization
        if (slot == 4) {
//            gui.onPlayerInventoryClick((Player) e.getWhoClicked(), e.getSlot(),1);
            addItem(whoClicked.getInventory().getItem(slot), slot,2);
           // this.isAccepted = true;
         //   whoClicked.playSound(whoClicked.getLocation(), Sound.BLOCK_NOTE_BASS, 3, 3);
            updateInventories();

        }


        if (slot == 30) {
            this.isAccepted = true;
            whoClicked.playSound(whoClicked.getLocation(), Sound.BLOCK_NOTE_BASS, 3, 3);
            updateInventories();
            if (getOtherPlayerGUI().isAccepted()) {

                updateInventories();
                finalizeTrade();
            }
        }

        // Logic to allow player remove his items from trade
        // when trade is not accepted by any of players
//允许玩家从交易中删除物品的逻辑
//当存在一方没有接受
        if (!this.isAccepted && !getOtherPlayerGUI().isAccepted()) {
            for (int i : TradeGUIUtils.getActiveSlots()) {
                if (i == slot) {
                    removeItem(i);
                    return;
                }
            }
        }
    }


    @Override
    public void onGUIClose(final Player player) {

        // Logic which synchronize player trade gui closing and
        //  giving back their items
        this.fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), " " + thisPlayer.getName() + " zamknal okno wymiany z " + otherPlayer.getName());
        config.saveConfig();
        main.removeTradeRequest(player);
        if (!(isAccepted && getOtherPlayerGUI().isAccepted())) {
            this.isClosed = true;
            giveBackItems();
            if (!getOtherPlayerGUI().isClosed()) {
                getOtherPlayerGUI().getThisPlayer().closeInventory();
            }
        }

    }

    @Override
    public void onGUIOpen(final Player player) {

    }

    @Override
    public void onPlayerInventoryClick(final Player player, final int slot,int counts) {

        // Logic for adding items to trade when
        // any of player haven't accepted trade

        addItem(player.getInventory().getItem(slot), slot,counts);

    }

    /**
     * Method inherited from InventoryHolder interface
     *
     * @return inventory
     */
    //初始化界面槽数
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Method for finalizing trade when both players accepted trade
     * It gives items to specified players
     */
    public boolean lock = true;
    //*当两名玩家都接受交易时，完成交易的方法
    //*它为指定的玩家提供物品
    public void finalizeTrade() {
        for (int i : TradeGUIUtils.getActiveSlots()) {
            if (myItems.get(i) != null && myItems.get(i).getType() != Material.AIR) {
                otherPlayer.getInventory().addItem(myItems.get(i));
            }
         //   System.out.println(index);

            if (index == 2){

                if (lock){
                    lock= false;
                    //   System.out.println("index");
                    double balance = EconomyUtils.getBalance(thisPlayer);
                    if (balance >0) {

                        EconomyUtils.withdraw(thisPlayer, Tradecount);
                        //     System.out.println("deposit"+Tradecount);
                        EconomyUtils.deposit(otherPlayer, Tradecount);
                    }




                }

            }else {
                if (lock) {
                    lock = false;
                    //   System.out.println("index");
                    double balance = EconomyUtils.getBalance(otherPlayer);
                    if (balance > 0) {

                        EconomyUtils.withdraw(otherPlayer, Tradecount);
                        //     System.out.println("deposit"+Tradecount);
                        EconomyUtils.deposit(thisPlayer, Tradecount);
                    }
                }


            }

        }
        for (int i : TradeGUIUtils.getActiveSlots()) {
            if (getOtherPlayerGUI().getMyItems().get(i) != null && getOtherPlayerGUI().getMyItems().get(i).getType() != Material.AIR) {
                thisPlayer.getInventory().addItem(getOtherPlayerGUI().getMyItems().get(i));
            }
        }
      //  thisPlayer.playSound(thisPlayer.getLocation(), Sound.MUSIC_CREDITS, 3, 3);
        //otherPlayer.playSound(otherPlayer.getLocation(), Sound.MUSIC_CREDITS, 3, 3);
        this.fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), " Koniec wymiany " + thisPlayer.getName() + " z " + otherPlayer.getName());
        this.fileConfiguration.set(otherPlayer.getName() + "." + LocalDateTime.now().toString(), " Koniec wymiany " + otherPlayer.getName() + " z " + thisPlayer.getName());
        config.saveConfig();
        thisPlayer.closeInventory();
        otherPlayer.closeInventory();
        main.removeTrade(thisPlayer);


    }

    /**
     * Getting the TradeGUI which player you are trading with view
     *
     * @return TradeGui object of player u are trading with
     */
    public TradeGUI getOtherPlayerGUI() {
        //判断当前玩家是否为 发送者 还是接收者 并且获取更新另一个关系的界面
        if (!main.getTrade(thisPlayer).getReceiverTradeGUI().equals(this)) {
            return main.getTrade(thisPlayer).getReceiverTradeGUI();
        }
        // senderTradeGUI = new TradeGUI(player1, player2, zdziszkeeTrade);
        //        receiverTradeGUI = new TradeGUI(player2, player1, zdziszkeeTrade);
        //判断是否是当前的界面实例
        //更新对方的界面实例
//通过当前实例判断是否是对方的界面(其他的实例)
        if (!main.getTrade(thisPlayer).getSenderTradeGUI().equals(this)) {
            return main.getTrade(thisPlayer).getSenderTradeGUI();
        }
        return null;
    }



    public int Tradecount;

    /**
     * Adding itemStack to trade
     *
     * @param itemStack the item u want to add to trade
     * @param slot      the slot in player inventory the item were
     */
    //玩家交易物品上架到交易库
    public void addItem(final ItemStack itemStack, final int slot,int counts) {
        switch (counts){
            case 1:{

                if (!this.isAccepted && !getOtherPlayerGUI().isAccepted()) {
                    for (int s : TradeGUIUtils.getActiveSlots()) {
                        if (myItems.get(s) == null || myItems.get(s).getType() == Material.AIR) {
                            myItems.put(s, itemStack);
                            updateInventories();
                            thisPlayer.getInventory().setItem(slot, new ItemStack(Material.AIR));
                            fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), thisPlayer.getName() + " wklada do okna wymiany " + (itemStack.toString()));
                            config.saveConfig();
                            return;
                        }
                    }
                }
            }
            case 2:{
              //  System.out.println(1);
                if (!this.isAccepted && !getOtherPlayerGUI().isAccepted()) {
                   // System.out.println(2);
                    for (int s : TradeGUIUtils.getActiveSlots()) {
                    //    System.out.println(3);
                        if (myItems.get(s) == null || myItems.get(s).getType() == Material.AIR) {
                    //        System.out.println(4);
                           // myItems.remove(s);

           //                 System.out.println(balance);
//                            System.out.println(5);
////itemStack.getItemMeta().setDisplayName("交易金额"+balance);
//                            System.out.println(6);

//Tradecount
                         //   System.out.println(EconomyUtils.getBalance(thisPlayer));
                            TradeGUI otherPlayerGUI = getOtherPlayerGUI();
                            //标记1还是标记2 全看他的确认时间
                            if (index == 2){
                                double balance = EconomyUtils.getBalance(thisPlayer);
                                if (balance > Tradecount){
                                    Tradecount +=100;
                                    otherPlayerGUI.Tradecount = Tradecount;
                                    myItems.put(4, itemStack);
                                    updateInventories();
                                }


                            }

                        //    thisPlayer.getInventory().setItem(4, new ItemStack(Material.GLASS));
                          //  fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), thisPlayer.getName() + " wklada do okna wymiany " + (itemStack.toString()));
                           // config.saveConfig();
                            //return;

break;
                        }
                    }


                }





            }

        }


    }

    /**
     * Give back items which player put in trade
     */
    //归还玩家投入交易的物品
    public void giveBackItems() {
        for (int i : TradeGUIUtils.getActiveSlots()) {
            if (myItems.get(i) != null) {
                thisPlayer.getInventory().addItem(myItems.get(i));
            }
        }
    }

    /**
     * Method for removing  itemStack from trade
     */
    public void removeItem(final int slot) {
        final ItemStack itemStack = myItems.get(slot);
        if (itemStack != null) {
            myItems.remove(slot);
            thisPlayer.getInventory().addItem(itemStack);
            updateInventories();
            fileConfiguration.set(thisPlayer.getName() + "." + LocalDateTime.now().toString(), thisPlayer.getName() + "wyjmuje z okna wymiany " + itemStack.toString());
            config.saveConfig();
        }
    }

    /**
     * Opening the trade gui for player
     */
    //*为玩家打开交易界面
    public void openInventory() {
        this.inventory.setContents(getContents());
        thisPlayer.openInventory(inventory);
    }

    /**
     * Updating contents of TradeGui
     */
    public void updateInventory() {
        this.inventory.setContents(getContents());
    }

    /**
     * Updating both players TradeGUIS
     */
    //更新对方玩家的界面
    public void updateInventories() {
        updateInventory();
        getOtherPlayerGUI().updateInventory();
    }

    /**
     * Getting contents for TradeGUI
     *
     * @return contents for TradeGUI
     */
    //更新自己的界面
    //可以通过获取对方实例的界面 获取getContents() 拿到对方玩家的更新界面
    public ItemStack[] getContents() {

        ItemStack glassPane = TradeGUIUtils.getGlassPane(thisPlayer, otherPlayer,Tradecount);
        //Creating local gui for getting contents
        //创建本地gui以获取内容
        //槽位 30 和 32 放置确认按钮。
        // 这些按钮反映当前交易的接受状态。
        // isAccepted 变量表明当前玩家是否已接受交易，
        // 而 getOtherPlayerGUI().isAccepted() 获取交易对方是否已接受。
        final Inventory test = Bukkit.createInventory(null, 36);
        //玻璃分割坐标
        test.setItem(4, glassPane);
        test.setItem(13, glassPane);
        test.setItem(22, glassPane);
        test.setItem(31, glassPane);
        test.setItem(30, TradeGUIUtils.getConfirmButton(isAccepted));
        test.setItem(32, TradeGUIUtils.getConfirmButton(getOtherPlayerGUI().isAccepted));
        //Getting the items player u are trading with put in trade
        //创建了一个新的 Inventory 实例（称为 test），设置为36个槽位，这对应于一个标准的Minecraft背包界面。
        //在特定的槽位中放置上述玻璃板，用于界面的布局分割。
        for (int i : TradeGUIUtils.getPassiveSlots()) {
            if (getOtherPlayerGUI().getMyItems().get(TradeGUIUtils.convertToActiveSlot(i)) != null) {
                //在当前实例(一方的实例 需要将对方的坐标进行转换根据转换后的坐标进行渲染)
                // 左边的坐标转换到右边坐标
                //实现对方的槽位在右侧的效果
                //  for (int s : TradeGUIUtils.getActiveSlots()) {
                //                        if (myItems.get(s) == null || myItems.get(s).getType() == Material.AIR) {
                //                            myItems.put(s, itemStack);
                //                            updateInventories();
            //通过上述方式添加交易物品到左侧槽位
                test.setItem(i, getOtherPlayerGUI().getMyItems().get(TradeGUIUtils.convertToActiveSlot(i)));
               // System.out.println(i);

            }
        }
        //Getting the items you put in trade
        //TradeGUIUtils.getActiveSlots() 左侧坐标数组
        //将实例的物品渲染到左侧
        for (int i : TradeGUIUtils.getActiveSlots()) {
            if (myItems.get(i) != null) {
                test.setItem(i, myItems.get(i));
            }
        }
        return test.getContents();

    }

    public Player getThisPlayer() {
        return thisPlayer;
    }


    public boolean isAccepted() {
        return isAccepted;
    }


    public boolean isClosed() {
        return isClosed;
    }

    public HashMap<Integer, ItemStack> getMyItems() {
        return myItems;
    }
}
