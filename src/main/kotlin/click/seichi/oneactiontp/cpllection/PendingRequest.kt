package click.seichi.oneactiontp.cpllection

import org.bukkit.entity.Player

object PendingRequest : Request() {
    private val pendingTasks = mutableMapOf<Player, Int>()

    fun addTask(player: Player, taskId: Int) = pendingTasks.put(player, taskId)

    fun removeTask(player: Player) = pendingTasks.remove(player)

    fun getTaskId(player: Player) = pendingTasks[player] ?: throw NoSuchElementException()

    fun taskContains(player: Player) = pendingTasks.contains(player)
}