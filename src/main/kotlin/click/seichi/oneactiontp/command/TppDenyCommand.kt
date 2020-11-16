package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.data.TeleportRequest
import click.seichi.oneactiontp.data.TeleportRequest.hasAlreadySent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TppDenyCommand : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        // TODO 自分に向けてリクエストを出しているプレイヤーのみサジェスト
        return mutableListOf()
    }

    override fun onCommand(cmdSender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (cmdSender !is Player) {
            cmdSender.sendMessage(Message.playerOnlyExeCmd)
            return true
        } else if (args.size != 1) {
            cmdSender.sendMessage(Message.invalidArgs)
            return false
        }

        val reqSender = Bukkit.getPlayer(args[0]) ?: run {
            cmdSender.sendMessage(Message.playerNotFound)
            return true
        }

        if (!hasAlreadySent(reqSender, cmdSender)) {
            cmdSender.sendMessage(Message.noPendingTppReq)
            return true
        }

        TeleportRequest.remove(reqSender, cmdSender)
        reqSender.sendMessage(Message.senderDeniedTppReq)
        cmdSender.sendMessage(Message.receiverDenyTppReq)

        return true
    }
}