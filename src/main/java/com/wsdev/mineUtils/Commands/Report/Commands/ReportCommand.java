package com.wsdev.mineUtils.Commands.Report.Commands;

import com.wsdev.mineUtils.MineUtils;
import com.wsdev.mineUtils.Commands.Report.Data.Report;
import com.wsdev.mineUtils.Commands.Report.DAO.ReportDAO;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Weslei
 */
public class ReportCommand implements CommandExecutor
{
    /**
     * Build command
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args )
    {
        if ( !( sender instanceof Player player ) )
        {
            sender.sendMessage( "§c[Info]: Apenas jogadores podem executar este comando." );
            return true;
        }
        boolean hasPermission  = MineUtils.getPluginConfig().getBoolean( "Report.hasPermission" );

        if ( hasPermission && !player.hasPermission( "ws.report" ) )
        {
            player.sendMessage("§c[Info]: Voce não possui permissão para executar este comando." );
            return true;
        }

        if ( args.length != 2 )
        {
            player.sendMessage( "§c[Info]: Comando digitado incorretamente. Utilize /report <jogador> <motivo>." );
            return true;
        }

        Player reportedPlayer = Bukkit.getPlayer( args[0] );

        if ( reportedPlayer == null )
        {
            player.sendMessage( "§c[Info]: Jogador reportado não encontrado." );
            return true;
        }

        String description = args[1];

        if ( description.isBlank() )
        {
            player.sendMessage( "§c[Info]: Insira um motivo da denuncia." );
        }

        Report report = new Report(
                reportedPlayer.getUniqueId(),
                description,
                LocalDateTime.now()
        );

        try
        {
            ReportDAO reportDAO = new ReportDAO();
            reportDAO.addReport( report );
        }
        catch ( SQLException e )
        {
            throw new RuntimeException( e );
        }

        player.sendMessage( "§c[Info]: §7Reportado com sucesso." );
        return true;
    }
}
