package com.duckfox.duckpokemonbingo;

import com.duckfox.duckpokemonbingo.configuration.Config;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class DoubleJump extends JavaPlugin implements Listener {
    private HashMap<UUID, Boolean> playerSwitchStates;
    private GUIEventListener guiEventListener;


    Config configs;
    public int price;


    @Override
    public void onEnable() {
        // 初始化经济服务
        if (!EconomyUtils.setupEconomy()) {
            getLogger().warning("Vault经济插件未找到或未正确安装！");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        configs = new Config(this, "config.yml");
        FileConfiguration config1 = configs.getConfig();
        configs.saveConfig();
//        System.out.println(configs);
//        System.out.println(tradeLogs);
        if (config1.get("price") == null) {

            //  System.out.println("");

            config1.set("price",40);
            price = config1.getInt("price");
            //   System.out.println("title"+title);
            // config.set("trades.title", string);
        }else {

            //  config = new Config(this, "Tradeconfig.yml");

            price = config1.getInt("price");
            // System.out.println("title"+title);
            // config.set("trades.title", string);
        }


        configs.saveConfig();





        playerSwitchStates = new HashMap<>();
        guiEventListener = new GUIEventListener(this);
        getServer().getPluginManager().registerEvents(guiEventListener, this);
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        SwitchCommand switchCommand = new SwitchCommand(this);

        // 注册命令
        if (getCommand("switchstate") != null) {
            getCommand("switchstate").setExecutor(switchCommand);
        }
    }
    public void setPlayerSwitchState(Player player, boolean state) {

        playerSwitchStates.put(player.getUniqueId(), state);
    }

    public boolean getPlayerSwitchState(Player player) {
        return playerSwitchStates.getOrDefault(player.getUniqueId(), false);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // 默认状态为 false
        this.setPlayerSwitchState(event.getPlayer(), false);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // 清理玩家状态
        this.playerSwitchStates.remove(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {


        //  System.out.println("PlayerToggleFlightEvent");
        Player player = event.getPlayer();
      //  System.out.println(EconomyUtils.getBalance(event.getPlayer()));
        Boolean b = playerSwitchStates.get(event.getPlayer().getUniqueId());
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true); // 取消默认飞行
            if (!player.isOnGround() && player.getAllowFlight()) {
                if (b) {
                    if (EconomyUtils.getBalance(event.getPlayer()) >= price) {
                        // 给予玩家一个向上的速度，实现二次跳跃
                        double x = player.getVelocity().normalize().getX();
                        double z = player.getVelocity().normalize().getZ();
                        Vector vector = applyJumpBoost(player);
                        player.setVelocity(player.getVelocity().add(new Vector(vector.getX(), 1, vector.getZ())));
                        EconomyUtils.withdraw(event.getPlayer(), price);
                    }
                }
                player.setAllowFlight(false); // 跳跃后禁止再次飞行，直到触地



                }

        }

    }
    public Vector applyJumpBoost(Player player) {
        // 获取玩家的偏航角yaw和俯仰角pitch（以度为单位）
        //Yaw 影响 X 和 Z 坐标，控制水平方向的朝向。(旋转y轴)
        //Pitch 影响 Y 坐标，控制垂直方向的望向。(旋转x轴)
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
// this.setMotion(this.getMotion().add(
// MathHelper.sin(-this.rotationYaw * ((float) Math.PI / 180F)) * f, 0.0D,
// MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * f));
        // 将角度转换为弧度
        double yawRad = Math.toRadians(yaw);
        double pitchRad = Math.toRadians(pitch);
        // 计算方向矢量
        //yaw 影响左右值 那么公式需要yawRad
        //pitchRad 间接影响x轴z轴 所以 影响的时候会基于 x *y z*y 进行符合预期的变换
        double x = Math.cos(pitchRad) * -Math.sin(yawRad);
        //pitch旋转的量 影响y轴 那么将需要影响的值给y轴
        //   Vector3d end = start.add(-distance * MathHelper.sin(finalYaw) * MathHelper.cos(finalPitch),
        //   -distance * MathHelper.sin(finalPitch),
        //   distance * MathHelper.cos(finalYaw) * MathHelper.cos(finalPitch));
        double y = -Math.sin(pitchRad);
        double z = Math.cos(pitchRad) * Math.cos(yawRad);

        // 创建新的速度矢量，加强Y轴以实现跳跃效果
        return  new Vector(x, 1 + Math.abs(y), z);

        // 设置玩家的速度
      //  player.setVelocity(newVelocity);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.isOnGround()) {
            // 玩家触地时允许飞行，以便能进行下一次双跳



                 //   if (!player.getAllowFlight())
                      //  System.out.println("玩家触地时允许飞行，以便能进行下一次双跳");
                //    EconomyUtils.withdraw(event.getPlayer(), price);

                player.setAllowFlight(true);

            }

        }
    }