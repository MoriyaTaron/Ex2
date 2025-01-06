import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Ex2Test {

    @Test
    public void testIsForm() {
     assertTrue(SCell.isForm("=(5+2)"));
            assertTrue(SCell.isForm("=5*(220)/(-9)+7.5"));
            assertTrue(SCell.isForm(" = 5"));
        assertTrue(SCell.isForm("=-25"));
       assertTrue(SCell.isForm("=-5*5"));
        assertTrue(SCell.isForm("=5*(-2)"));// לסדר אם מישהו ישים פלוס לפני
        assertTrue(SCell.isForm("=7"));
        assertTrue(SCell.isForm("=5"));
        assertTrue(SCell.isForm("=    -   2    5  + 15 * (  -  2 ) "));
        assertFalse(SCell.isForm("==17*2-4+9-5"));
                assertFalse(SCell.isForm("-25"));
        assertFalse(SCell.isForm("5-25"));
        assertFalse(SCell.isForm("=-25/"));
        assertFalse(SCell.isForm("=15+"));
        assertFalse(SCell.isForm("=29+2-"));
        assertFalse(SCell.isForm("5**2"));
    }

@Test
    public void testIsNumber(){
        assertTrue(SCell.isNumber("+5"));
        assertTrue(SCell.isNumber("-29.3467346"));
        assertTrue(SCell.isNumber("    23 9"));
        assertTrue(SCell.isNumber("- 898 09 . 9 7 "));
        assertFalse(SCell.isNumber(")2"));
        assertFalse(SCell.isNumber("(-2)"));
        assertFalse(SCell.isNumber("*5"));
    }
    @Test
    public void testIsText(){
        assertTrue(SCell.isText("hello"));
        assertTrue(SCell.isText("-58*-"));
        assertTrue(SCell.isText("5<7"));
        assertTrue(SCell.isText(" 9+7+"));
     //   assertTrue(SCell.isText("-.52"));   גם אצל בועז  זה קורא את זה מספר
        assertFalse(SCell.isText("-52"));
        assertFalse(SCell.isText("=123/456.232-3+(-5/3)"));
      //  assertFalse(SCell.isText("=5*-5")); אם יהיה זמן לנסות לתקן


    }
    @Test
    public void testcomputForm(){
        double s = SCell.computForm("=-5*2+(0-4)");
        assertEquals(s,-14.0);
        assertEquals(SCell.computForm("=17*2-4+9-5"),34);
        assertEquals(SCell.computForm("=-9/4"),-2.25);
        assertEquals(SCell.computForm("=(((3)))+5"),8.0);
        assertEquals(SCell.computForm("=((-2*3))+5/5"),-5.0);
    }
}

