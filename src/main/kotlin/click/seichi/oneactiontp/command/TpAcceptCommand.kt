package click.seichi.oneactiontp.command

import click.seichi.oneactiontp.collection.PendingRequest
import click.seichi.oneactiontp.collection.TeleportThereRequest
import click.seichi.oneactiontp.config.Config
import click.seichi.oneactiontp.config.Message
import click.seichi.oneactiontp.util.runTaskTimer
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TpAcceptCommand: TabExecutor {
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

        if (!TeleportThereRequest.requestExists(reqSender, cmdSender)) {
            cmdSender.sendMessage(Message.noPendingTppReq)
            return true
        }
        // TODO tpphereコマンドも確認する

        TeleportThereRequest.remove(reqSender, cmdSender)
        PendingRequest.add(reqSender, cmdSender)

        reqSender.sendMessage(Message.senderAcceptedTppReq)
        cmdSender.sendMessage(Message.receiverAcceptTppReq)

        runTaskTimer(0L, 20L, object : BukkitRunnable() {
            private var seconds = Config.secondsUntilTp

            override fun run() {
                if (--seconds > 0) {
                    if (!PendingRequest.requestExists(reqSender, cmdSender)) {
                        this.cancel()
                        // TODO Player-moved-before-tp message
                    }
                } else {
                    this.cancel()

                    PendingRequest.remove(reqSender, cmdSender)
                    // TODO currently-teleport message
                    reqSender.teleport(cmdSender.location)
                }
            }
        })

        return true
    }
}