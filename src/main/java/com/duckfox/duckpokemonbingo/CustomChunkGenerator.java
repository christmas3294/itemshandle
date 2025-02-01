package com.duckfox.duckpokemonbingo;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.List;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        //System.out.println("可以在这里自定义区块的地形生成逻辑");

       // System.out.println(1111);
        // 可以在这里自定义区块的地形生成逻辑
       // ChunkData chunkData = createChunkData(world);
        System.out.println("可以在这里自定义区块的地形生成逻辑");




        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkGenerator.ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                int i1 = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) + 1) * 15D + 50D);
                // 获取当前生物群系
                Biome biomes = world.getBiome(chunkX * 16 + x, chunkZ * 16 + z);
               // if (biomes == Biome.PLAINS){


                chunk.setRegion(0,0,0,chunkX * 16+x,100,chunkZ * 16+z, Material.ACACIA_DOOR);
                    //world.setBiome( + x, chunkZ * 16 + z, Biome.MUSHROOM_ISLAND);
              //  }
               // ChunkData chunkData = createChunkData(world);
//                chunk.setBlock(x, currentHeight, z, Material.);
//                if (random.nextInt(110) == 1)
//                    world.generateTree(world.getBlockAt(x, currentHeight, z).getLocation(), TreeType.TREE); // Does not let world generate
//                chunk.setBlock(x, currentHeight - 1, z, Material.DIRT);
//                for (int i = currentHeight - 2; i > 0; i--)
//                    chunk.setBlock(x, i, z, Material.STONE);
//                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        chunk.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        chunk.setRegion(0, 1, 0, 16, 2, 16, Material.GRASS);
        return chunk;







      //  return chunkData;
    }


    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        int x = 0 , z = 0;
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }



}
