package click.seichi.oneactiontp.Util

import org.bukkit.Bukkit

private val logger = Bukkit.getServer().logger

fun info(msg: String) = logger.info(msg)

fun warn(msg: String) = logger.warning(msg)

fun severe(msg: String) = logger.severe(msg)