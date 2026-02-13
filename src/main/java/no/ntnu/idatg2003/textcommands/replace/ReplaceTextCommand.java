package no.ntnu.idatg2003.textcommands.replace;

import java.util.Objects;
import no.ntnu.idatg2003.textcommands.TextCommand;

/**
 * Replaces all occurrences of a specific text ({@code target}) with a new text ({@code replacement}).
 *
 * <p>Example:
 * <pre>
 *     new ReplaceTextCommand("target", "replacement")
 *     .execute("text with target and target")
 *     -> "text with replacement and replacement"
 * </pre>
 *
 *<p>I chose to use {@link String#replace(CharSequence, CharSequence)} because it
 * replaces all matches without regex (i.e. completely "literal" text), which is a good fit here.</p>
 */
public class ReplaceTextCommand implements TextCommand {
    private final String target;
    private final String replacement;

    /**
     * Creates a replace command.
     *
     * @param target the text to replace (cannot be {@code null} or empty)
     * @param replacement the text to insert (cannot be {@code null})
     * @throws IllegalArgumentException if target is empty or {@code null}, or replacement is {@code null}
     */
    public ReplaceTextCommand(String target, String replacement) {
        if (target == null || target.isBlank()) {
            throw new IllegalArgumentException("Target cannot be null or empty");
        }
        this.target = target;
        this.replacement = Objects.requireNonNull(replacement, "replacement cannot be null");
    }

    /** @return the text to be replaced */
    public String getTarget() {
        return target;
    }

    /** @return the replacement text */
    public String getReplacement() {
        return replacement;
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        return text.replace(target, replacement);
    }
}