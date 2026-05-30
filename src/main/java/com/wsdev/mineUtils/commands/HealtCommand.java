package com.wsdev.mineUtils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealtCommand implements CommandExecutor
{
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args )
    {
        if( !( sender instanceof Player player ) )
        {
            sender.sendMessage( "§c[Info]: Voce não possui permissão para executar este comando." );
            return true;
        }

        if( !player.hasPermission( "ws.healt" ) )
        {
            player.sendMessage("§c[Info]: Voce não possui permissão para executar este comando.");
            return true;
        }

        player.setHealth( 20.0f );

        return true;
    }
}
