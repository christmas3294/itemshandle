package com.duckfox.duckpokemonbingo.gui;

import com.duckfox.duckpokemonbingo.DuckPokemonBingo;
import com.duckfox.duckpokemonbingo.Sounds;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Minesweeper implements Listener {

    public static ItemStack empty;
    public static ArrayList<ItemStack> banner = new ArrayList<>();
    public static ItemStack mine;

    public static HashMap<String, msInventory> saolei = new HashMap<>();
//    public static void init(){
//
//        //配置初始化
//        msInventory.title=DuckPokemonBingo.config.getString("Minesweeper.title");
//        msInventory.size =(DuckPokemonBingo.config.getInt("Minesweeper.inventory.size")/9)*9;
//        msInventory.minesize = DuckPokemonBingo.config.getInt("Minesweeper.inventory.mine.average")%msInventory.size;
//        msInventory.minedelta = DuckPokemonBingo.config.getInt("Minesweeper.inventory.mine.delta");
//
//        //物品显示初始化
//        empty= DuckPokemonBingo.getItemStack("Minesweeper.display.empty");
//
//        if(!DuckPokemonBingo.config.contains("Minesweeper.display.banner.0"))
//        {
//            DuckPokemonBingo.logger.warning("您需要至少设置一个 Minesweeper.display.banner");
//            Bukkit.getPluginManager().disablePlugin(DuckPokemonBingo.instance);
//        }
//        else
//        {
//            ItemStack bannernow = DuckPokemonBingo.getItemStack("Minesweeper.display.banner.0");
//            banner.add(bannernow.clone());
//
//            for(int i=1;i<=8;i++){
//                if(DuckPokemonBingo.config.contains("Minesweeper.display.banner."+i))
//                    bannernow=DuckPokemonBingo.getItemStack("Minesweeper.display.banner."+i);
//
//                banner.add(bannernow.clone());
//            }
//        }
//
//
//        mine=DuckPokemonBingo.getItemStack("Minesweeper.display.mine");
//    }

    public static void start(Player player){

        Sounds.StartSound(player);

        if(!saolei.containsKey(player.getName()))
            saolei.put(player.getName(), new msInventory(player.getName()));
        //saoleiindex.put(player.getName(),0);
        player.openInventory(saolei.get(player.getName()).inv);
    }

    public static void executeCommand(String command) {
        // 获取控制台发送者
        ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();

        // 执行命令
        Bukkit.dispatchCommand(consoleSender, command);
    }

    public static void end(Player player, boolean win){

        Date a = new Date();

        if(win)
        {

          //  net.minecraft.item.ItemStack itemStack = PixelmonItems.getAllItems().get(5436).func_190903_i();
//            PlayerParticipant playerParticipant = new PlayerParticipant();
//            System.out.println(1);
//           executeCommand("give "+player.getDisplayName()+' '+DuckPokemonBingo.config.getString("Minesweeper.messages.items")+" 8");
            Sounds.WinSound(player);

            player.sendMessage(DuckPokemonBingo.config.getString("Minesweeper.messages.win")
                    .replace("%time%",getTime(saolei.get(player.getName()).time,a,true)));

            Bukkit.broadcastMessage(DuckPokemonBingo.config.getString("Minesweeper.messages.broad")
                    .replace("%player%",player.getDisplayName())
                    .replace("%time%",getTime(saolei.get(player.getName()).time,a,true)));
            // 获取玩家的名字
            String playerName = player.getName();

            // 原始命令字符串
            String command = DuckPokemonBingo.config.getString("Minesweeper.messages.items");

            // 替换 %playername% 为实际的玩家名字
            command = command.replace("%playername%", playerName);

            // 获取控制台发送者
            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();

            // 执行命令
            Bukkit.dispatchCommand(consoleSender, command);



        }
        else{

            Sounds.FailedSound(player);

            player.sendMessage(DuckPokemonBingo.config.getString("Minesweeper.messages.failed")
                    .replace("%time%",getTime(saolei.get(player.getName()).time,a,true)));

        }

        saolei.remove(player.getName());

    }


    private static String getTime(Date start,Date end,boolean record){

        long time =  end.getTime()-start.getTime();
        if(record)
        {
            if(DuckPokemonBingo.config.getLong("Minesweeper.record")>time){
                Bukkit.broadcastMessage(DuckPokemonBingo.config.getString("Minesweeper.breakrecord"));
                DuckPokemonBingo.config.set("Minesweeper.record",time);
                DuckPokemonBingo.saveCFG();
            }
        }


        String out = "";
        if(time%(1000*60*60)>0){
            out+=time%(1000*60*60)+"小时";
            out+=time%(1000*60)+"分钟";
            out+=time%(60)+"秒";
        }
        else
        {
            if(time%(1000*60)>0)
            {
                out+=time%(1000*60)+"分钟";
                out+=time%(60)+"秒";
            }
            else
            {
                out+=time+"秒";
            }
        }
        return out;
    }

    @EventHandler(ignoreCancelled = true)
    private static void Choose(InventoryClickEvent e){


        if(saolei.containsKey(e.getWhoClicked().getName())){
            if(e.getInventory().equals(saolei.get(e.getWhoClicked().getName()).inv)){

                e.setCancelled(true);


                if(e.isLeftClick()){
                    saolei.get(e.getWhoClicked().getName()).leftSlot(e.getSlot());
                }
                else if(e.isRightClick())
                {
                    saolei.get(e.getWhoClicked().getName()).rightSlot(e.getSlot());
                }

            }

        }
        /*
        else if(e.getInventory().getTitle().equals(msInventory.title))
        {
            e.setCancelled(true);
        }*/
    }
}
