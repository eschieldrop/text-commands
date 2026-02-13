package no.ntnu.idatg2003.textcommands.replace;

/**
 * Replaces only the first occurrence of {@code target} with {@code replacement}.
 *
 * <p>Example:
 * <pre>
 *     new ReplaceFirstTextCommand("target", "replacement")
 *     .execute("text with target and target")
 *     -> "text with replacement and target"
 * </pre>
 *
 * <p>I'll have the class inherit from {@link no.ntnu.idatg2003.textcommands.replace.ReplaceTextCommand} to reuse fields and get methods,
 * but override {@code execute} since the behavior is slightly different.</p>
 */
public class ReplaceFirstTextCommand extends ReplaceTextCommand {

    public ReplaceFirstTextCommand(String target, String replacement) {
        super(target, replacement);
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        int index = text.indexOf(getTarget());
        if (index < 0) {
            return text; // nothing to replace
        }

        return text.substring(0, index)
                + getReplacement()
                + text.substring(index + getTarget().length());
    }
}