package click.seichi.oneactiontp.config

object Message : Configs("message") {

    val playerOnlyExeCmd: String by lazy { getString("player_only_can_execute") }

    val invalidArgs: String by lazy { getString("invalid_args") }

    val selfTeleport: String by lazy { getString("self_teleport") }

    val denyingAllReqs: String by lazy { getString("denying_all_req") }

    val playerNotFound: String by lazy { getString("player_not_found") }

    val hasSentReq: String by lazy { getString("has_sent_req") }

    val clickToAccept: String by lazy { getString("click_to_accept") }

    val clickToDeny: String by lazy { getString("click_to_deny") }

    val hoverToAccept: String by lazy { getString("hover_to_accept") }

    val hoverToDeny: String by lazy { getString("hover_to_deny") }

    val reqHasArrived: List<String> by lazy { getStringList("req_has_arrived") }

    val reqHasBeenSent: String by lazy { getString("req_has_been_sent") }

    val reqHasExpired: String by lazy { getString("req_has_expired") }
}