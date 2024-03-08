package world.cepi.kstom.command.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument

object ShellArgument : Argument<Unit>("shell") {
    override fun parse(sender: CommandSender, input: String) {

    }

    override fun parser(): String? {
        return null
    }

    override fun toString() = "Shell"
}