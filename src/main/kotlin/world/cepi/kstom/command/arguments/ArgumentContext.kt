package world.cepi.kstom.command.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import java.util.*

class ArgumentContextValue<T>(val lambda: CommandSender.() -> T?) {
    fun from(sender: CommandSender) = lambda(sender)
}

class ArgumentContext<T>(
    id: String? = null,
    val argument: Argument<out T>? = null,
    val lambda: CommandSender.() -> T?
) : Argument<ArgumentContextValue<T>>(id ?: "context${UUID.randomUUID()}") {

    init {
        setDefaultValue(ArgumentContextValue(lambda))
    }

    override fun parse(sender: CommandSender, input: String): ArgumentContextValue<T> =
        ArgumentContextValue(argument?.let { { it.parse(sender, input) } } ?: lambda)

    override fun parser(): String? {
        return argument?.parser()
    }

    override fun nodeProperties(): ByteArray? {
        return argument?.nodeProperties()
    }

    override fun toString() = "Context<$id>"

}