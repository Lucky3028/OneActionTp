package click.seichi.oneactiontp.config

object Config : Configs("config") {
    val secondsUntilTp: Int by lazy { getInt("seconds_until_teleport") }
}