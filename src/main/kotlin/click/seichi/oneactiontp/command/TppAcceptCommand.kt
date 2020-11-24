package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.OneActionTp.Companion.PLUGIN
import click.seichi.oneactiontp.config.Config
import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.cpllection.PendingRequest
import click.seichi.oneactiontp.cpllection.TeleportRequest
import click.seichi.oneactiontp.util.runTaskTimer
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class TppAcceptCommand: TabExecutor {
    private var taskId = 0
    private var seconds = Config.secondsUntilTp

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        return mutableListOf()
    }

    @Suppress("UNREACHABLE_CODE")
    override fun onCommand(cmdSender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        TODO("It is a little difficult for me to understand implementation of this command because I hardly have time!")

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
        PendingRequest.add(reqSender, cmdSender)

        reqSender.sendMessage(Message.senderAcceptedTppReq)
        cmdSender.sendMessage(Message.receiverAcceptTppReq)

        this.taskId = runTaskTimer(0L, 20L) {
            if (this.seconds >= 1) {
                if (PendingRequest.requestExists(reqSender, cmdSender)) {
                    this.seconds--
                }
                else {
                    PLUGIN.server.scheduler.cancelTask(this.taskId)
                    // TODO Player-moved-before-tp message
                }
            } else {
                PLUGIN.server.scheduler.cancelTask(this.taskId)
                PendingRequest.remove(reqSender, cmdSender)
                reqSender.teleport(cmdSender.location)
            }
        }.taskId

        return true
    }
}