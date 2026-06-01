package com.wsdev.mineUtils.Database;

import com.wsdev.mineUtils.MineUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Weslei
 */
public class Database
{
    private static Database instancia = null;
    private Connection connection = null;

    /**
     * constructor
     */
    public Database()
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

            connection = DriverManager.getConnection( url, user, password );
        }
        catch ( SQLException | ClassNotFoundException e )
        {
            throw new RuntimeException( e );
        }
    }

    /**
     * getInstance
     * @return
     */
    public static Database getInstance()
    {
        if (instancia == null)
        {
            instancia = new Database();
        }
        return instancia;
    }

    /**
     * Get connection
     * @return
     */
    public Connection getConnection()
    {
        if ( connection == null )
        {
            throw new RuntimeException( "Connection is null!" );
        }
        return connection;
    }

    /**
     * Close connection
     */
    public void closeConnection()
    {
        try
        {
            connection.close();
            instancia = null;
            connection = null;
        }
        catch ( Exception e )
        {
            System.err.println( e );
        }
    }
}