package no.ntnu.idatg2003.textcommands.capitalize;

/**
 * Capitalizes only a selected "selection" (substring) within a text.
 *
 * <p>Example:
 * <pre>
 *     new CapitalizeSelectionTextCommand("selection")
 *     .execute("text with selection and another selection")
 *     -> "text with Selection and another Selection"
 * </pre>
 * <p>I capitalize all occurrences of selection and leave the rest untouched.</p>
 */
public class CapitalizeSelectionTextCommand extends CapitalizeTextCommand {

    private final String selection;

    public CapitalizeSelectionTextCommand(String selection) {
        if (selection == null || selection.isBlank()) {
            throw new IllegalArgumentException("Selection cannot be null or empty.");
        }
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        String replacement = capitalizeWord(selection);
        return text.replace(selection, replacement);
    }
}