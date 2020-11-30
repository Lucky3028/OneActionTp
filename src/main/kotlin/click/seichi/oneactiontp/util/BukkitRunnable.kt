package click.seichi.oneactiontp.util

import click.seichi.oneactiontp.OneActionTp
import click.seichi.oneactiontp.collection.Request
import click.seichi.oneactiontp.config.Config
import click.seichi.oneactiontp.config.Message
import org.bukkit.entity.Player
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

/**
 * テレポート申請が一定時間経過後に無効になるという処理を予約しておく処理を共通化するための関数。
 * @param sender 申請の送信者。テレポートする側とは限らない
 * @param receiver 申請の受信者。テレポートされる側とは限らない
 * @param request [Request]を継承しているもの
 * @return [BukkitTask]
 */
fun makeRequestExpired(sender: Player, receiver: Player, request: Request): BukkitTask =
        runTaskLaterAsynchronously(20 * Config.secondsUntilExpired.toLong() ) {
            if (request.requestExists(sender, receiver)) {
                request.remove(sender, receiver)
                sender.sendMessage(Message.reqHasExpired)
                receiver.sendMessage(Message.reqHasExpired)
            }
        }