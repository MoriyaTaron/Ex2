//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//יצרתי את המיין בשביל עצמי כשאני רוצה לנסות להריץ

public class Main {
    public static void main(String[] args) {
       // System.out.println(SCell.computForm("=-5-2"));
//        String s ="kgk";
//       if (Ex2Sheet.containCell("=A151"))
//            System.out.println("yes");
//        else
//            System.out.println("not");
        Ex2Sheet sheet = new Ex2Sheet(10, 10);  // יצירת גליון בגודל 10x10
        sheet.set(2, 3, "123");  // מגדיר את הערך של התא במיקום (2, 3) ל-"123"
        Cell cell = sheet.get(2, 3);  // מחזיר את התא במיקום (2, 3)
        System.out.println(cell.getData());  // מדפיס את הערך של התא
        Ex2Sheet tester = new Ex2Sheet(3,3);
        tester.set(0,1,"=-2+3*5");
        tester.set(1,1,"=a1+1");
        System.out.println(tester.getCels(tester.value(1,1)));
    }
}



























