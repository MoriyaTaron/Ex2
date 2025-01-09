import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellEntryTest {

    @Test
    void isValid() {
        CellEntry tests = new CellEntry("b52");
        assertTrue(tests.isValid());
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }
}