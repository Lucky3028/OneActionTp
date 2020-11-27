package click.seichi.oneactiontp.collection

import click.seichi.oneactiontp.config.Message
import org.bukkit.entity.Player

/**
 * 自分宛ての申請をすべて許可するか拒否するかのトグル状況を保持する`map`を操作するobject。
 */
object RequestsLimitation {
    private val flagMap = mutableMapOf<Player, Boolean>()

    fun readReqLimitState(player: Player) = flagMap[player] ?: false

    fun toggleReqLimit(player: Player) {
        val current = readReqLimitState(player)
        val new = !current

        flagMap[player] = new

        if (new) player.sendMessage(Message.denyAllRequests)
        else player.sendMessage(Message.allowAllRequests)
    }
}