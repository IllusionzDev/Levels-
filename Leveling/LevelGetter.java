public class LevelGetter {
    public static Map<UUID, PlayerLevel> playerData = Maps.newHashMap();

    public static final PlayerLevel getPlayerLevel(Player ply)
    {
        PlayerLevel getInst = playerData.get(ply.getUniqueId());

        if (getInst == null)
        {
            getInst = new PlayerLevel(ply);
            playerData.put(ply.getUniqueId(), getInst);
            Main.console.Output("Success", true, "Added " + ply.getName() + " to the leveling system.");
        }
        return getInst;
    }
}
