package no.ntnu.idatg2003.textcommands.wrap;

/**
 * Wraps each line separately.
 *
 * <p>Example:
 * <pre>
 *     new WrapLinesTextCommand("<p>", "</p>")
 *     .execute("first\nsecond")
 *     -> "<p>first</p>\n<p>second</p>"
 * </pre>
 *
 * <p>I split on "\n" and build the result again, so that line breaks are preserved.</p>
 */
public class WrapLinesTextCommand extends WrapTextCommand {

    public WrapLinesTextCommand(String opening, String end) {
        super(opening, end);
    }

    @Override
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        String[] lines = text.split("\n", -1); // -1: beholder tomme linjer
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            sb.append(getOpening()).append(lines[i]).append((getEnd()));
            if (i < lines.length - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}