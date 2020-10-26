package click.seichi.oneactiontp

import org.bukkit.plugin.java.JavaPlugin

class OneActionTp : JavaPlugin() {
    companion object {
        var PLUGIN: OneActionTp? = null
            private set
    }

    override fun onEnable() {
        PLUGIN = this
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}