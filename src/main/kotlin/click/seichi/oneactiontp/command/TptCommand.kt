package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.collection.RequestsLimitation.readReqLimitState
import click.seichi.oneactiontp.collection.TeleportThereRequest
import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.util.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TptCommand : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        return mutableListOf()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Message.playerOnlyExeCmd)
            return true
        } else if (args.size != 1) {
            sender.sendMessage(Message.invalidArgs)
            return false
        }

        val receiver = Bukkit.getPlayer(args[0])?.also{ receiver ->
            if (receiver == sender) {
                sender.sendMessage(Message.selfTeleport)
                return true
            } else if (readReqLimitState(receiver)) {
                sender.sendMessage(Message.denyingAllReqs)
                return true
            }
        } ?: run {
            sender.sendMessage(Message.playerNotFound)
            return true
        }

        // TODO mutedならsenderにrequest完了を通知するが、receiverにはrequestを表示せず、一定時間後にtpadenyする

        if (TeleportThereRequest.hasRequested(sender)) {
            sender.sendMessage(Message.hasSentReq)
            return true
        }

        TeleportThereRequest.add(sender, receiver)

        Message.reqHasArrived.forEach { receiver.sendMessage(it) }
        receiver.sendMsgs(spaceText, hoverAcceptText(sender.name), spaceText, hoverDenyText(sender.name))
        sender.sendMessage(Message.reqHasBeenSent)

        makeRequestExpired(sender, receiver, TeleportThereRequest)

        return true
    }
}