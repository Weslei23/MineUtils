package com.wsdev.mineUtils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    public static Connection getDatabaseConnection() throws SQLException
    {
        final String url = "jdbc:postgresql://localhost:5432/novo_database";
        final String user = "postgres";
        final String password = "12345678";

        try
        {
            return DriverManager.getConnection( url, user, password );
        }
        catch ( SQLException e )
        {
            throw new RuntimeException( e );
        }
    }
}
