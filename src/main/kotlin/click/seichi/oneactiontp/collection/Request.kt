package click.seichi.oneactiontp.collection

import org.bukkit.entity.Player

/**
 * 申請を出した[Player]と出された[Player]の組み合わせを保持する`map`を操作する`abstract class`。
 */
abstract class Request {
    // Keyの重複が許されないので、cancelしない限り他のプレイヤーに申請を出せないことに注意
    private val requestSet = mutableMapOf<Player, Player>()

    /**
     * 指定されたPlayerが送信した申請の相手を返す関数。
     *
     * @param sender 検索対象の[Player]
     * @return [Player]
     * @throws NoSuchElementException
     */
    fun findRequest(sender: Player) = requestSet[sender] ?: throw NoSuchElementException()

    /**
     * 指定されたPlayerが送信された申請の相手をまとめたSetを返す関数。
     *
     * @param receiver 検索対象の[Player]
     * @return [Set]<[Player]>
     */
    fun findBeenRequested(receiver: Player) = requestSet.filterValues { it == receiver }.keys.toSet()

    /**
     * 指定されたPlayerの組の申請があるかどうかを返す関数。
     *
     * @param sender 申請を出した[Player]
     * @param receiver 申請を出された[Player]
     * @return [Boolean]
     */
    fun requestExists(sender: Player, receiver: Player) =
            requestSet.containsKey(sender) && requestSet.containsValue(receiver)

    /**
     * 指定されたPlayerは申請を出しているかどうかを返す関数。
     *
     * @param sender 検索対象の[Player]
     * @return [Boolean]
     */
    fun hasRequested(sender: Player) = requestSet.containsKey(sender)

    /**
     * 指定されたPlayerは申請を出されているかどうかを返す関数。
     *
     * @param receiver 検索対象の[Player]
     * @return [Boolean]
     */
    fun hasBeenRequested(receiver: Player) = requestSet.containsValue(receiver)

    fun add(sender: Player, receiver: Player) = requestSet.put(sender, receiver)!!

    fun remove(sender: Player, receiver: Player) = requestSet.remove(sender, receiver)

    fun removeAll(sender: Player) = requestSet.remove(sender)!!
}