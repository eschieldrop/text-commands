package no.ntnu.idatg2003.textcommands.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import no.ntnu.idatg2003.textcommands.TextCommand;

/**
 * A script is a "pipeline" of {@link no.ntnu.idatg2003.textcommands.TextCommand} objects that are executed one after the other.
 *
 * <p>The result of command #1 becomes input to command #2, etc.
 * This way we can build larger transformations of text, without creating one giant method.</p>
 */
public class Script {

    private final List<TextCommand> commands;

    /**
     * Creates a script with commands in a fixed order.
     *
     * <p>I copy the list into a new list to prevent anyone from changing it from outside
     * after the script is created.</p>
     *
     * @param commands list of commands (can be empty, but not {@code null})
     * @throws IllegalArgumentException if list is {@code null} or contains {@code null}
     */
public Script(List<TextCommand> commands) {
    if (commands == null) {
        throw new IllegalArgumentException("Commands cannot be null.");
    }
    for (TextCommand c : commands) {
        Objects.requireNonNull(c, "Commands cannot contain null.");
    }
    this.commands = new ArrayList<>(commands);
}
    /**
     * Runs all commands in the script on the text.
     *
     * @param text input text
     * @return finished transformed text after all commands have been run
     */
    public String execute(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        String result = text;
        for (TextCommand command : commands) {
            result = command.execute(result);
        }
        return result;
    }

    /** @return a copy of the command list (for inspection/testing) */
    public List<TextCommand> getCommands() {
    return new ArrayList<>(commands);
    }
}