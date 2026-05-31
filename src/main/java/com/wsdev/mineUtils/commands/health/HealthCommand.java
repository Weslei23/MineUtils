package com.wsdev.mineUtils.commands.health;

import com.wsdev.mineUtils.MineUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealthCommand implements CommandExecutor
{
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args )
    {
        if( !( sender instanceof Player player ) )
        {
            sender.sendMessage( "§c[Info]: Apenas jogadores podem executar este comando." );
            return true;
        }

        boolean hasPermission = MineUtils.getPluginConfig().getBoolean( "Health.HasPermission" );

        if( hasPermission && !player.hasPermission( "ws.health" ) )
        {
            player.sendMessage("§c[Info]: Voce não possui permissão para executar este comando.");
            return true;
        }

        player.setHealth( 20.0f );
        player.setHealthScale( 20.0f );

        player.sendMessage( "§a[Info]: Fome restaurada com sucesso!" );

        return true;
    }
}
