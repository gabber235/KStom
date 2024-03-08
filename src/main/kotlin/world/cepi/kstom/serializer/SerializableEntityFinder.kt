package world.cepi.kstom.serializer

import kotlinx.serialization.Serializable
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity
import net.minestom.server.utils.entity.EntityFinder

@Serializable
class SerializableEntityFinder(
    val entityFinderString: String
) {
    fun get(sender: CommandSender): EntityFinder =
        Argument.parse(sender, ArgumentEntity(entityFinderString).singleEntity(false).onlyPlayers(false))
}