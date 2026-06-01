package com.wsdev.mineUtils.Commands.Report.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Weslei
 */
public class Report
{
    private UUID playerId;
    private String description;
    private LocalDateTime reportedAt;

    /**
     * Constructor
     * @param playerId
     * @param description
     * @param reportedAt
     */
    public Report( UUID playerId, String description, LocalDateTime reportedAt )
    {
        this.playerId = playerId;
        this.description = description;
        this.reportedAt = reportedAt;
    }

    /**
     *
     * @return
     */
    public UUID getPlayerId()
    {
        return playerId;
    }

    /**
     *
     * @param playerId
     */
    public void setPlayerId( UUID playerId )
    {
        this.playerId = playerId;
    }

    /**
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getReportedAt()
    {
        return reportedAt;
    }

    /**
     *
     * @param reportedAt
     */
    public void setReportedAt( LocalDateTime reportedAt )
    {
        this.reportedAt = reportedAt;
    }
}
