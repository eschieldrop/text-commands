package no.ntnu.idatg2003.textcommands.replace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceTextCommandTest {

    @Test
    void replacesAllOccurrences() {
        var cmd = new ReplaceTextCommand("target", "replacement");
        assertEquals("text replacement replacement", cmd.execute("text target target"));
    }

    @Test
    void returnsOriginalIfNoMatch() {
        var cmd = new ReplaceTextCommand("x", "y");
        assertEquals("abc", cmd.execute("abc"));
    }

    @Test
    void throwsOnNullText() {
        var cmd = new ReplaceTextCommand("a", "b");
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(null));
    }
}
