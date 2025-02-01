package com.duckfox.duckpokemonbingo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SwitchCommand implements CommandExecutor {
    private DoubleJump plugin;

    public SwitchCommand(DoubleJump plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory gui = new GUIManager().createSwitchGUI(plugin, player);
            player.openInventory(gui);
            return true;
        }
        return false;
    }
}
