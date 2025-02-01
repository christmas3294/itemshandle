package com.duckfox.duckpokemonbingo;

import com.duckfox.duckpokemonbingo.configuration.Config;
import com.duckfox.duckpokemonbingo.tradegui.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ZdziszkeeTrade extends JavaPlugin {
    Config tradeLogs;


      Config configs;
    //    player.sendMessage(DuckPokemonBingo.config.getString("Minesweeper.messages.win")
    //                    .replace("%time%",getTime(saolei.get(player.getName()).time,a,true)));

    public static File configfile;
  public static ZdziszkeeTrade install;
    /**
     * Hashmap storing all active trades
     */
    private final Map<Player, Trade> tradeHashMap = new HashMap<>();
    /**
     * Hashmap storing last trade request of specified player
     */
    private final Map<Player, TradeRequest> tradeRequestHashMap = new HashMap<>();

public String title;
public String tradetitle;
public String playertitle;
public String submit;

    @Override
    public void onEnable() {
        // 初始化经济服务
        if (!EconomyUtils.setupEconomy()) {
            getLogger().warning("Vault经济插件未找到或未正确安装！");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityClickEvent(this), this);
        this.tradeLogs = new Config(this, "tradeLogs.yml");
        this.tradeLogs.saveConfig();
        install = this;
      //  System.out.println(1111);
        configs = new Config(this, "Tradeconfig.yml");
        FileConfiguration config1 = configs.getConfig();
        configs.saveConfig();
//        System.out.println(configs);
//        System.out.println(tradeLogs);
        if (config1.get("title") == null) {

          //  System.out.println("");

            config1.set("title","交易列表");
            config1.set("tradetitle","当前交易玩家");
            config1.set("playertitle","交易玩家");
            config1.set("submit","确认交易");
            title = config1.getString("title");
         //   System.out.println("title"+title);
            // config.set("trades.title", string);
            tradetitle = config1.getString("tradetitle");
            //   config.set("trades.tradetitle", string);
            playertitle = config1.getString("playertitle");
            submit = config1.getString("submit");
        }else {

            //  config = new Config(this, "Tradeconfig.yml");

            title = config1.getString("title");
           // System.out.println("title"+title);
            // config.set("trades.title", string);
            tradetitle = config1.getString("tradetitle");
            //   config.set("trades.tradetitle", string);
            playertitle = config1.getString("playertitle");
            submit = config1.getString("submit");
        }


        configs.saveConfig();
    }


    public Config getTradeLogs() {
        return tradeLogs;
    }

    /**
     * Adding Trade class object to hashmap
     *
     * @param player the player which contest in Trade
     * @param trade  trade object
     */
    public void addTrade(final Player player, final Trade trade) {

       //问题在这个trade
//        System.out.println("获取当前玩家的这个trade值 用于判断更新对方玩家的格子"+trade);
        tradeHashMap.put(player, trade);
    }

    /**
     * Method for removing trade object  from hashmap
     *
     * @param player the player which contest in Trade
     */
    public void removeTrade(final Player player) {
        try {
            tradeHashMap.remove(player);
        } catch (final NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for getting the trade
     *
     * @param player the player which contest in trade
     * @return trade object
     */
    //这个大概是存储的 当前交易请求对象
    public Trade getTrade(final Player player) {
        return tradeHashMap.get(player);
    }

    /**
     * Method for adding trade request to hashmap
     *
     * @param player       playerName which contest in trade
     * @param tradeRequest object
     */
    //hashmap添加交易请求的方法
    public void addTradeRequest(final Player player, final TradeRequest tradeRequest) {
        tradeRequestHashMap.put(player, tradeRequest);
    }

    /**
     * Getting the trade request of given player
     *
     * @param player playerName of player u want get trade request
     * @return Trade request of player
     */
    //玩家名称可以作为 交易的uuid
    //通过这个uuid 获取唯一请求函数
    public TradeRequest getTradeRequest(final Player player) {
        return tradeRequestHashMap.get(player);
    }

    public void removeTradeRequest(final Player player) {
        tradeRequestHashMap.remove(player);
    }


    @Override
    public void onDisable() {
    }

}
