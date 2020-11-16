package click.seichi.oneactiontp.util

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * [CommandSender.sendMessage]を省略して利用できる関数。
 * [Player]でも利用可。
 *
 * 自動的に[convertColorCode]が適用される。
 */
fun CommandSender.sendMsg(msg: String) = this.sendMessage(convertColorCode(msg))

/**
 * [CommandSender.sendMsg]の引数に配列を指定できる関数。
 *
 * 自動的に[convertColorCode]が適用される。
 */
fun CommandSender.sendMsgs(vararg msgs: String) = msgs.forEach { this.sendMessage(convertColorCode(it)) }

/**
 * [CommandSender.Spigot.sendMessage]を省略して利用できる関数。
 * [Player]でも利用可。
 *
 * 自動的に[convertColorCode]が適用される。
 */
fun CommandSender.sendMsg(msg: BaseComponent) = this.spigot().sendMessage(msg)

/**
 * [CommandSender.sendMsg]の引数に配列を指定できる関数。
 *
 * 自動的に[convertColorCode]が適用される。
 */
fun CommandSender.sendMsgs(vararg msgs: BaseComponent) = msgs.forEach { this.spigot().sendMessage(it) }

/**
 * カラーコード指定のためのアンパサンドを装飾記号へ変換する関数。
 * @see [ChatColor.translateAlternateColorCodes]
 */
fun convertColorCode(msg: String): String = ChatColor.translateAlternateColorCodes('&', msg)