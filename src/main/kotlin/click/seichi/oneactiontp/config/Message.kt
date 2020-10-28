package click.seichi.oneactiontp.config

object Message : Configs("message") {

    val playersOnlyCanExecute: String by lazy { getString("players_only_can_execute") }

    val invalidArgs: String by lazy { getString("invalid_args") }

    val selfTeleport: String by lazy { getString("self_teleport") }
}