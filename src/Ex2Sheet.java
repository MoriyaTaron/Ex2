import java.io.IOException;
import java.util.Arrays;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
                table[i][j] = new SCell("");
            }
        }
        eval();
    }
    public Ex2Sheet() {

        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        // Add your code here

        Cell c = get(x,y);
        if(c!=null) {ans = c.toString();}

        /////////////////////
        return ans;
    }

    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }

    @Override
    public Cell get(String cords) {
        Cell ans = null;
        CellEntry cordcel = new CellEntry(cords);
        if (cordcel.isValid()){
          if (isIn(cordcel.getX(),cordcel.getY()));
            ans = get(cordcel.getX(),cordcel.getY());


        }
        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int width() {
        return table.length;
    }
    @Override
    public int height() {
        return table[0].length;
    }
    @Override
    public void set(int x, int y, String s) {
        Cell c = new SCell(s);
        table[x][y] = c;
        // Add your code here

        /////////////////////
    }
    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) { //חושבת שממשתי צריכה לבדוק
        boolean ans = xx>=0 && yy>=0&& xx<=this.width()&&yy<=this.height() ;

        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Add your code here

        // ///////////////////
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        if (isIn(x,y)){
             if(get(x,y)!=null) {
                 if (containCell(get(x, y).getData())) {
                    get(x,y).setData("hello");
                     return get(x,y).getData();

                 }
             }  //String
            //ans = get(x,y).toString();

        }
        // Add your code here

        /////////////////////
        return ans;
        }






//פונקציית עזר לבדיקה האם יש תאים בסטרינג מסויים

    public  static boolean containCell(String s){
        if (s.charAt(0)!= '=')
            return false;
        s = s.toUpperCase();
        boolean ans = false;
        for (int i = 0;i<Ex2Utils.ABC.length;i++){
            if (s.contains(Ex2Utils.ABC[i])){
                ans=true;
                for (int j = 0; j<s.length();j++) {
                    if (s.charAt(j)>='A'&&s.charAt(j)<='Z'){
                        if (j==s.length()-1)
                            return false;
                        if (!(s.charAt(j+1)>='0'&&s.charAt(j+1)<='9'))
                            return false;
                        if (j+2<s.length()-1){
                            if ((s.charAt(j+2)>='0'&& s.charAt(j+2)<='9')&&(s.charAt(j+3)>='0'&&s.charAt(j+3)<='9'))
                                return false;
                        }
                    }
                }
            }

        }
return ans;
    }
    // פונקציה שבמידה ויש תאים בסטרינג מסויים היא מחזירה את הסטרינג המקורי ובמקום השם של התא היא שמה סטרינג של תוכן התא שקראו אליו(במידה והוא תקף)
//    public static String getCels(String s){
//        s=s.toUpperCase();
//        int count = 0;
//        int[] indofletr= new int[s.length()];
//        for (int i = 0; i<s.length();i++){
//            if (s.charAt(i)>='A'&&s.charAt(i)<='Z')
//                indofletr[count]=i;
//                count++;
//        }
//        String[] amountOfCe = new String[count];
//        for (int i = 0; i<count;i++){
//            if (i+1<s.length())
//    //   if ()    indofletr[i]+2
//        }
//    }







}


