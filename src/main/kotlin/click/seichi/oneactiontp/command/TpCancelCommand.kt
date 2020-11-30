package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.collection.PendingRequest
import click.seichi.oneactiontp.collection.TeleportThereRequest
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TpCancelCommand: TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
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

        if (requestExists(cmdSender, reqSender)) {
            // TODO 申請が申請者によって取り消されましたを両方に送る
            TeleportThereRequest.remove(cmdSender, reqSender)
            PendingRequest.remove(cmdSender, reqSender)
        } else {
            cmdSender.sendMessage(Message.noPendingTppReq)
        }
        // TODO tpphereも

        return true
    }

    private fun requestExists(sender: Player, receiver: Player) =
            TeleportThereRequest.requestExists(sender, receiver) || PendingRequest.requestExists(sender, receiver)
}