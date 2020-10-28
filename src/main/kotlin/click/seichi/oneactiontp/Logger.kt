package click.seichi.oneactiontp

import org.bukkit.Bukkit

object Logger {
    private val logger = Bukkit.getServer().logger

    fun info(msg: String) = logger.info(msg)

    fun warn(msg: String) = logger.warning(msg)

    fun severe(msg: String) = logger.severe(msg)
}