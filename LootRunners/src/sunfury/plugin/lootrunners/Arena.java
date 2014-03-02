package sunfury.plugin.lootrunners;

public class Arena {
    private Main plugin;
    private int id;

    public void Main(Main plugin) {
        this.plugin = plugin;
    }

    public Arena(int id) {
        this.id = id;
    }

    //Get this arenas ID
    public int getID() {
        return id;
    }
}
