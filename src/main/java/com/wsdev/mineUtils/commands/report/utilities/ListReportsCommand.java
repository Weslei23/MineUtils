package com.wsdev.mineUtils.commands.report.utilities;

import com.wsdev.mineUtils.MineUtils;
import com.wsdev.mineUtils.commands.report.data.Report;
import com.wsdev.mineUtils.commands.report.manager.ReportManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ListReportsCommand implements CommandExecutor
{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args )
    {
        if ( !( sender instanceof Player player ) )
        {
            sender.sendMessage( "§c[Info]: Apenas jogadores podem executar este comando." );
            return true;
        }

        boolean hasPermission = MineUtils.getPluginConfig().getBoolean( "Reports.HasPermission" );

        if ( hasPermission &&  !player.hasPermission( "ws.reports" ) )
        {
            player.sendMessage( "§c[Info]: Voce não possui permissão para executar este comando." );
            return true;
        }

        ReportManager reportManager = new ReportManager();

        try
        {
            for( Report report : reportManager.getReports() )
            {
                OfflinePlayer reportedPlayer = Bukkit.getOfflinePlayer( report.getPlayerId() );

                if( reportedPlayer != null )
                {
                    player.sendMessage( "§c[Info]: Jogadores reportados: \n"
                            + "§cNome: §7" + reportedPlayer.getName() + "\n"
                            + "§cMotivo: §7" + report.getDescription() + "\n"
                            + "§cReportado em: §7" + formatter.format( report.getReportedAt() ) );
                }
            }
        }
        catch ( SQLException e )
        {
            throw new RuntimeException( e );
        }

        return true;
    }
}
