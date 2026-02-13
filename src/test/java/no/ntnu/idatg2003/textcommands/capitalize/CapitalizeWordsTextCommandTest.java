package no.ntnu.idatg2003.textcommands.capitalize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeWordsTextCommandTest {

    @Test
    void capitalizesEveryWord() {
        var cmd = new CapitalizeWordsTextCommand();
        assertEquals("Text To Be Capitalized", cmd.execute("text to be capitalized"));
    }

    @Test
    void keepsSpacing() {
        var cmd = new CapitalizeWordsTextCommand();
        assertEquals("Hello   World", cmd.execute("hello   world"));
    }
}
