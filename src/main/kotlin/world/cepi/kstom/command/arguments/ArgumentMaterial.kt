package world.cepi.kstom.command.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.minecraft.ArgumentItemStack
import net.minestom.server.item.Material

class ArgumentMaterial(id: String) : Argument<Material>(id) {

    override fun parse(sender: CommandSender, input: String) = parse(sender, ArgumentItemStack(input)).material()
    override fun nodeProperties(): ByteArray? {
        return ArgumentItemStack(id).nodeProperties()
    }

    override fun parser(): String {
        return "minecraft:item_stack"
    }

    override fun toString() = "Material<$id>"
}