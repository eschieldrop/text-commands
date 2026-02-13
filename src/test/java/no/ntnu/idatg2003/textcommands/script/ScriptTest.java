package no.ntnu.idatg2003.textcommands.script;

import java.util.List;

import no.ntnu.idatg2003.textcommands.TextCommand;
import no.ntnu.idatg2003.textcommands.replace.ReplaceTextCommand;
import no.ntnu.idatg2003.textcommands.wrap.WrapTextCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {

    @Test
    void runsCommandsInOrder() {
        TextCommand r = new ReplaceTextCommand("world", "NTNU");
        TextCommand w = new WrapTextCommand("[", "]");

        var script = new Script(List.of(r, w));
        assertEquals("[hello NTNU]", script.execute("hello world"));
    }

    @Test
    void throwsOnNullInput() {
        var script = new Script(List.of(text -> text));
        assertThrows(IllegalArgumentException.class, () -> script.execute(null));
    }
}
