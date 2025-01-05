// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    public static boolean vSf (String s){// valid signs for form beside "="
        char [] validSigns  = {'A','B','C','D','E','F','G','H','I','1','2','3','4','5','6','7','8','9','0',' ','+','-','*','/','(',')','.'};
        for (int i = 1; i<s.length();i++){
            int valid = 0;
            for (int j = 0;j< validSigns.length;j++){
                if (s.charAt(i)==validSigns[j]){
                    valid=1;
                    j=validSigns.length;
                }
            } if (valid!=1)
                return false;
        }
        return true;
    }

    public static boolean isNumber(String sCell){ // "1234"
        try {
            double num = Double.parseDouble(sCell);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }









    public  static boolean isForm(String sCell){
        sCell=sCell.replaceAll(" ","");

        try {
            computForm(sCell);
        } catch (Exception e){
            return false;

        }
        if (sCell.charAt(0)!='=')
            return false;
        if (!vSf(sCell.substring(1)))
            return false;
        return true;
    }
    public  static double computForm(String form){
      form = form.replaceAll(" ","");
        if (form.charAt(0)=='=')
            form= form.substring(1);
        if (isNumber(form)){
            return Double.parseDouble(form);
        }
        int count;
        int countRound =0;
        int indOArr = 0;
        char [] op = new char[form.length()-1];
        int[] valueop = new int[form.length()-1];
        int[] indOfop = new int[form.length()-1];
        if (form.contains("(")){
            int firstRou=-1;
            int lastRou=-1;
            for (int i = 0; i<form.length();i++){
                if(form.charAt(i)=='(') {
                    if (countRound==0){
                        firstRou=i;
                    }
                    countRound++;
                }
                if (form.charAt(i)==')') {
                    countRound--;
                    if (countRound==0&&firstRou!=-1){
                        lastRou=i;
                        break;
                    }
                }
            }
            if (firstRou==0&& lastRou== form.length()-1){
                return computForm(form.substring(1,form.length()-1));
            }
              double insidround = computForm(form.substring(firstRou+1,lastRou));
            form = form.substring(0,firstRou)+Double.toString(insidround)+form.substring(lastRou+1,form.length());



        }
        for (int i = 0;i<form.length();i++){

        if (form.charAt(i)=='+'||form.charAt(i)=='-'){
            valueop[indOArr]=1;
            op[indOArr]=form.charAt(i);
            indOfop[indOArr]=i;
            indOArr++;
        }
        if (form.charAt(i)=='*'||form.charAt(i)=='/'){
            valueop[indOArr]=2;
            op[indOArr]=form.charAt(i);
            indOfop[indOArr]=i;
            indOArr++;
        }


        }
        for (int i = 0;i<indOArr-1;i++){
            for (int j = 0;j<indOArr-1-i;j++) {
                if (valueop[j] < valueop[j + 1]) {
                    int temp = valueop[j];
                    valueop[j] = valueop[j + 1];
                    valueop[j + 1] = temp;
                    char swich = op[j];
                    op[j] = op[j + 1];
                    op[j + 1] = swich;
                    int switchindop =indOfop[j];
                    indOfop[j]=indOfop[j+1];
                    indOfop[j+1]= switchindop;
                }
            }
        }
        if(indOArr==1&& !form.contains("(")){// חייבת לסדר את העניין הזה עם המינוסים ולהסיר מהשורה  הזאת את  ה contains () כי אני רוצה שזה יפתור לי גם מינוסים
            double firstNum = Double.parseDouble(form.substring(0,indOfop[0]));
            double secNum = Double.parseDouble(form.substring(indOfop[0]+1));
            if (op[0]=='+')
                return firstNum+secNum;
                if (op[0]=='-')
                    return firstNum-secNum;
                    if (op[0]=='*')
                        return firstNum*secNum;
                        if (op[0]=='/')
                          return  firstNum/secNum;
        }
        for (int i = 0;i<form.length();i++){


        }
        double first=computForm(form.substring(0,indOfop[indOArr-1]));
        double second = computForm(form.substring(indOfop[indOArr-1]+1));
        return  computForm(String.valueOf(first)+form.charAt(indOfop[indOArr-1])+String.valueOf(second));

    }
    public static boolean isText(String text){
        if (isNumber(text)||isForm(text))
            return false;
        return true;
    }


    public SCell(String s) {
        if (isNumber(s)) {
            setType(2);
            setData(s);
        }
         else if (isForm(s)) {
            setType(3);
            setData(Double.toString(computForm(s)));
        }
        if (isText(s)) {
            setType(1);
            setData(s);
        }
    }

    @Override
    public int getOrder() {
        // Add your code here

        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        // Add your code here
        line = s;
        /////////////////////
    }
    @Override
    public String getData() {
        return line;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here

    }
}
