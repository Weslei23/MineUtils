package com.wsdev.mineUtils.commands.report.data;

import java.time.LocalDateTime;
import java.util.UUID;

public class Report
{
    private UUID playerId;
    private String description;
    private LocalDateTime reportedAt;

    public Report( UUID playerId, String description, LocalDateTime reportedAt )
    {
        this.playerId = playerId;
        this.description = description;
        this.reportedAt = reportedAt;
    }

    public UUID getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId( UUID playerId )
    {
        this.playerId = playerId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public LocalDateTime getReportedAt()
    {
        return reportedAt;
    }

    public void setReportedAt( LocalDateTime reportedAt )
    {
        this.reportedAt = reportedAt;
    }
}
