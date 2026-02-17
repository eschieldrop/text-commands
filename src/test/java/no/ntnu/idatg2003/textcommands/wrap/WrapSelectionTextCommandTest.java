package no.ntnu.idatg2003.textcommands.wrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WrapSelectionTextCommandTest {

    @Test
    void wrapsAllOccurrencesOfSelection() {
        var cmd = new WrapSelectionTextCommand("<b>", "</b>", "sel");
        assertEquals(
                "a <b>sel</b> b <b>sel</b> c",
                cmd.execute("a sel b sel c")
        );
    }

    @Test
    void leavesTextUnchangedIfSelectionNotFound() {
        var cmd = new WrapSelectionTextCommand("(", ")", "missing");
        assertEquals("hello world", cmd.execute("hello world"));
    }

    @Test
    void throwsOnNullText() {
        var cmd = new WrapSelectionTextCommand("<", ">", "x");
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(null));
    }

    @Test
    void constructorRejectsNullOrBlankSelection() {
        assertThrows(IllegalArgumentException.class, () -> new WrapSelectionTextCommand("<", ">", null));
        assertThrows(IllegalArgumentException.class, () -> new WrapSelectionTextCommand("<", ">", ""));
        assertThrows(IllegalArgumentException.class, () -> new WrapSelectionTextCommand("<", ">", "   "));
    }

    @Test
    void getterReturnsSelection() {
        var cmd = new WrapSelectionTextCommand("[", "]", "abc");
        assertEquals("abc", cmd.getSelection());
    }
}
