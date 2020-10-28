package click.seichi.oneactiontp.data

import org.bukkit.ChatColor
import org.bukkit.entity.Player

object RequestsLimitation {
    private val flagMap = mutableMapOf<Player, Boolean>()

    fun readReqLimitState(player: Player) = flagMap[player] ?: false

    fun toggleReqLimit(player: Player) {
        val current = readReqLimitState(player)
        val new = !current

        flagMap[player] = new

        if (new) {
            player.sendMessage("${ChatColor.RED}OneActionTPのプレイヤー間テレポート申請をすべて拒否します。")
        } else {
            player.sendMessage("${ChatColor.GREEN}OneActionTPのプレイヤー間テレポート申請をすべて許可します。")
        }
    }
}