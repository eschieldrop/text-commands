package no.ntnu.idatg2003.textcommands.wrap;

import java.util.Objects;
import no.ntnu.idatg2003.textcommands.TextCommand;

/**
 * Wraps the entire text with an opening string and an ending string.
 *
 * <p>Example:
 * <pre>
 *     new WrapTextCommand("<p>", "</p>")
 *     .execute("text")
 *     -> "<p>text</p>"
 * </pre>
 */
public class WrapTextCommand implements TextCommand {

    private final String opening;
    private final String end;

    /**
     * @param opening text to be added before
     * @param end     text to be added after
     */
    public WrapTextCommand(String opening, String end) {
        this.opening = Objects.requireNonNull(opening, "Opening cannot be null.");
        this.end = Objects.requireNonNull(end, "End cannot be null.");
    }

    /**
     * @return the opening string
     */
    public String getOpening() {
        return opening;
    }

    /**
     * @return the ending string
     */
    public String getEnd() {
        return end;
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        return opening + text + end;
    }
}