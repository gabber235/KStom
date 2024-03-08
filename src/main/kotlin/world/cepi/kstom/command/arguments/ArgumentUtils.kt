package world.cepi.kstom.command.arguments

import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.ArgumentLiteral
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import org.jetbrains.annotations.Contract
import world.cepi.kstom.command.kommand.Kommand
import kotlin.reflect.KProperty

class Literal {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): ArgumentLiteral {
        return ArgumentLiteral(property.name)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: ArgumentLiteral) {

    }
}

/**
 * Automatically generates an [ArgumentLiteral] based on the String being passed
 *
 * @return an [ArgumentLiteral] based on the String being passed
 */
val literal get() = Literal()

fun String.literal() = ArgumentLiteral(this)

fun <T> Argument<T>.defaultValue(value: T): Argument<T> =
    this.setDefaultValue { _ -> value }

open class SuggestionIgnoreOption(val modifier: (String) -> String = { it }) {
    object NONE : SuggestionIgnoreOption()
    object IGNORE_CASE : SuggestionIgnoreOption({ it.lowercase() })
}

/**
 * Suggests a set of [SuggestionEntry]s.
 * Will automatically sort and filter entries to match with input
 *
 * @param lambda The lambda to process the args
 *
 * @return The argument that had its suggestion callback set
 */
@Contract("_ -> this")
fun <T> Argument<T>.suggestComplex(
    suggestionIgnoreOption: SuggestionIgnoreOption = SuggestionIgnoreOption.NONE,
    lambda: Kommand.SyntaxContext.() -> List<SuggestionEntry>
): Argument<T> = this.setSuggestionCallback { sender, context, suggestion ->

    lambda(Kommand.SyntaxContext(sender, context))
        .filter {
            suggestionIgnoreOption.modifier(it.entry)
                .startsWith(suggestionIgnoreOption.modifier(suggestion.input))
        }
        .sortedBy { it.entry }
        .forEach { suggestion.addEntry(it) }

}

fun <T> Argument<T>.suggest(
    suggestionIgnoreOption: SuggestionIgnoreOption = SuggestionIgnoreOption.NONE,
    lambda: Kommand.SyntaxContext.() -> List<String>
): Argument<T> = this.suggestComplex(suggestionIgnoreOption) { lambda(this).map { SuggestionEntry(it) } }