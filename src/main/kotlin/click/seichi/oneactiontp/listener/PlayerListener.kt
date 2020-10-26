package click.seichi.oneactiontp.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoined(event: PlayerJoinEvent) {
        val player = event.player
        val playerUuid = player.uniqueId

    }
}