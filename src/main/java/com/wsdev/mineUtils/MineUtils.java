package com.wsdev.mineUtils;

import com.wsdev.mineUtils.Commands.Feed.FeedCommand;
import com.wsdev.mineUtils.Commands.Health.HealthCommand;
import com.wsdev.mineUtils.Commands.Report.Commands.ReportsListCommand;
import com.wsdev.mineUtils.Commands.Report.Commands.ReportCommand;
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
        if( getCommand( "health" ) != null ) getCommand( "health" ).setExecutor( new HealthCommand() );
        if( getCommand( "report" ) != null ) getCommand( "report" ).setExecutor( new ReportCommand() );
        if( getCommand( "reports" ) != null ) getCommand( "reports" ).setExecutor( new ReportsListCommand() );
    }
}
