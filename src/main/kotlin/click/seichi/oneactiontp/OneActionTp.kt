package click.seichi.oneactiontp

import click.seichi.oneactiontp.command.*
import click.seichi.oneactiontp.config.Configs
import click.seichi.oneactiontp.config.Message
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class OneActionTp : JavaPlugin() {
    companion object {
        lateinit var PLUGIN: OneActionTp
            private set
    }

    override fun onEnable() {
        PLUGIN = this

        setupConfigs(
                Message
        )

        registerCommands(
                "tpt" to TptCommand(),
                "tpaccept" to TpAcceptCommand(),
                "tpdeny" to TpDenyCommand(),
                "tpcancel" to TpCancelCommand(),
                "tptoggle" to TpToggleCommand()
        )
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun setupConfigs(vararg configs: Configs) = configs.forEach { it.setup(PLUGIN) }

    private fun registerCommands(vararg commands: Pair<String, CommandExecutor>) =
            commands.forEach { this.getCommand(it.first).executor = it.second }
}