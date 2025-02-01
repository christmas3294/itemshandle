package com.duckfox.duckpokemonbingo;

import com.duckfox.duckpokemonbingo.tradegui.TradeGUI;
import org.bukkit.entity.Player;

public class Trade {
    /**
     * This class is representing trade between 2 players
     */
    //这个类代表两个玩家之间的交易
    private final TradeGUI senderTradeGUI;
    private final TradeGUI receiverTradeGUI;

    public Trade(Player player1, Player player2, ZdziszkeeTrade zdziszkeeTrade) {
        //初始化双方交易界面
        senderTradeGUI = new TradeGUI(player1, player2, zdziszkeeTrade,1);
        receiverTradeGUI = new TradeGUI(player2, player1, zdziszkeeTrade,2);
        //this = new Trade(sender, receiver, main);
        //该this实例存储了
        // new TradeGUI(player1, player2, zdziszkeeTrade);
        // new TradeGUI(player2, player1, zdziszkeeTrade);
        zdziszkeeTrade.addTrade(player1, this);
        zdziszkeeTrade.addTrade(player2, this);
    }

    public void openInventories() {
        senderTradeGUI.openInventory();
        receiverTradeGUI.openInventory();
    }


    public TradeGUI getSenderTradeGUI() {
        return senderTradeGUI;
    }

    public TradeGUI getReceiverTradeGUI() {
        return receiverTradeGUI;
    }
}