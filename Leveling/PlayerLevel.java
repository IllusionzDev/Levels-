public class PlayerLevel {
    public static Map<UUID, Integer> playerLevel = Maps.newHashMap();
    public static Map<UUID, Integer> playerXP = Maps.newHashMap();
  
    private Player ply;
    private int xp = 0;
    private int level = 1;

    public PlayerLevel(Player ply)
    {
        this.ply = ply;

        if (playerLevel.containsKey(ply.getUniqueId()) && playerXP.containsKey(ply.getUniqueId()))
        {
            this.level = playerLevel.get(ply.getUniqueId());
            this.xp = playerXP.get(ply.getUniqueId());
        }
    }

    public static Integer getMaxLevel()
    {
        return Main.config.getConfig().getInt("leveling.maxLevel");
    }

    public static double getXPForNextLevel(Integer level)
    {
        return (Main.config.getConfig().getInt("leveling.baseXP") * level);
    }

    public Integer getXP()
    {
        return xp;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void addXP(Integer xpAmount)
    {
        if (isMaxLevel())
        {
            Main.console.Output("Error", true, ply.getName() + " has already reached the max level. (" + Main.config.getConfig().getInt("leveling.maxLevel") + ")");
            return;
        }

        xp += xpAmount;
        int nextLevel = (int) getXPForNextLevel(getLevel());

        while (xp >= nextLevel)
        {
            level += 1;
            xp -= nextLevel;
            playerLevel.put(ply.getUniqueId(), level);
            Main.console.Output("Success", true, ply.getName() + " has just reached level " + level + "!");
        }
        playerXP.put(ply.getUniqueId(), xp);
    }

    public boolean isMaxLevel()
    {
        return getLevel().equals(Main.config.getConfig().getInt("leveling.maxLevel"));
    }
}
