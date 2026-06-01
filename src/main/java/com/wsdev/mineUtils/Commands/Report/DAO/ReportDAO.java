package com.wsdev.mineUtils.Commands.Report.DAO;

import com.wsdev.mineUtils.Commands.Report.Data.Report;
import com.wsdev.mineUtils.Utils.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Weslei
 */
public class ReportDAO
{
    private Connection connection;

    public ReportDAO()
    {
        connection = ApplicationContext.getInstance().getConnection();
    }

    /**
     * Insert a new report in to table
     * @param report
     * @throws SQLException
     */
    public void addReport( Report report ) throws SQLException
    {
        String sql = "INSERT INTO tb_reports (playerId, description, reportedAt) VALUES (?,?,?)";

        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) )
        {

            preparedStatement.setObject( 1, report.getPlayerId() );
            preparedStatement.setString( 2, report.getDescription() );
            preparedStatement.setTimestamp( 3, java.sql.Timestamp.valueOf( report.getReportedAt() ) );

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Get the player with id
     * @param playerId
     * @return
     * @throws SQLException
     */
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
                       resultSet.getObject( "playerId", UUID.class ),
                       resultSet.getString( "description" ),
                       resultSet.getTimestamp( "reportedAt" ).toLocalDateTime() );
            }
        }
        return null;
    }

    /**
     * Get all reports
     * @return
     * @throws SQLException
     */
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
                        resultSet.getObject( "playerId", UUID.class ),
                        resultSet.getString( "description" ),
                        resultSet.getTimestamp( "reportedAt" ).toLocalDateTime() ) );
            }
        }
        return reports;
    }
}
