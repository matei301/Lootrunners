package sunfury.plugin.lootrunners;

import java.util.ArrayList;

public class ArenaManager {
    //Makes ArenaManager class available from all other classes
    private static ArenaManager instance = new ArenaManager();
    public static ArenaManager getInstance() {
        return instance;
    }

    private ArrayList<Arena> arenas = new ArrayList <Arena> ();

    //Goes through all the IDs from the config and creates arenas from the information specified in the config
    public void setup() {
        for (String StringID: Main.getInstance().getConfig().getStringList("IDs")) {
            int id = Integer.parseInt(StringID);
            createArena(id);
        }
    }

    //Method used to reset an arena
    public void resetArena(Arena arena) {
        int tempID = arena.getID();
        arenas.remove(arena);
        createArena(tempID);
    }

    //Get a list of all arenas
    public ArrayList <Arena> getArenas() {
        return arenas;
    }

    //Check if an arena exists
    public boolean arenaExists(int id) {
        for (Arena arena: getArenas()) {
            if (arena.getID() == id) {
                return true;
            }
        }
        return false;
    }

    //Method for creating an arena with a specific id
    public void createArena(int id) {
        if (!arenaExists(id)) {
            arenas.add(new Arena(id));
        }
    }

    //Get an arena from a specific id
    public Arena getArena(int id) {
        for (Arena arena: getArenas()) {
            if (arena.getID() == id) {
                return arena;
            }
        }
        return null;
    }
}
