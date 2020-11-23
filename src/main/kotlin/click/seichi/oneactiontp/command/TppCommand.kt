package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.data.RequestsLimitation.readReqLimitState
import click.seichi.oneactiontp.data.TeleportRequest
import click.seichi.oneactiontp.util.runTaskLaterAsynchronously
import click.seichi.oneactiontp.util.sendMsgs
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TppCommand : TabExecutor {
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

        // TODO コマンド実行にintervalを作る

        if (TeleportRequest.hasSentRequest(sender)) {
            sender.sendMessage(Message.hasSentReq)
            return true
        }

        TeleportRequest.add(sender, receiver)

        val hoverAcceptText = TextComponent(Message.clickToAccept).apply {
            this.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(Message.hoverToAccept).create())
            this.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppaccept ${sender.name}")
        }
        val hoverDenyText = TextComponent(Message.clickToDeny).apply {
            this.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(Message.hoverToDeny).create())
            this.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppdeny ${sender.name}")
        }
        val spaceText = TextComponent("     ")
        Message.reqHasArrived.forEach { receiver.sendMessage(it) }
        receiver.sendMsgs(spaceText, hoverAcceptText, spaceText, hoverDenyText)
        sender.sendMessage(Message.reqHasBeenSent)

        // TODO 無効になるまでの時間を設定できるようにする（秒単位）
        runTaskLaterAsynchronously(20 * 120) {
            if (TeleportRequest.hasAlreadySent(sender, receiver)) {
                TeleportRequest.remove(sender, receiver)
                sender.sendMessage(Message.reqHasExpired)
                receiver.sendMessage(Message.reqHasExpired)
            }
        }

        return true
    }
}