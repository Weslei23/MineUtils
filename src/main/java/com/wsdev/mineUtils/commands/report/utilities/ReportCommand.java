package com.wsdev.mineUtils.commands.report.utilities;

import com.wsdev.mineUtils.MineUtils;
import com.wsdev.mineUtils.commands.report.data.Report;
import com.wsdev.mineUtils.commands.report.manager.ReportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportCommand implements CommandExecutor
{
    private ReportManager reportManager;

    public ReportCommand( ReportManager reportManager )
    {
        this.reportManager = reportManager;
    }

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
            reportManager.addReport( report );
        }
        catch ( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return true;
    }
}
