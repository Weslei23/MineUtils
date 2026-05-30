package com.wsdev.mineUtils;

import com.wsdev.mineUtils.commands.feed.FeedCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineUtils extends JavaPlugin
{
    private static FileConfiguration config;

    @Override
    public void onEnable()
    {
        getLogger().info( "Plugin ativado com sucesso!" );

        saveDefaultConfig();

        config = getConfig();

        //Chama os comandos.
        registerCommands();
    }

    @Override
    public void onDisable()
    {
        getLogger().info( "Plugin desativado com sucesso!" );
    }

    public static FileConfiguration getPluginConfig()
    {
        return config;
    }

    public void registerCommands()
    {
        if( getCommand( "feed" ) != null ) getCommand( "feed" ).setExecutor( new FeedCommand() );
//        if( getCommand( "gm" ) != null ) getCommand( "gm" ).setExecutor( new GmCommand() );
//        if( getCommand( "site" ) != null ) getCommand( "site" ).setExecutor( new SiteCommand() );
//        if( getCommand( "bau" ) != null ) getCommand( "bau" ).setExecutor( new ChestCommand() );
//        if( getCommand( "tpa" ) != null ) getCommand( "tpa" ).setExecutor( new TpCommand() );
    }
}
