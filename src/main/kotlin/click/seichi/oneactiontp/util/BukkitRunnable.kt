package click.seichi.oneactiontp.util

import click.seichi.oneactiontp.OneActionTp
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

/**
 * [BukkitRunnable.runTaskLaterAsynchronously]を短縮した関数。
 *
 * 非同期で待機時間をつけて処理を実行する。
 *
 * @param delay 待機時間（tick）
 * @param task 実行する内容
 * @return [BukkitTask]
 */
fun runTaskLaterAsynchronously(delay: Long, task: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            task()
        }
    }.runTaskLaterAsynchronously(OneActionTp.PLUGIN, delay)
}

/**
 * [BukkitRunnable.runTaskTimer]を短縮した関数。
 *
 * @param delay 待機時間（tick）
 * @param period 実行間隔（tick）
 * @param task 実行する内容
 * @return [BukkitTask]
 */
fun runTaskTimer(delay: Long, period: Long, task: BukkitRunnable): BukkitTask
    = task.runTaskTimer(OneActionTp.PLUGIN, delay, period)
