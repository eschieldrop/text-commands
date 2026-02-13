package no.ntnu.idatg2003.textcommands.wrap;

import java.util.Objects;

/**
 * Wraps a given selection (substring) inside a text.
 *
 * <p>Example:
 * <pre>
 *     new WrapSelectionTextCommand("<p>", "</p>", "selection")
 *     .execute("text with selection")
 *     -> "text with <p>selection</p>
 * </pre>
 *
 * <p>Here I interpret "selection" as a specific text
 * fragment that can occur multiple times.
 * Therefore, I wrap all occurences, not just the first.</p>
 */

public class WrapSelectionTextCommand extends WrapTextCommand {

    private final String selection;

    public WrapSelectionTextCommand(String opening, String end, String selection) {
        super(opening, end);
        if (selection == null || selection.isBlank()) {
            throw new IllegalArgumentException("Selection cannot be null or empty.");
        }
        this.selection = selection;
    }

    /** @return the text to be wrapped */
    public String getSelection() {
        return selection;
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        String wrapped = getOpening() + selection + getEnd();
        return text.replace(selection, wrapped);
    }
}