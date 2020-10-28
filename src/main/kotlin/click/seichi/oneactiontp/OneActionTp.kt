package click.seichi.oneactiontp

import click.seichi.oneactiontp.config.Configs
import click.seichi.oneactiontp.config.Message
import org.bukkit.plugin.java.JavaPlugin

class OneActionTp : JavaPlugin() {
    companion object {
        lateinit var PLUGIN: OneActionTp
            private set
    }

    override fun onEnable() {
        PLUGIN = this

        setupConfig(
                Message
        )
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun setupConfig(vararg configs: Configs) = configs.forEach { it.setup(PLUGIN) }
}