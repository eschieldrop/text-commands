package no.ntnu.idatg2003.textcommands.replace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceFirstTextCommandTest {

    @Test
    void replacesOnlyFirstOccurrence() {
        var cmd = new ReplaceFirstTextCommand("target", "replacement");
        assertEquals("text replacement target", cmd.execute("text target target"));
    }
}
