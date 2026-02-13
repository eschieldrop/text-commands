package no.ntnu.idatg2003.textcommands;

/**
 * Represents a text command: one concrete action that can be performed on a text string.
 *<p>The whole point of this interface is that all commands have the same "contract":
 * they take in some text and return some new text. This allows us to treat all
 * commands the same, store them in lists and run them as pipeline</p>
**/
@FunctionalInterface
public interface TextCommand {

    /**
     * Executes the command on the incoming text.
     *
     * @param text input-text (can be empty string, but not {@code null})
     * @return result text after the command is executed
     * @throws IllegalArgumentException if {@code text} is {@code null}
     */
    String execute(String text);
}