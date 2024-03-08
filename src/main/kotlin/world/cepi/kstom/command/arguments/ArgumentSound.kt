package world.cepi.kstom.command.arguments

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.command.builder.arguments.minecraft.SuggestionType
import net.minestom.server.command.builder.arguments.minecraft.registry.ArgumentRegistry
import net.minestom.server.sound.SoundEvent

/**
 * Represents an argument giving an [Sound.Type].
 */
class ArgumentSound(id: String?) : ArgumentRegistry<Sound.Type>(id) {
    override fun getRegistry(value: String): Sound.Type {
        return SoundEvent.fromNamespaceId(value)
            ?: return Sound.Type { Key.key(value) } // Instance of Sound.Type
    }

    init {
        suggestionType = SuggestionType.AVAILABLE_SOUNDS
    }

    override fun parser(): String {
        return "minecraft:resource_location"
    }

    override fun toString() = "Sound$id"

}