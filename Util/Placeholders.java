public class Placeholders extends PlaceholderExpansion {
    private Main plugin;

    public Placeholders(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "levels";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player ply, String params) {
        if (ply == null)
            return "";

        if (params.equals("plylevel")) // %levels_plylevel%
        {
            return String.valueOf(LevelGetter.getPlayerLevel(ply).getLevel());
        }

        if (params.equals("maxlevel"))
        {
            return String.valueOf(PlayerLevel.getMaxLevel());
        }

        return null;
    }
}
