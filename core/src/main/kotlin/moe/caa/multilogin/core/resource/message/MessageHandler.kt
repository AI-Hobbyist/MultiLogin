package moe.caa.multilogin.core.resource.message

import moe.caa.multilogin.api.logger.info
import moe.caa.multilogin.api.logger.warn
import moe.caa.multilogin.core.main.MultiCore
import moe.caa.multilogin.core.resource.MESSAGE_CONFIGURATION
import moe.caa.multilogin.core.resource.getResource
import moe.caa.multilogin.core.resource.saveDefaultResource
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.loader.HeaderMode
import java.io.BufferedReader
import java.io.InputStreamReader

class MessageHandler {
    var messageConfigurationNode: ConfigurationNode = HoconConfigurationLoader.builder().buildAndLoadString("")
        private set

    fun init() {
        reload()
    }

    fun reload() {
        val defaultMessageConfigurations = HoconConfigurationLoader.builder().source {
            BufferedReader(InputStreamReader(getResource(MESSAGE_CONFIGURATION), Charsets.UTF_8))
        }.build().load()

        val file = saveDefaultResource(MESSAGE_CONFIGURATION)

        messageConfigurationNode = HoconConfigurationLoader.builder().file(file).build().load().apply {
            var messing = false
            defaultMessageConfigurations.childrenMap().entries.forEach {
                if (hasChild(it.key).not()) {
                    node(it.key).set(it.value)
                    warn("Missing message node: ${it.key}")
                    messing = true
                }
            }

            if (messing) {
                HoconConfigurationLoader.builder()
                    .headerMode(HeaderMode.PRESERVE)
                    .file(file)
                    .build()
                    .save(this)

                info("All missing message nodes have been completed.")
            }
        }
    }
}

fun language(node: String): String {
    val configurationNode = MultiCore.instance.messageHandler.messageConfigurationNode
        .node(node.split("."))
    if (configurationNode.isList) {
        return configurationNode.getList(String::class.java)?.joinToString { "\n" } ?: ""
    }
    return configurationNode.getString("")
}