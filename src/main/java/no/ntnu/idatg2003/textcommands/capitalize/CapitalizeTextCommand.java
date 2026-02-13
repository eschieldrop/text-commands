 package no.ntnu.idatg2003.textcommands.capitalize;

import no.ntnu.idatg2003.textcommands.TextCommand;

/**
 * Capitalize the first letter of the text (if the text is not empty).
 *
 * <p>Example:
 * <pre>
 *     new CapitalizeTextCommand().execute("text to be capitalized")
 *     -> "Text to be capitalized"
 * </pre>
 */
public class CapitalizeTextCommand implements TextCommand {

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (text.isEmpty()) {
            return text;
        }

        char first = text.charAt(0);
        return Character.toUpperCase(first) + text.substring(1);
    }

    /**
     * I use helper method in subclasses to capitalize a "word" in a simple way.
     * @param word the word to capitalize
     * @return the word with the first letter capitalized (or unchanged if empty)
     */
    protected String capitalizeWord(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}