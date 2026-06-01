package com.wsdev.mineUtils.Utils;

import java.sql.Connection;

/**
 * @author Weslei
 */
public class ApplicationContext
{
    private static ApplicationContext instance;

    private Connection connection;

    private ApplicationContext() {};

    /**
     *
     * @return
     */
    public static ApplicationContext getInstance()
    {
        if ( instance == null )
        {
            instance = new ApplicationContext();
        }

        return instance;
    };

    /**
     *
     * @return
     */
    public Connection getConnection()
    {
        return connection;
    }

    /**
     *
     * @param conn
     */
    public void setConnection( Connection conn )
    {
        this.connection = conn;
    }
}
