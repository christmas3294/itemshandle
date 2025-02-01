package com.duckfox.duckpokemonbingo.gui;

import com.duckfox.duckpokemonbingo.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class msInventory {

    public static String title;
    public static int size;
    public static int minesize;
    public static int minedelta;

    public Inventory inv;
    public HashMap<Integer,Integer> slots = new HashMap<>();
    public Date time = new Date();
    public String player;
    //用名字作为玩家标识的原因是：玩家下线再登录后Player会刷新



    private static Random random = new Random();

    public msInventory(String player){

        inv = Bukkit.createInventory(null,size,title);

        //重置列表
        for(int i=0;i<size;i++){
            slots.put(i,0);
        }

        //设置雷
        for(int i=0;i<minesize;i++)
        {
            slots.replace(random.nextInt(size),-1);
        }

        //数字计算
        for(int i=0;i<size/9;i++){
            for(int j=0;j<9;j++){

                //是雷
                if(slots.get(i*9+j)==-1)
                {
                    System.out.println("输出地雷位置x"+i+1+"y"+j+1);
                    add((i-1)*9+j);
                    add((i+1)*9+j);

                    if(j+1<=8)
                    {
                        add(i*9+j+1);
                        add((i-1)*9+j+1);
                        add((i+1)*9+j+1);
                    }

                    if(j-1>=0)
                    {
                        add(i*9+j-1);
                        add((i-1)*9+j-1);
                        add((i+1)*9+j-1);
                    }

                }

                inv.setItem(i*9+j, Minesweeper.empty);
            }
        }

        this.player=player;

    }

    private void add(int slot){
        if(slots.containsKey(slot)){

            if(slots.get(slot)!=-1)
            {
                slots.replace(slot,slots.get(slot)+1);
            }
        }
    }

    public void leftSlot(int slot){
        if(slots.containsKey(slot)){

            if(slots.get(slot)==-1)
                end(false);
            else
                show(slot);

            if(check())
                end(true);
            System.out.println("leftSlot");
        }

    }

    public void rightSlot(int slot){
        if(slots.containsKey(slot)){

            if(slots.get(slot)==-1)
                show(slot);
            else
                end(false);


            if(check())
                end(true);
        }
    }

    private boolean check(){
     //   System.out.println("size"+size);

        System.out.println(Minesweeper.mine);
        for(int i=0;i<size;i++){
         //   System.out.println(inv.getItem(i));
            if(slots.get(i)==-1)
                return false;
        }
        return true;
    }

    private void end(boolean win){
        System.out.println("end");
        for(int i=0;i<size;i++)
            show(i);

        Minesweeper.end(Bukkit.getPlayer(player),win);
    }

    private void show(int slot){

        if(slots.containsKey(slot)){



            Sounds.GameSound1(Bukkit.getPlayer(player));

            if(slots.get(slot)==-1){
                inv.setItem(slot, Minesweeper.mine);
                slots.put(slot,0);
            }
            else
            {
                if(slots.get(slot)==0){

//                    Minesweeper.saoleiindex.put()
//                    saoleiindex.put(player.getName(),0);
                    showzero(slot);
                }
                else {
                    inv.setItem(slot, Minesweeper.banner.get(slots.get(slot)));
                }
            }
        }

    }
    private void show(int slot, String p){

        if(slots.containsKey(slot)){



            Sounds.GameSound1(Bukkit.getPlayer(player));

            if(slots.get(slot)==-1){
                inv.setItem(slot, Minesweeper.mine);
            }
            else
            {
                if(slots.get(slot)==0){

                   // Minesweeper.saoleiindex.put(p,)
                   // saoleiindex.put(player.getName(),0);
                    showzero(slot);
                }
                else {
                    inv.setItem(slot, Minesweeper.banner.get(slots.get(slot)));
                }
            }
        }

    }
    private void showzero(int slot){

        inv.setItem(slot, Minesweeper.banner.get(0));

        if(slots.get(slot).equals(0))
        {

            checkzero(slot - 9);
            checkzero(slot + 9);

            if(slot%9+1<=8){
                checkzero(slot+1);
                checkzero(slot-9+1);
                checkzero(slot+9+1);
            }

            if(slot%9-1>=0){
                checkzero(slot-1);
                checkzero(slot-9-1);
                checkzero(slot+9-1);
            }
        }
    }
    private void showzero(int slot,String player){

        inv.setItem(slot, Minesweeper.banner.get(0));

        if(slots.get(slot).equals(0))
        {
//            int i = Minesweeper.saoleiindex.get(player);
//            Minesweeper.saoleiindex.put(player,i+1);

            checkzero(slot - 9);
            checkzero(slot + 9);

            if(slot%9+1<=8){
                checkzero(slot+1);
                checkzero(slot-9+1);
                checkzero(slot+9+1);
            }

            if(slot%9-1>=0){
                checkzero(slot-1);
                checkzero(slot-9-1);
                checkzero(slot+9-1);
            }
        }
    }
    private void checkzero(int slot){
        if(slots.containsKey(slot)){
            if(!inv.getItem(slot).equals(Minesweeper.banner.get(0))
                    && slots.get(slot)==0){   //为0则继续递归
                show(slot);
            }
            else if(slots.get(slot)!=-1){  //不为雷则显示
                inv.setItem(slot, Minesweeper.banner.get(slots.get(slot)));
            }
        }
    }
}
