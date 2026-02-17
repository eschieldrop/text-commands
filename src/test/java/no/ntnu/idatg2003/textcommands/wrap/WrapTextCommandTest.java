package no.ntnu.idatg2003.textcommands.wrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WrapTextCommandTest {

    @Test
    void wrapsEntireText() {
        var cmd = new WrapTextCommand("<p>", "</p>");
        assertEquals("<p>hello</p>", cmd.execute("hello"));
    }

    @Test
    void wrapsEmptyStringIntoOnlyMarkers() {
        var cmd = new WrapTextCommand("[", "]");
        assertEquals("[]", cmd.execute(""));
    }

    @Test
    void throwsOnNullText() {
        var cmd = new WrapTextCommand("<", ">");
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(null));
    }

    @Test
    void constructorRejectsNullOpeningOrEnd() {
        assertThrows(NullPointerException.class, () -> new WrapTextCommand(null, "]"));
        assertThrows(NullPointerException.class, () -> new WrapTextCommand("[", null));
    }

    @Test
    void gettersReturnConfiguredValues() {
        var cmd = new WrapTextCommand("OPEN", "END");
        assertEquals("OPEN", cmd.getOpening());
        assertEquals("END", cmd.getEnd());
    }
}
