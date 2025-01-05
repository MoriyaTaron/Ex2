import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Ex2Test {

    @Test
    public void testIsForm() {
     assertTrue(SCell.isForm("=(5+2)"));
            assertTrue(SCell.isForm("=5*(220)/9+7.5"));
            assertTrue(SCell.isForm(" = 5"));
        assertTrue(SCell.isForm("=-25"));
   //    assertTrue(SCell.isForm("=(-5)*5")); אני צריכה לבדוק ולתקן את כל עניין המינוסים
        assertTrue(SCell.isForm("=5"));
        assertTrue(SCell.isForm("=7"));
        assertTrue(SCell.isForm("=5"));
        assertTrue(SCell.isText("hello"));

    }
}
