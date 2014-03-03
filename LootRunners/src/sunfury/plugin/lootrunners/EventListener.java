package sunfury.plugin.lootrunners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class EventListener implements Listener {
    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {
        if (Main.getInstance().getChestSetters().containsKey(event.getPlayer())) {
            if (event.getBlock().getType() == Material.CHEST) {
                List<String> list = Main.getInstance().getConfig().getStringList("LootRunners.Templates.Template" + Main.getInstance().getChestSetters().get(event.getPlayer()));
                for (String string: list) {
                    if (string.equals(event.getBlock().getX() + ", " + event.getBlock().getY() + ", " + event.getBlock().getZ())) {
                        event.getPlayer().sendMessage(ChatColor.RED + "A chest has already been added at this location!");
                    } else {
                        list.add(string);
                        Main.getInstance().getConfig().set("LootRunners.Templates.Template" + Main.getInstance().getChestSetters().get(event.getPlayer()), list);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak (BlockBreakEvent event) {
        if (Main.getInstance().getChestSetters().containsKey(event.getPlayer())) {
            if (event.getBlock().getType() == Material.CHEST) {
                List<String> list = Main.getInstance().getConfig().getStringList("LootRunners.Templates.Template" + Main.getInstance().getChestSetters().get(event.getPlayer()));
                for (String string: list) {
                    if (string.equals(event.getBlock().getX() + ", " + event.getBlock().getY() + ", " + event.getBlock().getZ())) {
                        list.remove(string);
                        Main.getInstance().getConfig().set("LootRunners.Templates.Template" + Main.getInstance().getChestSetters().get(event.getPlayer()), list);
                        break;
                    }
                }
            }
        }
    }
}
