// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    public static boolean vSf (String s){// valid signs for form beside "="
        char [] validSigns  = {'A','B','C','D','E','F','G','H','I','1','2','3','4','5','6','7','8','9','0',' ','+','-','*','/','(',')','.'};
        for (int i = 0; i<s.length();i++){
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

    public static boolean isNumber(String sCell){
        sCell = sCell.replaceAll(" ","");// "1234"
        try {
            double num = Double.parseDouble(sCell);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }









    public  static boolean isForm(String s){
        s = s.replace(" ","");
        try {
            computForm(s);
        } catch (Exception e){
            return false;

        }
        if (!vSf(s.substring(1)))
            return false;
        if (s.charAt(0)!='=')
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
            if (insidround<0)
                form = form.substring(0,firstRou)+'('+Double.toString(insidround)+')'+form.substring(lastRou+1,form.length());
            else
            form = form.substring(0,firstRou)+Double.toString(insidround)+form.substring(lastRou+1,form.length());



        }
        for (int i = 0;i<form.length();i++){
            if (i==0&&form.charAt(0)=='-'){
                    i=1;
            }
            if (form.charAt(i)=='('){
                for (int j = i+1;j<form.length();j++){
                    if (form.charAt(j)==')'){
                        i=j;
                        break;
                    }
                }
            }

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
        if(indOArr==1){// חייבת לסדר את העניין הזה עם המינוסים ולהסיר מהשורה  הזאת את  ה contains () כי אני רוצה שזה יפתור לי גם מינוסים
            double firstNum = Double.parseDouble(form.substring(0,indOfop[0]).replace("(","").replace(")",""));
            double secNum = Double.parseDouble(form.substring(indOfop[0]+1).replace("(","").replace(")",""));
            if (op[0]=='+')
                return firstNum+secNum;
                if (op[0]=='-')
                    return firstNum-secNum;
                    if (op[0]=='*')
                        return firstNum*secNum;
                        if (op[0]=='/')
                          return  firstNum/secNum;

           // return firstNum +form.charAt(indOfop[0])+ secNum;
        }
//        if (form.charAt(0) == '-') {
//            return - +computForm(form.substring(1));
//        }
        double first=computForm(form.substring(0,indOfop[indOArr-1]));
        double second = computForm(form.substring(indOfop[indOArr-1]+1));
        if (form.charAt(indOfop[indOArr-1])=='+')
            return first+second;
        if (form.charAt(indOfop[indOArr-1])=='-')
            return first - second;
        if (form.charAt(indOfop[indOArr-1])=='*')
            return first * second;
        if (form.charAt(indOfop[indOArr-1])=='/')
            return first / second;
        else {
            throw new IllegalArgumentException("not a form");
            //return  computForm(String.valueOf(first)+form.charAt(indOfop[indOArr-1])+String.valueOf(second));

        }
    }
    public static boolean isText(String text){
        if (isNumber(text)||isForm(text))
            return false;
        return true;
    }


    public SCell(String s) {
        if (isNumber(s)) {
            setType(Ex2Utils.NUMBER);
            setData(s);
        }
         else if (isForm(s)) {
            setType(Ex2Utils.FORM);
            setData(s);
        }
        else if (errform(s)){
            setType(Ex2Utils.ERR_FORM_FORMAT);
            setData(s);
        }
        else if (isText(s)) {
            setType(Ex2Utils.TEXT);
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
//        if (isNumber(line))
//            return line;
//        else if (isForm(line))
//            return Double.toString(computForm(line));
//       else if (isText(line))//&&line.charAt(0)=='=')
//        return Ex2Utils.ERR_FORM;
//       else if (isText(line))
           return line;
//       else return

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

    public static boolean errform(String s){
        if (s != ""){
        if (s.charAt(0)=='='&&!isForm(s))
            return true;
        }
        return false;
    }
}
