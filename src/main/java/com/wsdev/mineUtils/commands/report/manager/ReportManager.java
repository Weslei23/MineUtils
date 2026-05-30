package com.wsdev.mineUtils.commands.report.manager;

import com.wsdev.mineUtils.commands.report.data.Report;
import com.wsdev.mineUtils.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        String sql = "SELECT playerId, description, reportedAt FROM tb_reports WHERE playerId = ?";

        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) )
        {

            preparedStatement.setString( 1 , playerId );

            ResultSet resultSet = preparedStatement.executeQuery();

            if( resultSet.next() )
            {
               return new Report(
                       UUID.fromString( "playerId" ),
                       resultSet.getString( "description" ),
                       resultSet.getTimestamp( "reportedAt" ).toLocalDateTime() );
            }
        }
        return null;
    }

    public List<Report> getReports() throws SQLException
    {
        String sql = "SELECT playerId, description, reportedAt FROM tb_reports";

        List<Report> reports = new ArrayList<>();

        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) )
        {
            ResultSet resultSet = preparedStatement.executeQuery();

            while( resultSet.next() )
            {
                reports.add( new Report(
                        UUID.fromString( "playerId" ),
                        resultSet.getString( "description" ),
                        resultSet.getTimestamp( "reportedAt" ).toLocalDateTime() ) );
            }
        }
        return reports;
    }
}
