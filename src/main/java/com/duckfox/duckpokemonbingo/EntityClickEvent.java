package com.duckfox.duckpokemonbingo;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.HashMap;
import java.util.Map;

public class EntityClickEvent implements Listener {
    private final ZdziszkeeTrade main;
    private final Map<Player, Long> lastTrigger = new HashMap<>();

    public EntityClickEvent(final ZdziszkeeTrade main) {
        this.main = main;
    }

    /**
     * Logic for sending a trade request to player and accepting it
     *
     * @param e event
     */
    //玩家交易请求发送
//    @EventHandler
//    public void onEntityClick(final PlayerInteractAtEntityEvent e) {
//        //这个意思是sender(该玩家)是否右键的实体为玩家
//        if (e.getRightClicked() instanceof Player) {
//            if (e.getPlayer().isSneaking()) {
//                final Long last = lastTrigger.get(e.getPlayer());
//                //此函数大概是判断玩家方向前面是否为存在一个玩家
//                //System.out.println("last"+last);
//                if (last != null && System.currentTimeMillis() < last + 3000L) return;
//             //此处的逻辑需要判断 玩家是否交易请求初始化
//                //没有初始化需要执行此处逻辑进行初始化
//                final Player sender = e.getPlayer();
//                //这个意思是sender(该玩家)是否右键的实体为玩家 获取该被请求玩家
//                final Player receiver = (Player) e.getRightClicked();
//
//              //  receiver.playSound(receiver.getLocation(), Sound.MUSIC_CREDITS, 3, 3);
////请求交易的玩家需要进 交易队列
//                lastTrigger.put(sender, System.currentTimeMillis());
//                //将双方玩家 包装成TradeRequest 方便数据访问操作
//                //正常流程
//                //第一次玩家 key
//                //第二次玩家 key
//                main.addTradeRequest(sender, new TradeRequest(sender, receiver));
//
//                sender.sendMessage(ChatColor.GRAY + "您向发送了交易请求 " + ChatColor.DARK_GRAY + receiver.getName());
//                receiver.sendMessage(ChatColor.GRAY + "您已收到交易请求 " + ChatColor.DARK_GRAY + sender.getName());
//
//                Bukkit.getScheduler().runTaskLater(main, () -> lastTrigger.remove(sender), 20 * 10L);
////双方玩家都右键对方 执行交易逻辑初始化
//                if (main.getTradeRequest(receiver) != null) {
//                  //  System.out.println("双方玩家都右键对方 执行交易逻辑初始化");
//                    //获取该请求对象存储的双方玩家
//                    final Player senderReceiver = main.getTradeRequest(sender).getReceiver();
//                    final Player receiverReceiver = main.getTradeRequest(receiver).getReceiver();
//                    //被请求方的回复请求符合预期
//                    //被请求方现在是请求对象 存储的被请求对象 是否符合 当前事件的被请求对象
//                    //第一次的请求对象 获取的 被请求对象 是否符合 当前事件的 请求对象
//                    //通过上述逻辑获取符合条件的请求交易对象
//                    if (senderReceiver.equals(receiver) && receiverReceiver.equals(sender)) {
//                        final Trade trade = new Trade(sender, receiver, main);
//                        trade.openInventories();
//                    }
//                }
//            }
//        }
//    }


    public void onEntityClickschose(final Player sender,Player receiver) {
        //这个意思是sender(该玩家)是否右键的实体为玩家
      //  if (e.getRightClicked() instanceof Player) {
          //  if (e.getPlayer().isSneaking()) {
                final Long last = lastTrigger.get(sender);
                //此函数大概是判断玩家方向前面是否为存在一个玩家
              //  System.out.println("last"+last);
                if (last != null && System.currentTimeMillis() < last + 3000L) return;
                //此处的逻辑需要判断 玩家是否交易请求初始化
                //没有初始化需要执行此处逻辑进行初始化
            //    final Player sender = e.getPlayer();
                //这个意思是sender(该玩家)是否右键的实体为玩家 获取该被请求玩家
              //  final Player receiver = (Player) e.getRightClicked();

             //   receiver.playSound(receiver.getLocation(), Sound.MUSIC_CREDITS, 3, 3);
//请求交易的玩家需要进 交易队列
                lastTrigger.put(sender, System.currentTimeMillis());
                //将双方玩家 包装成TradeRequest 方便数据访问操作
                //正常流程
                //第一次玩家 key
                //第二次玩家 key
                main.addTradeRequest(sender, new TradeRequest(sender, receiver));

                sender.sendMessage(ChatColor.GRAY + "您已将交易请求发送到 " + ChatColor.DARK_GRAY + receiver.getName());
                receiver.sendMessage(ChatColor.GRAY + "您收到了来自的交易请求 " + ChatColor.DARK_GRAY + sender.getName());

                Bukkit.getScheduler().runTaskLater(main, () -> lastTrigger.remove(sender), 20 * 10L);
//双方玩家都右键对方 执行交易逻辑初始化
                if (main.getTradeRequest(receiver) != null) {
                 //   System.out.println("双方玩家都右键对方 执行交易逻辑初始化");
                    //获取该请求对象存储的双方玩家
                    final Player senderReceiver = main.getTradeRequest(sender).getReceiver();
                    final Player receiverReceiver = main.getTradeRequest(receiver).getReceiver();
                    //被请求方的回复请求符合预期
                    //被请求方现在是请求对象 存储的被请求对象 是否符合 当前事件的被请求对象
                    //第一次的请求对象 获取的 被请求对象 是否符合 当前事件的 请求对象
                    //通过上述逻辑获取符合条件的请求交易对象
                    if (senderReceiver.equals(receiver) && receiverReceiver.equals(sender)) {
                        final Trade trade = new Trade(sender, receiver, main);
                        trade.openInventories();
                    }
                }
         //   }
    //    }
    }
}
