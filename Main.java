public final class Main extends JavaPlugin implements Listener {
    public static Logger console = null;
    public static Main inst = null;
    public static CreateConfig config, blockConfig, levelConfig;

    @Override
    public void onEnable() {
        inst = this;
        console = new Logger();
        Handling.loadClasses();

        config = new CreateConfig("plugins/Levels+", "config.yml", inst);
        blockConfig = new CreateConfig("plugins/Levels+", "blocks.yml", inst);
        levelConfig = new CreateConfig("plugins/Levels+", "levels.yml", inst);

        console.Output("Normal", false, " ");
        console.Output("Normal", false, "-----------------------------------");
        console.Output("Normal", false, " ");
        console.Output("Success", false, "Levels+ has successfully started!");
        console.Output("Normal", false, " ");
        console.Output("Normal", false, Info.getInfo());
        console.Output("Normal", false, " ");
        console.Output("Normal", false, "-----------------------------------");
        console.Output("Normal", false, " ");

        getServer().getPluginManager().registerEvents(inst, this);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            console.Output("Warning", true, "PlaceholderAPI is not installed!");
        else
            {
                new Placeholders(inst);
            }

        Handling.configData.startUpData();
    }

    @Override
    public void onDisable() {
        Handling.configData.disableData();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player ply = e.getPlayer();
        LevelGetter.getPlayerLevel(ply);
        String joinText = ply.getName() + " [%levels_plylevel%]";
        joinText = PlaceholderAPI.setPlaceholders(ply, joinText);
      
        e.setJoinMessage(joinText);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player ply = e.getPlayer();
        Block block = e.getBlock();
        Material blockMaterial = block.getBlockData().getMaterial();
      
        /* 25 * (MaxLevel / Level) / (BLOCK_VALUE / 2) / 2 = 6.25 XP if the block value was 100 */
        if (blockConfig.getConfig().getInt("blocks." + blockMaterial) > 0 && !e.isCancelled())
        {
            LevelGetter.getPlayerLevel(ply).addXP(blockConfig.getConfig().getInt("blocks." + blockMaterial));
        }
    }
}
