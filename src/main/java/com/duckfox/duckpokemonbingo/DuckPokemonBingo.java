package com.duckfox.duckpokemonbingo;


import catserver.api.bukkit.event.ForgeEvent;
import com.duckfox.duckpokemonbingo.gui.Pair;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.selection.PartySelectionFactory;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StoragePosition;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.comm.EnumUpdateType;
import com.pixelmonmod.pixelmon.enums.battle.BattleResults;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public  class DuckPokemonBingo  extends JavaPlugin  implements Listener {
    Material  worlditem = Material.GLOWSTONE;
//    ArrayList<String> arrayList = new ArrayList<String>();
    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        //List<String> stringList = DuckPokemonBingo.config.getStringList("Minesweeper.world");
//        boolean o = getConfig().getBoolean("Minesweeper.worldtitle");
//        if (o){
//
//            worlditem = Material.JACK_O_LANTERN;
//
//        }
        // System.out.println("stringList"+stringList.size());
//        for (String string : stringList) {
//            System.out.println("11");
//           // System.out.println(string);
//            WorldCreator worldCreator = new WorldCreator(string);
//            worldCreator.generator(new CustomChunkGenerator());
//            World personalMinesWorld = Bukkit.createWorld(worldCreator);
//            personalMinesWorld.getWorldBorder().setCenter(0, 0);
//            personalMinesWorld.getWorldBorder().setSize(30000000);
//            personalMinesWorld.setDifficulty(Difficulty.PEACEFUL);
//
//        }



        config = getConfig();
        instance=this;
        logger=getLogger();
        configfile=new File(getDataFolder(),"config.yml");
        saveDefaultConfig();

      //  plugin = this;
        // 注册事件监听器到 Forge 的事件总线
      //  MinecraftForge.EVENT_BUS.register(this);
      //  saveDefaultConfig();

       // getLogger().info("原版鱼竿钓宝可梦系统已开启");
        //Pixelmon.instance.init();
    }

//    @EventHandler
//    public void onWorldInit(WorldInitEvent event) {
//        // 监听世界初始化事件，获取世界生成器
//        World world = event.getWorld();
//
//        // 检查世界生成器类型，并修改
//       // world.getPopulators().removeIf(populator -> populator.getClass().getName().contains("MushroomIsland"));
//    }
//    // 用于存储每个 Chunk 的前一个 X 坐标
//    private final HashMap<String, Integer> previousX = new HashMap<>();
//    private final HashMap<String, Integer> previousZ = new HashMap<>();
//boolean lock = false;

//    @EventHandler
//    public void onChunkGenerate(ChunkLoadEvent event) {
//        if (event.isNewChunk()) {
//            if (true) {
//               // event.getWorld().setBiome(event.getChunk().getX(), event.getChunk().getZ(), Biome.BEACHES);
//
//
//                World world1 = event.getWorld();
////        world1.setBiome();
//                World world = event.getWorld();
//                ChunkSnapshot chunkData = event.getChunk().getChunkSnapshot();
//                //    ChunkSnapshot chunkSnapshot = event.getChunk().getChunkSnapshot(true, true, true);
//
//                lock = true;
//                //!(world.getBiome(event.getChunk().getX(), event.getChunk().getZ()) == Biome.OCEAN
////                if (true) {
////
//////                    // 获取当前 Chunk 的唯一标识符，避免不同 Chunk 之间干扰
//////                    String chunkIdentifier = "UUIDrandomUUIDtoString";
//////                    // 获取当前 Chunk 的唯一标识符，避免不同 Chunk 之间干扰
//////                    String chunkIdentifierz = "UUIDrandomUUIDtoStringz";
//////                    // 获取之前保存的 X 值
//////                    Integer previousXValue = previousX.get(chunkIdentifier);
//////                    Integer previousXValuez = previousZ.get(chunkIdentifierz);
////                    Integer currentX = event.getChunk().getX();
////                    Integer currentZ = event.getChunk().getZ();
////                    //  System.out.println(1);
//////                    if (previousXValue != null) {
//////                        if (previousXValuez != null) {
////                            //  System.out.println(22);
////                            // 比较当前 X 值和前一 tick 的值
//////                            if (true) {
//////                                if (true) {
////                                    //  System.out.println("current");
////
////
////                                    // System.out.println("Chunk X changed for " + chunkIdentifier + ": Previous X = " + previousXValue + ", Current X = " + currentX);
//////                                    world.setBiome(event.getChunk().getX(), event.getChunk().getZ(), Biome.DESERT);
//////                                    world.refreshChunk(event.getChunk().getX(), event.getChunk().getZ());
////
////                                    //event.getChunk().getChunkSnapshot().getBiome()
////                                    // event.getChunk().getChunkSnapshot().getBiome(event.getChunk().getX(),event.getChunk().getZ()).setBiome(event.getChunk().getX(),event.getChunk().getZ(),Biome.DESERT);
////
////                                    //  System.out.println(world.getBiome(event.getChunk().getX(),event.getChunk().getZ()));
////                                    //  Bukkit.getScheduler().runTask(getInstance(),()->{
////
////
////                                    world.getPopulators().add(new BlockPopulator() {
////                                        @Override
////                                        public void populate(World world, Random random, Chunk chunk) {
////                                            Block block;
////                                            Biome biome = world.getBiome(chunk.getX(), chunk.getZ());
////                                            //      if (biome == Biome.OCEAN) {
////
////                                            // biome.getDeclaringClass()
////                                            //   world.setBiome(chunk.getX(), chunk.getZ(), Biome.DESERT);
////                                            //    System.out.println("Biome.DESERT");
////                                            // world.setBiome(x, z, Biome.PLAINS);
////                                            //   world.refreshChunk(chunk.getX(), chunk.getZ());
////                                           // int index = 0;
////
////                                            for (int i = 0; i < 8; i++) {
////                                                int randomindex = random.nextInt(180);
////                                                Block block1 = chunk.getBlock(currentX, randomindex+i, currentZ);
////                                                if (block1.getType() != Material.AIR) {
////                                                    arrayList.add(randomindex+i);
////                                                }
////                                            }
////
//////                                         if (block1.getType() != Material.AIR){
//////                                            for (int i = 60; i < 80; i++) {
//////                                                Block block1 = chunk.getBlock(currentX, i, currentZ);
//////                                         if (block1.getType() != Material.AIR){
//////
//////
//////
//////
//////                                         }
//////
//////                                            }
////
////
////                                            for (int x = 1; x <= 2; x++) {
////                                                for (int z = 1; z <= 2; z++) {
////                                                //    for (int y = 40; y <= 100; y++) {
////
///////tp 82000 100 18000
////
////                                                    for (int i : arrayList) {
////                                                        block = chunk.getBlock(x, i, z);
////                                                        block.setType(worlditem);
////                                                       // if (block.getType() != Material.AIR) {
////
////
////
////                                                            // chunk.getChunkSnapshot()
//////                                                        world.setBiome(x, z, Biome.PLAINS);
//////                                                        world.refreshChunk(x, z);
////
////
////
////                                                    //    }
////                                                    }
////
////                                                        //    if (block.isEmpty()) {
////
////                                                        //world.setBiome((chunk.getX() * 16), (chunk.getZ() * 16), Biome.DESERT);
////                                                        //   world.setBiome(x, z, Biome.DESERT);
////                                                        //    }
////
////                                                  //  }
////                                                }
////                                            }
////                                            arrayList.clear();
////                                            //   }
////
////
////                                            // chunk.load();
////                                        }
////
////
//////                            System.out.println("传入的世界"+world.getName());
//////                            System.out.println("区块"+chunk.getChunkSnapshot());
////                                        // world.setBiome(event.getChunk().getX() , event.getChunk().getZ(), Biome.BEACHES);
////                                        //     System.out.println(" chunk.unload();"+ chunk.unload());
//////                            chunk.getChunkSnapshot()
//////                            chunk.unload();
////
////
////                                    });
////
////
////                                }
//
//                            }
//                        }
//                    }
                    // 更新保存的前一 tick X 值
//                    previousX.put(chunkIdentifier, currentX);
//                    previousZ.put(chunkIdentifierz, currentZ);
             //   }
                // event.getChunk().getX()

                //  if (event.getChunk().getX()) {


                //   });

                //   }

                // 遍历区块中的每个位置
//        for (int x = 0; x < 16; x++) {
//            for (int z = 0; z < 16; z++) {
//
//
//
//
//
//
//
//
//
//
//
//                // 获取当前生物群系
//                Biome biome = world.getBiome(event.getChunk().getX() * 16 + x, event.getChunk().getZ() * 16 + z);
//
//                // 如果是蘑菇岛群系，替换为普通平原
//                if (biome == Biome.JUNGLE) {
//                    System.out.println("JUNGLE");
//                    //BlockPopulator
//                   // event.getChunk().getChunkSnapshot()
//
//                    int finalX = x;
//                    int finalZ = z;
//                    world.getPopulators().add(new BlockPopulator() {
//                        @Override
//                        public void populate(World world, Random random, Chunk chunk) {
//                            System.out.println("传入的世界"+world.getName());
//                            System.out.println("区块"+chunk.getChunkSnapshot());
//                            world.setBiome(event.getChunk().getX() * 16 + finalX, event.getChunk().getZ() * 16 + finalZ, Biome.PLAINS);
//
//                            chunk.unload();
//                           // chunk.getChunkSnapshot().getBiome().
//
//                        }
//                    });
//
//
//                    //event.getChunk().getWorld().setBiome(x,z,Biome.PLAINS);
////                    Biome biome1 = chunkData.getBiome(x, z);
////                    chunkData.setBiome(x, 0, z, Biome.PLAINS);  // 替换成平原群系
////                    chunkData(x, 0, z, Biome.PLAINS);  // 替换成平原群系
//                }
//                if (biome == Biome.HELL) {
//                    System.out.println("Biome.HEL");
//                    //BlockPopulator
//                    // event.getChunk().getChunkSnapshot()
//
//                    int finalX = x;
//                    int finalZ = z;
//                    world.getPopulators().add(new BlockPopulator() {
//                        @Override
//                        public void populate(World world, Random random, Chunk chunk) {
//                            int x = chunk.getBlock(15,15,15).getX();
//                            double y = world.getHighestBlockAt(chunk.getBlock(15,15,15).getX(), chunk.getBlock(15,15,15).getZ()).getLocation().getY()+3;
//                            int z = chunk.getBlock(15,15,15).getZ();
////                            System.out.println("传入的世界"+world.getName());
////                            System.out.println("区块"+chunk.getChunkSnapshot());
//                          //  world.setBiome(x,z, Biome.BEACHES);
//                            chunk.unload();
//                        }
//                    });
//
//
////                    event.getChunk().getWorld().setBiome(x,z,Biome.PLAINS);
////                    Biome biome1 = chunkData.getBiome(x, z);
////                    chunkData.setBiome(x, 0, z, Biome.PLAINS);  // 替换成平原群系
////                    chunkData(x, 0, z, Biome.PLAINS);  // 替换成平原群系
//                }
//
//                if (biome == Biome.FOREST_HILLS) {
//                    System.out.println("FOREST_HILLS");
//
//
//                    // event.getChunk().getChunkSnapshot()
//
//                    world.getPopulators().add(new BlockPopulator() {
//                        @Override
//                        public void populate(World world, Random random, Chunk chunk) {
////                            System.out.println("传入的世界"+world.getName());
////                            System.out.println("区块"+chunk.getChunkSnapshot());
//                          //  world.setBiome(event.getChunk().getX() , event.getChunk().getZ(), Biome.BEACHES);
//                            System.out.println(" chunk.unload();"+ chunk.unload());
//                            chunk.unload();
//                        }
//                    });
//
//
////                    event.getChunk().getWorld().setBiome(x,z,Biome.PLAINS);
//
////                    Biome biome1 = chunkData.getBiome(x, z);
////                    chunkData.setBiome(x, 0, z, Biome.PLAINS);  // 替换成平原群系
////                    chunkData(x, 0, z, Biome.PLAINS);  // 替换成平原群系
//                }
//
//                if (biome == Biome.ROOFED_FOREST) {
//                    System.out.println("ROOFED_FOREST::");
//                    System.out.println("FOREST_HILLS");
//                    int finalX = x;
//                    int finalZ = z;
//
//
//                    //BlockPopulator
//                    // event.getChunk().getChunkSnapshot()
////ROOFED_FOREST:
//
//
//
//                  //  event.getChunk().getWorld().setBiome(x,z,Biome.PLAINS);
////                    Biome biome1 = chunkData.getBiome(x, z);
////                    chunkData.setBiome(x, 0, z, Biome.PLAINS);  // 替换成平原群系
////                    chunkData(x, 0, z, Biome.PLAINS);  // 替换成平原群系
//                }
//            }
//        }
          //  }
      //  }
   // }

    public static DuckPokemonBingo getInstance() {
        return instance;
    }



//}




       static boolean isflag = false;
  static   String name;
  static int isindex = 0;
  static EntityPlayerMP entityPlayerMP;
  static   Pokemon uuid;
   static PartySelectionFactory.SelectionData selectionConsumer;
   static int randoms;
    static boolean isdone = true;
    static int  indexpool = 0;
//    PartyStorage partyStorage;
//            PlayerPartyStorage playerPartyStorage;
//
////    public static void refreshScreen() {
////        Minecraft mc = Minecraft.func_71410_x();
////        if (mc.currentScreen != null) {
////            EntityRenderer current = mc.field_71460_t;
////            mc.setScreen(null);
////            mc.setScreen(current);
////        } else {
////            // 如果当前没有打开的屏幕，可以打开并关闭一个临时屏幕
////            mc.setScreen(new DummyScreen());
////            mc.setScreen(null);
////        }
////    }
HashSet<EntityPlayerMP>  hashplayer= new HashSet();
Boolean Isplayer = false;
    Pokemon pokemon1;
    NBTTagCompound nbtTagCompound1;
    UUID randomuuid;
    HashMap<EntityPlayerMP,Integer> hashMap = new HashMap();
    NBTTagCompound nbtTagCompound;
    UUID nbtTagCompound2;
    @EventHandler
    public void onPixelmonSendOut(ForgeEvent event) {

//      NoticeOverlay.Builder builder = NoticeOverlay.builder();
//        System.out.println(1);
//        // 设置布局
//        builder.setLayout(EnumOverlayLayout.LEFT_AND_RIGHT);
//        System.out.println(2);
////        // 设置文字内容
////        builder.addLine("Welcome to Pixelmon!");
////        builder.addLine("Catch 'em all!");
//        System.out.println(3);
//        // 设置图标
//        builder.setPokemon3D(PokemonSpec.from("Pikachu"));
//        System.out.println(4);

//        if (event.getForgeEvent() instanceof PixelmonReceivedEvent) {
//            EntityPlayerMP player = ((PixelmonReceivedEvent) event.getForgeEvent()).player;
//            NoticeOverlay.hide(player);
//        }


//        if (event.getForgeEvent() instanceof PixelmonDeletedEvent) {
//            EntityPlayerMP player = ((PixelmonDeletedEvent) event.getForgeEvent()).player;
//            if (!hashMap.containsKey(player)) {
//                hashMap.put(player, 0);
//            }
//
//            int deletecount = hashMap.get(player);
//            hashMap.put(player,deletecount+1);
////            player.getBukkitEntity().getInventory().getBoots()
//
//
//
//            Inventory inventory = player.getBukkitEntity().getPlayer().getInventory();
//
//
//
//
//
//
//
//
//
//
//
//
////            for (ItemStack armorContent : player.getBukkitEntity().getInventory()) {
//////                System.out.println(armorContent);
//////                System.out.println(armorContent.getItemMeta().getDisplayName());
////               if (armorContent.getItemMeta().getDisplayName().toString().equals("{PIXELMON_MASTER_BALL x 1}"))
////               {
////                   System.out.println(armorContent);
////
////               }
////
////
////            }
//            boolean lockpokemon = false;
//           // hashMap.put(player, deletecount+1);
//            if (hashMap.get(player) >=4){
//
//                // 遍历玩家背包中的所有物品
//                for (ItemStack item : inventory.getContents()) {
//                    if (item != null && item.getType() != Material.AIR) {
//                        //    System.out.println(item.getType());
//                        if (item.getType().toString().equals("PIXELMON_MASTER_BALL")) {
////                        System.out.println("PIXELMON_MASTER_BALLx1");
////                        System.out.println(item.getAmount());
//                            if (item.getAmount() >= 1){
//                                lockpokemon = true;
//                                item.setAmount(item.getAmount()-1);
//                                break;
//
//                            }else {
//                                //  inventory.remove(item);
//                                //break;
//
//                            }
////                        item.setType(Material.AIR);
////                        item.setAmount(0);
//                        }
//                        // 获取物品的名称
////                    ItemMeta meta = item.getItemMeta();
////                    System.out.println(meta.getDisplayName());
////                    if (meta != null && meta.hasDisplayName()) {
////
////
////                        }
////                    } else {
////
////                        // 如果没有显示名称，则打印物品类型
////                     //   System.out.println("Item: " + item.getType().toString());
////
////                    if (item.getType().toString().contains("MASTER_BALL")){
////                        int amount = item.getAmount();
////                        System.out.println("Amount: " + amount);
////
////                        if (amount ==1){
////                            item.setType(Material.AIR);
////                        }
////
////                        item.setAmount(amount-1);
////                    }
//                    }
//                }
//if (lockpokemon){
//    Random random = new Random();
//    int i = random.nextInt(900);
//    if (i == 0){
//        i = 1;
//    }
//    //905
//
//
//
//    hashMap.put(player,0);
//    PlayerParticipant playerParticipant = new PlayerParticipant(((PixelmonDeletedEvent) event.getForgeEvent()).player);
////                if (nbtTagCompound == null) {
////                    nbtTagCompound = new NBTTagCompound();
////                    nbtTagCompound2 = playerParticipant.allPokemon[1].pokemon.getUUID();
////                }
//    // UUID pokemonUUID = playerParticipant.allPokemon[1].getPokemonUUID();
//
//    //  int slot = first1.getStorage().getSlot(uuid1);  // 通过 UUID 获取宝可梦所在位置
//    //  System.out.println(2);
//    PlayerPartyStorage storage = playerParticipant.getStorage();
//    //  int slot = storage.getSlot(pokemonUUID);
//    // 如果宝可梦存在于仓库中
//    //  if (slot != -1) {
//    // 设置该位置为空，移除宝可梦
//    EnumSpecies
//    storage.set(new StoragePosition(-1, 0), Pixelmon.pokemonFactory.create(EnumSpecies.getFromDex(i)));
//    // storage.set(new StoragePosition(24, slot), Pixelmon.pokemonFactory.create(nbtTagCompound2));
//    // System.out.println(4);
//    // 更新仓库，保存变更
//    storage.setNeedsSaving();
//    //System.out.println(5);
//    storage.notifyListeners(new StoragePosition(-1, 0), Pixelmon.pokemonFactory.create(EnumSpecies.getFromDex(i)), new EnumUpdateType[0]);
//    //  storage.notifyListeners(new StoragePosition(24, slot), Pixelmon.pokemonFactory.create(nbtTagCompound2));
//    // System.out.println(6);
//    // 可选：如果需要，可以清理其他相关数据
//    // 例如：可以设置宝可梦的一些标志（如清空战斗信息等）
//
//    //  }
////
//
//}
//
//
//
//
//            }
////            hashMap.put(((PixelmonDeletedEvent) event.getForgeEvent()).player, 1);
////           // hashMap.put("hash",2);
////            System.out.println(hashMap.get("hash"));
//////hashMap.put(event)
////            ((PixelmonDeletedEvent) event.getForgeEvent()).pokemon
//        //    ((PixelmonDeletedEvent) event.getForgeEvent()).player
////            if (partyStorage == null && playerPartyStorage == null){
////                //      EntityPlayerMP player = ((PixelmonDeletedEvent) event.getForgeEvent()).player;
////                partyStorage = new PartyStorage((((PixelmonDeletedEvent) event.getForgeEvent()).pokemon.getUUID()));
//////          partyStorage.getAll()[0] = null;
//////            partyStorage.set(0,null);
////                playerPartyStorage = new PlayerPartyStorage(   ((PixelmonDeletedEvent) event.getForgeEvent()).player.getBukkitEntity().getPlayer().getUniqueId());                // 更新仓库，保存变更
////            }
////            partyStorage.add(Pixelmon.pokemonFactory.create(EnumSpecies.getFromDex(1)));
////            playerPartyStorage.add(Pixelmon.pokemonFactory.create(EnumSpecies.getFromDex(1)));
//
////            for (int i = 0; i < playerParticipant.allPokemon.length; i++) {
////                playerParticipant.allPokemon[i] = null;
////            }
//
////            System.out.println("partyStorage"+partyStorage.get());
////           // partyStorage.getAll()
//////            for (Pokemon pokemon : partyStorage) {
//////                System.out.println(playerPartyStorage.getAll().length);
//////                System.out.println(pokemon.getDisplayName());
//////            }
////            System.out.println(partyStorage.get(0));
////            for (int i = 0; i < partyStorage.getAll().length; i++) {
////                System.out.println("partyStorage[i]");
////
////                System.out.println(partyStorage.getAll()[i]);
////              //  System.out.println(partyStorage[i]);
////            }
////            System.out.println(1);
////                UUID uuid1 = playerPartyStorage.get(0).getUUID();
//                // 获取宝可梦的存储位置
//
//
//
//
//      //      PartyStorage partyStorage = first1.getStorage();
//                            // 设置该位置为空，移除宝可梦
//                       //     partyStorage.set(new StoragePosition(-1, 0), null);
//
//
////
//
//           // playerPartyStorage.set(0);
//            //Pokemon pokemon = playerPartyStorage.get(0);
//           // UpdateClientPlayerData.ClientDataType lure = UpdateClientPlayerData.ClientDataType.Lure;
//            //new UpdateClientPlayerData.Handler().onSyncMessage(new UpdateClientPlayerData(),null);
//            //new PartyStorage()
//           // playerPartyStorage.updatePlayer();
//            //playerPartyStorage(1);
////            ((PixelmonDeletedEvent) event.getForgeEvent()).player
//         //   new DeletePokemon(playerPartyStorage.get(0).getUUID());
////            playerPartyStorage.setNeedsSaving();
////            playerPartyStorage.getShouldSave();
////            StoragePosition position = playerPartyStorage.getAll()[0].getPosition();
////            playerPartyStorage.set(0,null);
////            ReforgedFileAdapter reforgedFileAdapter = new ReforgedFileAdapter();
//          //  reforgedFileAdapter.save();
//            //new PixelmonDeletedEvent(player,playerPartyStorage.get(0), DeleteType.PC);
//            //new ReforgedFileAdapter().save(playerPartyStorage);
//
//            //            if (Objects.requireNonNull(playerPartyStorage.get(1)).getLevel() >= 10) {
////                playerPartyStorage.get(0).setLevel(playerPartyStorage.get(1).getLevel()-4);
////            }
////            playerPartyStorage.set(0,null);
////            playerPartyStorage.updatePlayer();
////            playerPartyStorage.updatePartyCache();
////            playerPartyStorage.updateTicksTillEncounter();;
//          //  System.out.println(playerPartyStorage);
//
//        }

//        if (event.getForgeEvent() instanceof PixelmonSendOutEvent){
//            NBTTagCompound nbtTagCompound = new NBTTagCompound();
//           // NBTTagCompound nbtTagCompound1 = nbtTagCompound.func_74737_b();
//
////            nbtTagCompound1 = ((PixelmonSendOutEvent) event.getForgeEvent()).pokemon.writeToNBT(nbtTagCompound);
////            pokemon1  = ((PixelmonSendOutEvent) event.getForgeEvent()).pokemon;
////            PlayerPartyStorage playerPartyStorage = new PlayerPartyStorage(((PixelmonSendOutEvent) event.getForgeEvent()).player.getPersistentID());
////            if (Objects.requireNonNull(playerPartyStorage.get(1)).getLevel() >= 10) {
////                playerPartyStorage.get(0).setLevel(playerPartyStorage.get(1).getLevel()-4);
////            }
////if (randomuuid == null){
////    randomuuid = UUID.randomUUID();
////}
//
//
//           // playerPartyStorage.set(0, );
//            //EnumSpecies.MissingNo
//
////            for (Pokemon pokemon : playerPartyStorage.getAll()) {
////
////                pokemon = null;
////               // pokemon.updateDimensionAndEntityID();
////            }
//           // EnumSpecies.MissingNo;
//           // playerPartyStorage.updatePlayer();
//
//
//
//            // 设置该位置为空，移除宝可梦
////                            partyStorage.set(new StoragePosition(-1, slot), null);
//
//            // ((PixelmonSendOutEvent) event.getForgeEvent()).pokemon.readFromNBT(nbtTagCompound);
//        //    ((PixelmonSendOutEvent) event.getForgeEvent()).player.
//
////            boolean getpokemon = nbtTagCompound1.func_186855_b("UUID");
////
////            boolean  getpokemon = nbtTagCompound1.func_186855_b("UUID");
////            System.out.println("是否获取宝可梦"+getpokemon);
//          //  System.out.println("是否获取宝可梦"+bs);
//            // ((PixelmonSendOutEvent) event.getForgeEvent()).player.serializeNBT().func_186855_b(nbtTagCompound1)
//             // ((PixelmonSendOutEvent) event.getForgeEvent()).player.serializeNBT().;
//
//      NoticeOverlay.Builder builder = NoticeOverlay.builder();
//            Pokemon pokemon = ((PixelmonSendOutEvent) event.getForgeEvent()).pokemon;
//            PokemonSpec spec = new PokemonSpec(pokemon.getDisplayName(), "level:100", "shiny:false", "ball:poke_ball");
//
//       // System.out.println(1);
//            builder.setPokemonSprite(spec);
//
//            //PokemonSpec specs = new PokemonSpec(pokemon.getDisplayName(), "shiny:false", "ball:poke_ball");
//            builder.setLayout(EnumOverlayLayout.LEFT);
////            builder.setLines("当前宝可梦"+pokemon.lastBattleCrits);
//            builder.setLines(
//                    "友谊值"+pokemon.getFriendship(),
//                    "最近战斗中的暴击次数"+pokemon.lastBattleCrits);
//
//            EntityPlayerMP player = ((PixelmonSendOutEvent) event.getForgeEvent()).player;
//            Isplayer = false;
//            for (EntityPlayerMP playerMP : hashplayer) {
//                if (playerMP.toString().equals(player.toString())) {
//                    Isplayer=true;
//                }
//            }
////         //   PokemonSpec specs = new PokemonSpec(pokemon.getDisplayName());
//            if (Isplayer){
//                NoticeOverlay.hide(player);
//                hashplayer.remove(player);
//            }else {
//                builder.sendTo( ((PixelmonSendOutEvent) event.getForgeEvent()).player);
//                hashplayer.add(player);
//            }
//
//
////            builder.setEmpty();
////            builder = NoticeOverlay.builder();
////            PokemonSpec specs = new PokemonSpec("pokemon.getDisplayName()", "level:100", "shiny:true", "ball:poke_ball");
////            builder.setEmpty();
////            builder.setLines("");
////            builder.setLayout(EnumOverlayLayout.LEFT);
////            builder.setPokemonSprite(specs);
////            builder.sendTo( ((PixelmonSendOutEvent) event.getForgeEvent()).player);
//          //  spec.nature = Byte.valueOf("");
////            int i = new Random().nextInt(8);
////
////         //   spec = new PokemonSpec(pokemon.getDisplayName(), "level:100", "shiny:true", "ball:poke_ball");
////            System.out.println(i);
//
////            switch (i){
////                case 0:
////                    if ( spec.formInvert == null){
////                        spec.formInvert = false;
////                    }
////                    spec.formInvert = !spec.formInvert;
////                case 1:
////                    //spec.nature = 0;
////                    case 2:
////                        spec.growth = 0;
////                        case 3:
////                            if ( spec.gmaxFactor == null){
////                                spec.formInvert = false;
////                            }
////                            spec.formInvert = !spec.gmaxFactor;
////                            case 4:
////                              //  spec.growth = 0;
////                                case 5:
////                                  //  spec.pokerusSpread = 0;
////                                    case 6:
////                                       spec.ribbons.remove(0);
////
////            }
////            spec.level = 1;
////            spec.shiny = false;
////            spec.pokerusSpread = 0;
////            spec.formInvert = false;
//           // builder.setEmpty();
//            //builder.addLine("").;
//          //  spec.name = "";
//          //  spec.ball = Byte.valueOf("");;
//       //     Pixelmon.network.sendTo(null, null);
//
//         //   builder.sendTo( ((PixelmonSendOutEvent) event.getForgeEvent()).player);
//  //          NoticeOverlay.Builder builders = NoticeOverlay.builder();
////            PokemonSpec specs = new PokemonSpec(pokemon.getDisplayName(), "level:1");
////            builder.setPokemon3D(specs);
////
////            builder.setLayout(EnumOverlayLayout.LEFT);
////            builders.sendTo( ((PixelmonSendOutEvent) event.getForgeEvent()).player);
//
//           // ((PixelmonSendOutEvent) event.getForgeEvent()).player
//        // 设置布局
////        builder.setLayout(EnumOverlayLayout.LEFT_AND_RIGHT);
////            CustomNoticePacket build = builder.build();
////            build.setEmpty();
////            build.setEnabled(true);
////            String[] a  ={"1","2","3","4","5","6","7","8","9"};
////            build.setLines(a);
////        System.out.println(2);
//////        // 设置文字内容
////            builder.addLine("Welcome to Pixelmon!");
////            builder.addLine("Catch 'em all!");
////        System.out.println(3);
////        // 设置图标
////       // builder.setPokemon3D(PokemonSpec.from("Pikachu"));
////        System.out.println(4);
////            MinecraftServer.getServerInst().server.getOnlinePlayers().forEach(craftPlayer -> {
////                builder.sendTo((EntityPlayerMP) craftPlayer.getHandle());
////            });
//           // builder.sendTo( ((CaptureEvent.SuccessfulCapture) event.getForgeEvent()).player);
//
//
//        }
        if (event.getForgeEvent() instanceof BattleEndEvent) {
            ArrayList<Pair> pairs = new ArrayList<>();
            indexpool = 0;
//            ImmutableMap<BattleParticipant, BattleResults> results = ((BattleEndEvent) event.getForgeEvent()).results;
            ((BattleEndEvent) event.getForgeEvent()).results.forEach((battleParticipant, battleResults) -> {
//    battleParticipant.getDisplayName() ==


                //玩家背包宝可梦数量
                //battleParticipant.allPokemon
                pairs.add(new Pair<>(battleParticipant, indexpool));
                indexpool++;
                for (String o : arrayList) {
                    if (o.toString().equals(battleParticipant.getDisplayName()) && battleResults == BattleResults.VICTORY) {
                        name = o.toString();
//            System.out.println("battleResults"+battleResults);
                        if (battleResults == BattleResults.VICTORY) {
                            //  System.out.println("isflag" + isflag);
                            isflag = true;
                        }
                    }
                }


            });
            if (isflag) {
                for (Pair pair : pairs) {
                    //if (!player.getDisplayNameString().equals(name)) {
                    BattleParticipant first = (BattleParticipant) pair.getFirst();
//                    BattleResults second = (BattleResults) pair.getSecond();
                    if (first.getDisplayName().equals(name)) {

                        isflag = false;
                        Random random = new Random();
                        int i = random.nextInt(first.allPokemon.length);
                        int second = (int) pair.getSecond();
                        if (second == 0) {

                            BattleParticipant first1 = (BattleParticipant) pairs.get(1).getFirst();
                            PixelmonWrapper pixelmonWrapper = first1.allPokemon[i];
                            first.getStorage().add(pixelmonWrapper.pokemon);
                            // int slot = first1.getStorage().getSlot(pixelmonWrapper.pokemon.getUUID());
                            // first1.allPokemon[i] = null;
//                            System.out.println("pixelmonWrapper"+pixelmonWrapper);
//                            Pokemon pokemon = pixelmonWrapper.pokemon;
//                            System.out.println(1);
//                            first.getTeamPokemon().add(pixelmonWrapper);
//                            System.out.println(2);
//                            first.addSwitchingOut(pixelmonWrapper);
//                            System.out.println(3);
////                            first.getOpponentPokemon().remove(1);
//                            System.out.println(4);
//                            first1.getPartyPosition(pixelmonWrapper);
//                            System.out.println(5);
//                            first.getPokemonFromParty(pokemon.getUUID());


                            UUID uuid1 = pixelmonWrapper.pokemon.getUUID();
                            // 获取宝可梦的存储位置
                            int slot = first1.getStorage().getSlot(uuid1);  // 通过 UUID 获取宝可梦所在位置
                            // 如果宝可梦存在于仓库中
                            if (slot != -1) {
                                PartyStorage partyStorage = first1.getStorage();
                                // 设置该位置为空，移除宝可梦
                                partyStorage.set(new StoragePosition(-1, slot), null);

                                // 更新仓库，保存变更
                                partyStorage.setNeedsSaving();
                                partyStorage.notifyListeners(new StoragePosition(-1, slot), null, new EnumUpdateType[0]);

                                // 可选：如果需要，可以清理其他相关数据
                                // 例如：可以设置宝可梦的一些标志（如清空战斗信息等）
                            }

                        } else {
                            BattleParticipant first1 = (BattleParticipant) pairs.get(0).getFirst();
                            PixelmonWrapper pixelmonWrapper = first1.allPokemon[i];
//                            Pokemon pokemon = pixelmonWrapper.pokemon;
//                            System.out.println(1);
//                            first.getTeamPokemon().add(pixelmonWrapper);
//                            System.out.println(2);
//                            first.addSwitchingOut(pixelmonWrapper);
//                            System.out.println(3);
////                            first.getOpponentPokemon().remove(1);
//                            System.out.println(4);
//                            first1.getPartyPosition(pixelmonWrapper);
//                            System.out.println(5);
//                            first.getPokemonFromParty(pokemon.getUUID());
                            first.getStorage().add(pixelmonWrapper.pokemon);
                            UUID uuid1 = pixelmonWrapper.pokemon.getUUID();
                            // 获取宝可梦的存储位置
                            int slot = first1.getStorage().getSlot(uuid1);  // 通过 UUID 获取宝可梦所在位置
                            // 如果宝可梦存在于仓库中
                            if (slot != -1) {
                                PartyStorage partyStorage = first1.getStorage();
                                // 设置该位置为空，移除宝可梦
                                partyStorage.set(new StoragePosition(-1, slot), null);

                                // 更新仓库，保存变更
                                partyStorage.setNeedsSaving();
                                partyStorage.notifyListeners(new StoragePosition(-1, slot), null, new EnumUpdateType[0]);

                                // 可选：如果需要，可以清理其他相关数据
                                // 例如：可以设置宝可梦的一些标志（如清空战斗信息等）
                            }
                        }
                        break;
                    }

                }


                for (EntityPlayerMP player : ((BattleEndEvent) event.getForgeEvent()).getPlayers()) {
                    System.out.println("EntityPlayerMP"+player);
                    if (name != null) {

                        if (!player.getDisplayNameString().equals(name)) {
//待做
                            // selectionConsumer = PartySelectionFactory.getSelectionConsumer(player);

                          //  System.out.println("selectionConsumer"+selectionConsumer);
                         //   pairs

                            int pokemonsort = selectionConsumer.getPokemon().size();
                            Random random = new Random();
                            uuid = selectionConsumer.getPokemon().get(1);
                            System.out.println(" 生成 0 到 9 之间的随机数");
                             randoms = random.nextInt(2); // 生成 0 到 9 之间的随机数
                            if (randoms == 0) {
                                randoms = 1;


                            }

                        //    UUID uuid = pokemon.getUUID();
//                            CraftPlayer playerExact = (CraftPlayer) Bukkit.getPlayerExact(name);
                            System.out.println("uuid"+uuid);
                           // PartySelectionFactory.getSelectionConsumer(playerExact);
//Bukkit.getPlayer(name).getPlayer().getInventory()
                            //   PartySelectionFactory.getSelectionConsumer(Bukkit.getPlayer(UUID.randomUUID()).getPlayer().)
if ( isindex == 1){
    if (isdone){
        if (entityPlayerMP != null){
            System.out.println(" PartySelectionFactory");
            isdone = false;
            PartySelectionFactory.getSelectionConsumer(entityPlayerMP).getPokemon().add(uuid);

        }

    }


}
                            isindex= 1;
                           // selectionConsumer.getPokemon()
                          //  selectionConsumer.getPokemon().size();
                        }else {

                            if (isindex == 0) {
                                entityPlayerMP = player;
                                isindex= 1;
                                isdone = true;
                            }else {
                                PartySelectionFactory.SelectionData selectionConsumer = PartySelectionFactory.getSelectionConsumer(player);
                                if (uuid != null) {
                                    if (isdone) {
                                        isdone = false;
                                        selectionConsumer.getPokemon().add(uuid);
                                    }

                                }
                                isindex = 0;

                              //  player.
                            }
                        }

                    }
                    name = null;
                    isflag = true;
                    selectionConsumer.getPokemon().remove(randoms);

                    // player.getDisplayNameString()
                }
                   }


            }
        }





    @EventHandler
    public void onInventoryClick(InventoryClickEvent  event) {


//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            CraftPlayer entityPlayerMP = ((CraftPlayer) onlinePlayer);
//            entityPlayerMP.getHandle();
//            //  builder.sendTo(entityPlayerMP);
//        }
//        // 发送通知
//        builder.sendTo(player);
//
//        builder.addLine("builder");
//        builder.setPokemon3D(PokemonSpec.from("Pikachu"));
//        builder.sendTo(event.getWhoClicked());
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            System.out.println("inventory");
//            builder.sendTo((EntityPlayerMP) onlinePlayer);
//        }
        // 获取当前操作的容器
        Inventory inventory = event.getInventory();

        // 判断是否是箱子
        if (inventory.getHolder() instanceof org.bukkit.block.Chest) {

            Block block = ((Chest) inventory.getHolder()).getBlock();
            // 判断是否是箱子
            if (block.getType() == Material.CHEST) {
                // 获取箱子的位置
                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();

                // 检查箱子下方两格是否是钻石块
                if (isDiamondBlock(x, y - 1, z, block) && isDiamondBlock(x, y - 2, z, block)) {
                    // 检查箱子周围 3x3 地面是否是钻石块
                    if (isDiamondFloor(x, y - 2, z, block)) {
                        // 获取箱子的容器
                        BlockState state = block.getState();
                        if (state instanceof Chest) {
                            Chest chest = (Chest) state;

                            if (chest.getInventory() != null) {
                                // System.out.println(1);
                                inventory = chest.getInventory();
                                //  System.out.println(2);
                                // 检查箱子内是否至少有一个钻石
                                if (hasDiamond(inventory, event.getWhoClicked().getName())) {
                                    removeItemsFromChest(inventory);
                                    removeisDiamondBlock(x, y, z, block);
                                    removeisDiamondBlock(x, y - 1, z, block);
                                    removeisDiamondBlock(x, y - 2, z, block);
                                    removeisDiamondFloor(x, y - 2, z, block);
//                                    System.out.println(5);
                                    event.getWhoClicked().sendMessage("10分钟效果红色玩家！");
//                                      event.getWhoClicked().sendMessage(event.getWhoClicked().getName());
//                                System.out.println(event.getDestination().getName());
                                      //getupdatename(event.getWhoClicked().getName());
//                                // 满足所有条件，执行你的逻辑
//                                event.getWhoClicked().sendMessage("你放置的箱子符合条件！");
                                }
                            }

                        }
                    }
                }
            }
        }
    }












//            // 获取玩家的操作槽位
//            int slot = event.getSlot();
//            ItemStack clickedItem = event.getCurrentItem();
//
//            // 检查是否放入物品（物品不是空的，且玩家放入的物品）
//            if (clickedItem != null && !clickedItem.getType().equals(Material.AIR)) {
//                // 检查是否是放入箱子的操作
//                if (event.getAction().toString().contains("PICKUP")) {
//
//                    // 如果是放入物品的动作（可以判断是否是放入操作）
//                    event.getWhoClicked().sendMessage("你已放入物品: " + clickedItem.getType());
//                }
//            }
//        }
//    }

    // 清空箱子中的所有物品
    public void removeItemsFromChest(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.getType() != Material.AIR) {
                // 移除物品
                inventory.setItem(i, null); // 将物品设置为 null，即移除物品
            }
        }
    }
    // 检查某个位置的方块是否是钻石块
    private boolean isDiamondBlock(int x, int y, int z ,Block block) {
        Block blocks = block.getWorld().getBlockAt(x, y, z);
        return blocks.getType() == Material.DIAMOND_BLOCK;
    }

//     移除某个位置的方块
    private void removeisDiamondBlock(int x, int y, int z ,Block block) {
        block.getWorld().getBlockAt(x, y, z).setType(Material.AIR);

      //  return blocks.getType() == Material.DIAMOND_BLOCK;
    };
    // 移除3x3某个位置的方块
    // 移除3x3某个位置的方块
    private void removeisDiamondFloor(int x, int y, int z,Block block) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                block.getWorld().getBlockAt(x+dx, y, z+dz).setType(Material.AIR);
            }
        }
       // return true;
    }
    // 检查某个位置是否是 3x3 钻石块地面
    private boolean isDiamondFloor(int x, int y, int z,Block block) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (!isDiamondBlock(x + dx, y, z + dz,block)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 检查箱子内是否至少有PIXELMON_MASTER_BALL x 20
    private boolean hasDiamond(Inventory inventory,String name) {
      //  System.out.println(3);
        for (int i = 0; i < inventory.getContents().length; i++) {
            if (inventory.getContents()[i] != null) {
               // System.out.println(4);
                isflag = false;
                // if (item != null && ) {
                if ("ItemStack{PIXELMON_MASTER_BALL x 20}".equals(inventory.getContents()[i].toString())){
                    for (String string : arrayList) {
                        if (string.equals(name)) {
                            isflag = true;
                        }
                    }
                    if (!isflag){
                      // System.out.println("isflag");
                        isflag = false;
//                        inventory.getContents()[i].setType(Material.AIR);

                        getupdatename(name);

                    }



                    return true;

                }
            }

        }
       // for (ItemStack item : inventory.getContents()) {


           // }

      //  System.out.println(4.1);
        return false;
    }


//    public static DuckPokemonBingo instance;
//    public static final BingoManager bingoManager = new BingoManager();
//
//    public static MessageManager getMessageManager() {
//        return instance.messageManager;
//    }
//
//    public static ConfigManager getConfigManager() {
//        return instance.configManager;
//    }

//    public static final AutoSaver saver = AutoSaver.INSTANCE;

    public void getjoinupdatename(String name){
if (team == null){
    // 获取 ScoreboardManager
    ScoreboardManager manager = Bukkit.getScoreboardManager();

board = manager.getNewScoreboard();
    team = board.registerNewTeam("customIDTeam");
}

        arrayList.add(name);
        index++;
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                // 任务内容：向所有在线玩家发送消息
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.GREEN + "这是一条延时 5 秒后发送的消息！");
                }
            }
        }, 100L); // 延时 100 刻（5 秒）
        Player player = Bukkit.getPlayer(name);
        System.out.println(player.getUniqueId());
        team.addEntry(player.getUniqueId().toString());
    }
    public void getleaveupdatename(String name){
        arrayList.remove(name);

        team.removeEntry(name);
    }

public void getupdatename(String name){
//    team.removeEntry(name);
// 创建一个新的 Team

// 设置前缀为红色
    team.setPrefix(ChatColor.RED.toString());

// 可选：设置后缀（例如显示角色）
    team.setSuffix(ChatColor.GRAY + "红色玩家");
    // 获取目标玩家
    Player player = Bukkit.getPlayer(name);

    for (Player string : Bukkit.getOnlinePlayers()) {
        if (name.equals(string.getDisplayName())){
            arrayList.add(string.getDisplayName().toString());


            team.addEntry(string.getDisplayName().toString());
            team.addEntry(string.getUniqueId().toString());
            player.setScoreboard(board);
            startScheduledTask(name);
//            team.addEntry(string.getDisplayName().toString());
//            team.addEntry(string.getUniqueId().toString());
        }else {

            string.setScoreboard(board);
        }

    }

//    for (int i = 0; i < playerpool.length; i++) {
//
//
//// 将玩家加入 Team
//        team.addEntry(player.getDisplayName());
//    }
//
//    for (String string : playerpool) {
//        team.addEntry(string);
//    }
//    Bukkit.getServer()

// 或者使用 UUID（更可靠）



}// 创建一个新的 Scoreboard


    public void getupdatenames(String name){
//    team.removeEntry(name);
// 创建一个新的 Tea
// 设置前缀为红色
        team.setPrefix(ChatColor.BLACK.toString());

// 可选：设置后缀（例如显示角色）
        team.setSuffix(ChatColor.BLACK + "普通玩家");
        // 获取目标玩家
        Player player = Bukkit.getPlayer(name);

        for (Player string : Bukkit.getOnlinePlayers()) {
            if (name.equals(string.getDisplayName())){
               // arrayList.add(string.getDisplayName().toString());
            //    startScheduledTask(name);
                team.addEntry(string.getDisplayName().toString());
                team.addEntry(string.getUniqueId().toString());
                player.setScoreboard(board);

//            team.addEntry(string.getDisplayName().toString());
//            team.addEntry(string.getUniqueId().toString());
            }else {

                string.setScoreboard(board);
            }

        }

//    for (int i = 0; i < playerpool.length; i++) {
//
//
//// 将玩家加入 Team
        //   team.addEntry(player.getDisplayName());
//    }

//    for (String string : playerpool) {
//        team.addEntry(string);
//    }
        // Bukkit.getServer()

// 或者使用 UUID（更可靠）



    }// 创建一个新的 Scoreboard
    public static Scoreboard board;
    public static Team team;
public static   HashSet<String> arrayList = new HashSet();
    public static int index = 0;
//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent event) {
//        //playerpool.add(event.getPlayer().getName().toString());
//       // playerpool[playerpool.length] = event.getPlayer().getName().toString();
//        //System.out.println(event.getPlayer().getName().toString());
//        getjoinupdatename(event.getEventName().toString());
//    }
//    @EventHandler
//    public void leaveplayer(PlayerQuitEvent event){
//        getleaveupdatename(event.getEventName().toString());
//    }
    public void startScheduledTask(String name) {
        // 30 分钟转化为 ticks（1 tick = 1/20 秒，所以 30 分钟 = 30 * 60 * 20）
        long delay = 0L; // 初始延迟（0 表示立即执行）
        long period = 10 * 60 * 20; // 30 分钟的周期（单位：tick）

        // 使用 BukkitScheduler 调度定时任务
        new BukkitRunnable() {
            @Override
            public void run() {

//                for (String string : arrayList) {
//                    if (name ==string){
//                        arrayList.remove(string);
//                    }
//                }
                arrayList.remove(name);
              //  System.out.println("arrayList");
                for (String string : arrayList) {
                    System.out.println(string);
                }
                getupdatenames(name);
                // 在此处编写每 30 分钟执行的任务
               // Bukkit.getLogger().info("执行了定时任务！");  // 打印一条消息到服务器控制台
                // 你可以在这里添加其他的操作，例如：
                // 1. 给玩家发放奖励
                // 2. 清理物品
                // 3. 更新某些系统状态等
            }
        }.runTaskTimer(this, period, period);  // 调度任务
    }


    public static FileConfiguration config;
    public static DuckPokemonBingo instance;
    public static Logger logger;

    DuckPokemonBingo InventoryGames = this;

//    @Override
//    public void onEnable() {
//        // Plugin startup logic
//
//        saveDefaultConfig();
//        config = getConfig();
//        instance=this;
//        logger=getLogger();
//        configfile=new File(getDataFolder(),"config.yml");
//
//
//        Minesweeper.init();
//        Bukkit.getPluginManager().registerEvents(new Minesweeper(),this);
//        //JGG.init();
//       // Bukkit.getPluginManager().registerEvents(new JGG(),this);
//
//    }
//
//    @Override
//    public void onDisable() {
//        // Plugin shutdown logic
//    }
//
//
//    public static ItemStack getItemStack (String key){
//
//        ItemStack stack;
//        String ma = config.getString(key+".item");
//        if(ma.contains(":"))
//            stack = new ItemStack(
//                    Material.getMaterial(ma.substring(0,ma.indexOf(":"))),1,
//                    Short.valueOf(ma.substring(ma.indexOf(":")+1))
//            );
//        else
//            stack = new ItemStack(Material.getMaterial(ma),1);
//
//
//        if(config.contains(key+".name")){
//            ItemMeta a = stack.getItemMeta();
//            a.setDisplayName(config.getString(key+".name"));
//            stack.setItemMeta(a);
//        }
//
//        return stack.clone();
//
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        System.out.println(1);
//        if(sender instanceof Player) {
//            System.out.println(2);
//            if (command.getName().equalsIgnoreCase("ms"))
//            {
//                System.out.println(3);
//                if(args.length>0){
//                    System.out.println(4);
//                    if(args[0].equalsIgnoreCase("reset") && Minesweeper.saolei.containsKey(sender.getName()))
//                    {
//                        Minesweeper.saolei.remove(sender.getName());
//                    }
//                }
//
//                Minesweeper.start((Player) sender);
//                return true;
//            }
//            else if(command.getName().equalsIgnoreCase("jgg")){
//               // JGG.start((Player) sender);
//                return true;
//            }
//        }
//        return super.onCommand(sender, command, label, args);
//    }
//

    private static File configfile;
    public static void saveCFG(){
        try {
            config.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//}
}
