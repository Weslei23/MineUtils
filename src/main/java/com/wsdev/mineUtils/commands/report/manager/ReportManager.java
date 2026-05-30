package com.wsdev.mineUtils.commands.report.manager;

import com.wsdev.mineUtils.commands.report.data.Report;
import com.wsdev.mineUtils.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportManager
{
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public void addReport( Report report ) throws SQLException
    {
        String sql = "INSERT INTO tb_reports (playerId, description, reportedAt) VALUES (?,?,?)";

        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) )
        {

            preparedStatement.setString( 1, report.getPlayerId().toString() );
            preparedStatement.setString( 2, report.getDescription() );
            preparedStatement.setTimestamp( 3, java.sql.Timestamp.valueOf( report.getReportedAt() ) );

            preparedStatement.executeUpdate();
        }
    }

    public Report getReport( String playerId ) throws SQLException
    {
        String sql = "SELECT playerId, description, reportedAt FROM tb_report WHERE playerId = ?";

        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) )
        {
//            preparedStatement.setString( , playerId );


        }
    }
}
