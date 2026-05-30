package com.wsdev.mineUtils.commands.report.utilities;

import com.wsdev.mineUtils.commands.report.data.Report;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ReportCommand implements CommandExecutor
{
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args )
    {
        if ( !( sender instanceof Player player ) )
        {
            sender.sendMessage( "§c[Info]: Voce não possui permissão para executar este comando." );
            return true;
        }
        boolean hasPermission  = false;

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

        return true;
    }
}
