package no.ntnu.idatg2003.textcommands.capitalize;

/**
 * Capitalizes all words in the text.
 *
 * <p>Example:
 * <pre>
 *     new CapitalizeWordTextCommand().execute("text to be capitalized")
 *     -> "Text to be capitalized"
 * </pre>
 *
 * <p>Here I interpret "words" as a sequences seperated by whitespace.
 * I preserve spaces by splitting on a regex that gives us words and spaces separately.</p>
 */
public class CapitalizeWordsTextCommand extends CapitalizeTextCommand {

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        if (text.isEmpty()) {
            return text;
        }

        // Split into "pieces": either whitespace or non-whitespace.
        String[] parts = text.split("(?<=\\s)|(?=\\s)");
        StringBuilder sb = new StringBuilder();

        for (String part : parts) {
            if (part.isBlank()) {
                sb.append(part); // whitespace preserved
            } else {
                sb.append(capitalizeWord(part));
            }
        }

        return sb.toString();
    }
}