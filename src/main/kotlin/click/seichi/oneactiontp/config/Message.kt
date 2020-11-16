package click.seichi.oneactiontp.config

import click.seichi.oneactiontp.util.convertColorCode

object Message : Configs("message") {

    val playerOnlyExeCmd: String by lazy { convertColorCode(getString("player_only_can_execute")) }

    val invalidArgs: String by lazy { convertColorCode(getString("invalid_args")) }

    val selfTeleport: String by lazy { convertColorCode(getString("self_teleport")) }

    val denyingAllReqs: String by lazy { convertColorCode(getString("denying_all_req")) }

    val playerNotFound: String by lazy { convertColorCode(getString("player_not_found")) }

    val hasSentReq: String by lazy { convertColorCode(getString("has_sent_req")) }

    val clickToAccept: String by lazy { convertColorCode(getString("click_to_accept")) }

    val clickToDeny: String by lazy { convertColorCode(getString("click_to_deny")) }

    val hoverToAccept: String by lazy { convertColorCode(getString("hover_to_accept")) }

    val hoverToDeny: String by lazy { convertColorCode(getString("hover_to_deny")) }

    val reqHasArrived: List<String> by lazy { getStringList("req_has_arrived").map { convertColorCode(it) } }

    val reqHasBeenSent: String by lazy { convertColorCode(getString("req_has_been_sent")) }

    val reqHasExpired: String by lazy { convertColorCode(getString("req_has_expired")) }

    val noPendingTppReq: String by lazy { convertColorCode(getString("no_pending_tpp_req")) }

    val senderDeniedTppReq: String by lazy { convertColorCode(getString("sender_denied_tpp_req")) }

    val receiverDenyTppReq: String by lazy { convertColorCode(getString("receiver_deny_tpp_req")) }
}