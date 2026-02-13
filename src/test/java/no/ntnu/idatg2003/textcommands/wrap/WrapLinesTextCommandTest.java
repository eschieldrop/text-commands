package no.ntnu.idatg2003.textcommands.wrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WrapLinesTextCommandTest {

    @Test
    void wrapsEachLine() {
        var cmd = new WrapLinesTextCommand("<p>", "</p>");
        assertEquals("<p>first</p>\n<p>second</p>", cmd.execute("first\nsecond"));
    }
}
