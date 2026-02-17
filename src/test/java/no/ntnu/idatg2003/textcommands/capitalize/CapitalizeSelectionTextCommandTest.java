package no.ntnu.idatg2003.textcommands.capitalize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeSelectionTextCommandTest {

    @Test
    void capitalizesAllOccurrencesOfSelection() {
        var cmd = new CapitalizeSelectionTextCommand("selection");
        assertEquals(
                "text with Selection and another Selection",
                cmd.execute("text with selection and another selection")
        );
    }

    @Test
    void leavesTextUnchangedIfSelectionNotFound() {
        var cmd = new CapitalizeSelectionTextCommand("missing");
        assertEquals("hello world", cmd.execute("hello world"));
    }

    @Test
    void throwsOnNullText() {
        var cmd = new CapitalizeSelectionTextCommand("selection");
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(null));
    }

    @Test
    void constructorRejectsNullOrBlankSelection() {
        assertThrows(IllegalArgumentException.class, () -> new CapitalizeSelectionTextCommand(null));
        assertThrows(IllegalArgumentException.class, () -> new CapitalizeSelectionTextCommand(""));
        assertThrows(IllegalArgumentException.class, () -> new CapitalizeSelectionTextCommand("   "));
    }

    @Test
    void preservesOriginalCaseExceptSelectionReplacement() {
        var cmd = new CapitalizeSelectionTextCommand("abc");
        assertEquals("xx Abc yy Abc", cmd.execute("xx abc yy abc"));
    }
}
