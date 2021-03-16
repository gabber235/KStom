package world.cepi.kstom.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.SimpleCommand

/** Kotlin version of CommandProcessor using constructors instead of overriding methods */
public open class SimpleKommand(
    /** The name of the command. Used for bridager */
    name: String,

    /** Aliases, or alternative ways to type the command, stored in a list. */
    aliases: List<String> = emptyList(),

    /** The condition the player needs to meet to see the command. */
    public val condition: (sender: CommandSender, commandString: String?) -> Boolean = { _, _ -> true },

    /** What runs when the command is run. */
    public val process: (sender: CommandSender, command: String, args: Array<out String>) -> Boolean = { _, _, _ -> true }
): SimpleCommand(name, *aliases.toTypedArray()) {

    override fun process(sender: CommandSender, command: String, args: Array<out String>): Boolean {
        return process.invoke(sender, command, args)
    }

    public override fun hasAccess(sender: CommandSender, commandString: String?): Boolean {
        return condition.invoke(sender, commandString)
    }
}