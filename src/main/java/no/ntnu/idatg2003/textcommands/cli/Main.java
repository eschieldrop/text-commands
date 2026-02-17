package no.ntnu.idatg2003.textcommands.cli;

import no.ntnu.idatg2003.textcommands.TextCommand;
import no.ntnu.idatg2003.textcommands.capitalize.CapitalizeSelectionTextCommand;
import no.ntnu.idatg2003.textcommands.capitalize.CapitalizeTextCommand;
import no.ntnu.idatg2003.textcommands.capitalize.CapitalizeWordsTextCommand;
import no.ntnu.idatg2003.textcommands.replace.ReplaceFirstTextCommand;
import no.ntnu.idatg2003.textcommands.replace.ReplaceTextCommand;
import no.ntnu.idatg2003.textcommands.script.Script;
import no.ntnu.idatg2003.textcommands.wrap.WrapLinesTextCommand;
import no.ntnu.idatg2003.textcommands.wrap.WrapSelectionTextCommand;
import no.ntnu.idatg2003.textcommands.wrap.WrapTextCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * A tiny terminal client for trying the TextCommands API without writing Java code.
 *
 * <p>This client is intentionally simple:
 * the first argument decides which command to run, the rest are parameters.</p>
 *
 * <p>Examples:
 * <pre>
 *   java -cp target/classes no.ntnu.idatg2003.textcommands.cli.Main wrap "<p>" "</p>" "hello"
 *   java -cp target/classes no.ntnu.idatg2003.textcommands.cli.Main replace target replacement "text target target"
 *   java -cp target/classes no.ntnu.idatg2003.textcommands.cli.Main script "replace:world:NTNU|wrap:[ : ]|capwords" "hello world"
 * </pre>
 *
 * <p>Note: Task 7 is optional, so this is only meant as a demo runner.</p>
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String cmd = args[0].toLowerCase();

        try {
            switch (cmd) {
                case "wrap" -> runSingle(new WrapTextCommand(args[1], args[2]), args, 3);
                case "wraplines" -> runSingle(new WrapLinesTextCommand(args[1], args[2]), args, 3);
                case "wrapsel" -> runSingle(new WrapSelectionTextCommand(args[1], args[2], args[3]), args, 4);

                case "replace" -> runSingle(new ReplaceTextCommand(args[1], args[2]), args, 3);
                case "replacefirst" -> runSingle(new ReplaceFirstTextCommand(args[1], args[2]), args, 3);

                case "cap" -> runSingle(new CapitalizeTextCommand(), args, 1);
                case "capwords" -> runSingle(new CapitalizeWordsTextCommand(), args, 1);
                case "capsel" -> runSingle(new CapitalizeSelectionTextCommand(args[1]), args, 2);

                case "script" -> runScript(args);
                default -> printUsage();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void runSingle(TextCommand command, String[] args, int textIndex) {
        if (args.length <= textIndex) {
            throw new IllegalArgumentException("Missing text argument.");
        }
        String text = joinRest(args, textIndex);
        System.out.println(command.execute(text));
    }

    private static void runScript(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Usage: script \"<pipeline>\" \"<text>\"");
        }

        String pipeline = args[1];
        String text = joinRest(args, 2);

        List<TextCommand> commands = parsePipeline(pipeline);
        Script script = new Script(commands);

        System.out.println(script.execute(text));
    }

    /**
     * Parses a pipeline like:
     * replace:world:NTNU|wrap:[ : ]|capwords
     *
     * <p>Format per step:
     * <ul>
     *   <li>replace:target:replacement</li>
     *   <li>replacefirst:target:replacement</li>
     *   <li>wrap:opening:end</li>
     *   <li>wraplines:opening:end</li>
     *   <li>capsel:selection</li>
     *   <li>cap</li>
     *   <li>capwords</li>
     * </ul>
     */
    private static List<TextCommand> parsePipeline(String pipeline) {
        String[] steps = pipeline.split("\\|");
        List<TextCommand> commands = new ArrayList<>();

        for (String step : steps) {
            String trimmed = step.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split(":", -1);
            String name = parts[0].toLowerCase();

            switch (name) {
                case "replace" -> commands.add(new ReplaceTextCommand(parts[1], parts[2]));
                case "replacefirst" -> commands.add(new ReplaceFirstTextCommand(parts[1], parts[2]));
                case "wrap" -> commands.add(new WrapTextCommand(parts[1], parts[2]));
                case "wraplines" -> commands.add(new WrapLinesTextCommand(parts[1], parts[2]));
                case "cap" -> commands.add(new CapitalizeTextCommand());
                case "capwords" -> commands.add(new CapitalizeWordsTextCommand());
                case "capsel" -> commands.add(new CapitalizeSelectionTextCommand(parts[1]));
                default -> throw new IllegalArgumentException("Unknown step in pipeline: " + name);
            }
        }
        return commands;
    }

    private static String joinRest(String[] args, int fromIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = fromIndex; i < args.length; i++) {
            if (i > fromIndex) sb.append(" ");
            sb.append(args[i]);
        }
        return sb.toString();
    }

    private static void printUsage() {
        System.out.println("""
      Usage:
        wrap <opening> <end> <text...>
        wraplines <opening> <end> <text...>
        wrapsel <opening> <end> <selection> <text...>

        replace <target> <replacement> <text...>
        replacefirst <target> <replacement> <text...>

        cap <text...>
        capwords <text...>
        capsel <selection> <text...>

        script "<pipeline>" <text...>

      Example pipeline:
        "replace:world:NTNU|wrap:[ : ]|capwords"
      """);
    }
}
