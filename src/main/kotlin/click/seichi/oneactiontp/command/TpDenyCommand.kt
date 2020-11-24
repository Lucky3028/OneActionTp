package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.cpllection.TeleportRequest
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TpDenyCommand : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        return if (
                command.name.equals("tppdeny", true)
                && args.size == 1
                && sender is Player) {
            // TODO findBeenRequestedがEmptyの場合どうなる？
            TeleportRequest.findBeenRequested(sender).map { it.name }.toMutableList()
        } else mutableListOf()
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

        if (!TeleportRequest.requestExists(reqSender, cmdSender)) {
            cmdSender.sendMessage(Message.noPendingTppReq)
            return true
        }
        // TODO tpphereコマンドも確認する

        TeleportRequest.remove(reqSender, cmdSender)
        reqSender.sendMessage(Message.senderDeniedTppReq)
        cmdSender.sendMessage(Message.receiverDenyTppReq)

        return true
    }
}