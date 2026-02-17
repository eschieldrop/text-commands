package no.ntnu.idatg2003.textcommands.capitalize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTextCommandTest {

    @Test
    void capitalizesFirstLetterOnly() {
        var cmd = new CapitalizeTextCommand();
        assertEquals("Hello world", cmd.execute("hello world"));
    }

    @Test
    void returnsSameStringWhenAlreadyCapitalized() {
        var cmd = new CapitalizeTextCommand();
        assertEquals("Hello", cmd.execute("Hello"));
    }

    @Test
    void handlesEmptyString() {
        var cmd = new CapitalizeTextCommand();
        assertEquals("", cmd.execute(""));
    }

    @Test
    void throwsOnNullText() {
        var cmd = new CapitalizeTextCommand();
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(null));
    }

    @Test
    void doesNotTrimOrModifyRestOfText() {
        var cmd = new CapitalizeTextCommand();
        assertEquals("H   ello", cmd.execute("h   ello"));
    }
}
