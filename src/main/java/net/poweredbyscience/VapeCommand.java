package net.poweredbyscience;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by lax on 9/23/16.
 */
public class VapeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You are too young to vape, m8. git a body first.");
        }
        if(!sender.hasPermission("vape.vape")) {
            sender.sendMessage("You may not vape");
            return false;
        }
        Player p = (Player) sender;
        if (p.getItemInHand().getType() == Material.POTION) {
            Vape.instance.smokeVape(p);
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cPlease acquire a potion juice first!"));
        }
        return false;
    }
}
