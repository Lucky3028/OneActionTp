package click.seichi.oneactiontp.util

import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

/**
 * [CommandSender.sendMessage]の引数に[ChatColor.RED]を適用してくれる関数。
 */
fun CommandSender.sendErrMsg(msg: String) = this.sendMessage("${ChatColor.RED}$msg")

/**
 * [CommandSender.sendErrMsg]の引数に配列を指定できる関数。
 */
fun CommandSender.sendErrMsgs(vararg msgs: String) = msgs.forEach { this.sendMessage("${ChatColor.RED}$it") }

/**
 * [CommandSender.Spigot.sendMessage]を省略して利用できる関数。
 */
fun CommandSender.sendMsg(msg: BaseComponent) = this.spigot().sendMessage(msg)

/**
 * [CommandSender.sendMsg]の引数に配列を指定できる関数。
 */
fun CommandSender.sendMsgs(vararg msgs: BaseComponent) = msgs.forEach { this.spigot().sendMessage(it) }