package com.wsdev.mineUtils.db;

import com.wsdev.mineUtils.MineUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    public static Connection getDatabaseConnection()
    {
        final String driver = MineUtils.getPluginConfig().getString( "storage-method" );
        final String database = MineUtils.getPluginConfig().getString( "database.name" );
        final String port = MineUtils.getPluginConfig().getString( "database.port" );
        final String hostIP = MineUtils.getPluginConfig().getString( "database.host" );
        final String user = MineUtils.getPluginConfig().getString( "database.user" );
        final String password = MineUtils.getPluginConfig().getString( "database.password" );

        final String url = "jdbc:" + driver + "://" + hostIP + ":" + port + "/" + database;

        try
        {
            if ( "postgresql".equalsIgnoreCase( driver ) )
            {
                Class.forName( "org.postgresql.Driver" );
            }

            return DriverManager.getConnection( url, user, password );
        }
        catch ( SQLException | ClassNotFoundException e )
        {
            throw new RuntimeException( e );
        }
    }
}