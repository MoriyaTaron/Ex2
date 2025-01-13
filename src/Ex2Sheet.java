import java.io.*;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    private CellEntry cordin;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
                table[i][j] = new SCell("");
                cordin = new CellEntry(Ex2Utils.ABC[i]+Integer.toString(j));
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
        Cell c = get(x,y);
        if(c!=null&& c.toString() != Ex2Utils.EMPTY_CELL) {
            if (SCell.isForm(c.getData())){
                c.setType(Ex2Utils.FORM);
                return Double.toString(SCell.computForm(c.getData()));
            }
            if (SCell.isNumber(c.getData())){
                c.setType(Ex2Utils.NUMBER);
                return c.getData();
            }
            if (containCell(c.toString())) {
                cordin= new CellEntry(x, y);
                if (c.toString().toUpperCase().contains(cordin.toString() )){
                     c.setType(Ex2Utils.ERR_CYCLE_FORM);
                     return Ex2Utils.ERR_CYCLE;//כדי שיהיה דגיאה על מעגליות ( לא הספקתי לעשות את הפונקציות של ה errcycle  אז יש לי רק במצב שהתא פיזית קוראה לעצמו)
                }
                if (getCels(c.toString()) == Ex2Utils.ERR_FORM) {
                    c.setType(Ex2Utils.ERR_FORM_FORMAT);
                    return Ex2Utils.ERR_FORM;
                }
                if (!SCell.isForm(getCels(c.toString()))) {
                    c.setType(Ex2Utils.ERR_FORM_FORMAT);
                    return Ex2Utils.ERR_FORM;
                } else{
                    c.setType(Ex2Utils.FORM);
                    return Double.toString(SCell.computForm(getCels(c.toString())));
                }
            }
            if (SCell.errform(c.getData())) {
                c.setType(Ex2Utils.ERR_FORM_FORMAT);
                return Ex2Utils.ERR_FORM;
            }
            if (SCell.isText(c.getData())){
                c.setType(Ex2Utils.TEXT);
                return c.getData();
            }
            ans = c.toString();

        }

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
        if (containCell(cords)){
            for (int i = 0;i<Ex2Utils.ABC.length;i++){
                if (cords.charAt(0)+""==Ex2Utils.ABC[i]) {
                    if (isIn(i, Integer.parseInt(cords.substring(1)))) {
                        ans = this.table[i][Integer.parseInt(cords.substring(1))];
                        return ans;
                    }
                }
            }
        }
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
    public  boolean isIn(int xx, int yy) {
        boolean ans = xx>=0 && yy>=0&& xx<=this.width()&&yy<=this.height() ;

        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        for (int i = 0; i < width(); i++){
            for (int j = 0; j < height(); j++){
                ans[i][j] = -1;
            }
        }

        // Add your code here

        // ///////////////////
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 3);
                    if (parts.length >= 3) {
                        int x = Integer.parseInt(parts[0].trim());
                        int y = Integer.parseInt(parts[1].trim());
                        String data = parts[2].trim();
                        if (isIn(x, y)) {
                            set(x, y, data);
                        }
                    }
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            eval();
        }


    @Override
    public void save(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("I2CS ArielU: SpreadSheet (Ex2) assignment - this line should be ignored in the load method");
        writer.newLine();
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                Cell cell = get(i, j);
                if (cell != null && !cell.getData().isEmpty()) {
                    writer.write(i + "," + j + "," + cell.getData());
                    writer.newLine();
                }
            }
        }

        writer.close();


    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        if (isIn(x,y)){
            if(get(x,y)!=null) {
                if (containCell(get(x,y).toString())){
                    if (SCell.isForm(getCels(get(x,y).toString()))){
                        String formu = Double.toString(SCell.computForm(getCels(get(x,y).toString())));
                        set(x,y,formu);
                        return ans=Double.toString(SCell.computForm(getCels(get(x,y).toString())));
                    }
                }
            }  //String
            ans = get(x,y).toString();

        }


        ///////////////////
        return ans;
    }






//פונקציית עזר לבדיקה האם יש תאים בסטרינג מסויים

    public  static boolean containCell(String s){
        boolean ans = false;
        if (s!=null){
            if (s.charAt(0)!= '=')
            return false;
        s = s.toUpperCase();
        for (int i = 0;i<Ex2Utils.ABC.length;i++) {
            if (s.contains(Ex2Utils.ABC[i])) {
                ans = true;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') {
                        if (j == s.length() - 1)
                            return false;
                        if (!(s.charAt(j + 1) >= '0' && s.charAt(j + 1) <= '9'))
                            return false;
                        if (j + 2 < s.length() - 1) {
                            if ((s.charAt(j + 2) >= '0' && s.charAt(j + 2) <= '9') && (s.charAt(j + 3) >= '0' && s.charAt(j + 3) <= '9'))
                                return false;
                        }
                    }
                }
            }
        }

        }
        return ans;
    }

    //    פונקציה שבמידה ויש תאים בסטרינג מסויים היא מחזירה את הסטרינג המקורי ובמקום השם של התא היא שמה סטרינג של תוכן התא שקראו אליו(במידה והוא תקף)
    public String getCels(String s){
        String ans = Ex2Utils.ERR_FORM;
        s=s.toUpperCase();
        int count = 0;
        int countcel = 0;
        int[] indofletr= new int[s.length()];
        String[] cells = new String[s.length()];
        for (int i = 0; i<s.length();i++){
            if (s.charAt(i)>='A'&&s.charAt(i)<='Z') {
                indofletr[count] = i;
                count++;

            }
        }
        String[] amountOfCe = new String[count];
        for (int i = 0; i<count;i++){
            if (s.length()>=indofletr[i]+3){
                if (s.charAt(indofletr[i]+2)>='0'&&s.charAt(indofletr[i]+2)<='9') {
                    cells[countcel] = s.substring(indofletr[i], indofletr[i] + 3);
                    countcel++;
                }
                else {
                    cells[countcel]=s.substring(indofletr[i],indofletr[i]+2);
                    countcel++;
                }
            }
            else {
                cells[countcel]=s.substring(indofletr[i],indofletr[i]+2);
                countcel++;
            }
        }
        for (int i = 0;i<countcel;i++){
            CellEntry cellEntry = new CellEntry(cells[i]);
            if (isIn(cellEntry.getX(),cellEntry.getY())){
                if (get(cellEntry.getX(),cellEntry.getY())==null||get(cellEntry.getX(),cellEntry.getY()).getType()==Ex2Utils.TEXT||get(cellEntry.getX(),cellEntry.getY()).getType()==Ex2Utils.ERR_CYCLE_FORM){
                    return Ex2Utils.ERR_FORM;
                }
                if (containCell( s.replaceAll(cells[i], "("+get(cellEntry.getX(),cellEntry.getY()).getData().replace("=","")+")")))
                    s=

                            getCels(s.replaceAll(cells[i], "("+get(cellEntry.getX(),cellEntry.getY()).getData().replace("=","")+")"));
                else
                    s=   s.replaceAll(cells[i], "("+get(cellEntry.getX(),cellEntry.getY()).getData().replace("=","")+")");// לבדוקקק

            }
        }


        return s;
    }
}
/**
//פונקציה לבדיקה האם אפשר לחשב עכשיו
private boolean canBeComputedNow(int x, int y){
    Cell cell =table[x][y];
    if (cell == null || cell.getType() == Ex2Utils.TEXT || cell.getType() == Ex2Utils.NUMBER){
        return true;
    }
    String data = cell.getData();
    if (!SCell.isForm(data)) {
        return false;
    }
    else if (!containCell(data)&& SCell.isForm(data)) {
        return true;
    }
    else {
        String[] depend = SCell.showcells(data);
        for (int i = 0; i<depend.length;i++){

        }
    }
    return true;
}
לא הספקתי לסיים את זה אז שמתי את זה בהערה
*/






