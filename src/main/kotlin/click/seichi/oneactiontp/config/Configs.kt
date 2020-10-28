package click.seichi.oneactiontp.config

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

abstract class Configs(private val ymlName: String): YamlConfiguration() {
    fun setup(plugin: JavaPlugin) {
        val yml = File(plugin.dataFolder, "$ymlName.yml")
        if (yml.exists()) this.load(yml)
        else createNewFile(plugin, yml)
    }

    private fun createNewFile(plugin: JavaPlugin, yml: File) = plugin.saveResource(yml.name, false)
}