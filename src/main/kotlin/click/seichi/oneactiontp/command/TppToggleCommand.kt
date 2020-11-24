package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.cpllection.RequestsLimitation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TppToggleCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Message.playerOnlyExeCmd)
            return true
        } else if (args.isNotEmpty()) {
            sender.sendMessage(Message.invalidArgs)
            return false
        }

        RequestsLimitation.toggleReqLimit(sender)

        return true
    }
}