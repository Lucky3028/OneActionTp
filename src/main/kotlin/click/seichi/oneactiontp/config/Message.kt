package click.seichi.oneactiontp.config

object Message : Configs("message") {

    val selfTeleport: String by lazy { getString("self-teleport") }
}