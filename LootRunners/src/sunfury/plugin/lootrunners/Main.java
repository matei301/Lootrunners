package sunfury.plugin.lootrunners;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    //Makes Main class available from all other classes
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    private HashMap<Player, Integer> chestSetters = new HashMap<Player, Integer> ();

    public HashMap<Player, Integer> getChestSetters() {
        return chestSetters;
    }

    public void onEnable() {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        //Runs the setup method to create the arenas
        ArenaManager.getInstance().setup();
    }

    public void onDisable() {

    }

    //Basic command structure
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("lr") || command.getName().equalsIgnoreCase("lootrunners")) {
            if (player.hasPermission("lootrunners.commands")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Syntax error!");
                } else if (args[0].equals("chest")) {
                    if (player.hasPermission("lootrunners.mapmaker")) {
                        if (chestSetters.containsKey(player)) {
                            player.sendMessage("Chest Setting toggled " + ChatColor.RED + "OFF");
                            chestSetters.remove(player);
                        } else {
                            player.sendMessage("Chest Setting toggled " + ChatColor.GREEN + "ON");
                            chestSetters.put(player, Integer.parseInt(args[1]));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to do that!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Syntax error!");
                }
            }
        }
        return true;
    }
}
