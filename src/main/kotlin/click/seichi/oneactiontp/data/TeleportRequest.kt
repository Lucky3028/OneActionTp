package click.seichi.oneactiontp.data

import org.bukkit.entity.Player

object TeleportRequest {
    private val teleportRequestSet = mutableSetOf<Pair<Player, Player>>()

    /**
     * 指定されたPlayerが送信したテレポート申請の相手をまとめたSetを返す関数。
     *
     * @param sender 検索対象の[Player]
     * @return [Set]<[Player]>
     */
    fun findRequests(sender: Player) = teleportRequestSet
            .filter { it.first.uniqueId == sender.uniqueId }.map { it.second }.toSet()

    /**
     * 指定されたPlayerが送信されたテレポート申請の相手をまとめたSetを返す関数。
     *
     * @param receiver 検索対象の[Player]
     * @return [Set]<[Player]>
     */
    fun findRequested(receiver: Player) = teleportRequestSet
            .filter { it.second.uniqueId == receiver.uniqueId }.map { it.first }.toSet()

    /**
     * 指定されたPlayerの組の申請があるかどうかを返す関数。
     *
     * @param sender テレポート申請を出した[Player]
     * @param receiver テレポート申請を出された[Player]
     * @return [Boolean]
     */
    fun hasAlreadySent(sender: Player, receiver: Player) = findRequests(sender).contains(receiver)

    /**
     * 指定されたPlayerはテレポート申請を出しているかどうかを返す関数。
     *
     * @param sender 検索対象の[Player]
     * @return [Boolean]
     */
    fun hasSentRequest(sender: Player) = findRequests(sender).isNotEmpty()

    /**
     * 指定されたPlayerはテレポート申請を出されているかどうかを返す関数。
     *
     * @param receiver 検索対象の[Player]
     * @return [Boolean]
     */
    fun isRequested(receiver: Player) = findRequested(receiver).isNotEmpty()

    fun add(sender: Player, receiver: Player) = teleportRequestSet.add(sender to receiver)

    fun remove(sender: Player, receiver: Player) = teleportRequestSet.remove(sender to receiver)

    fun removeAll(sender: Player) = findRequests(sender).forEach { remove(sender, it) }
}