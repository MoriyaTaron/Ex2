// Add your documentation below:

import java.sql.Array;

public class SCell implements Cell {
    private String line;
    private int type;
    public boolean vSf (String s){// valid signs for form beside "="
      char [] validSigns  = {'A','B','C','D','E','F','G','H','I','1','2','3','4','5','6','7','8','9','0',' ','+','-','*','/','(',')'};
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

public boolean isNumber(String sCell){ // "1234"
    try {
        double num = Double.parseDouble(sCell);
        return true;
    }
    catch (Exception e) {
        return false;
    }
}
public Double simpEval(String s,char[] arr){                        // this function is for calculating a form on the right order
       for()
}






public boolean isForm(String sCell){
    if (sCell.charAt(0)!='='||vSf(sCell.substring(1)))
        for (int i = 0;i<sCell.length();i++){
        }
        return false;
}
public double eval(String form){
        if (isNumber(form.substring(1))){
            return Double.parseDouble(form.substring(1));
    }
    if (isNumber(form)) {
        return Double.parseDouble(form);
    }
        int count;
        int countRound =0;
        int indOArr = 0;
        char [] op = new char[form.length()-2];
        int[] num = new int[form.length()-2];
        for (int i = 1; i<form.length();i++){
            if(form.charAt(i)=='(')
                countRound++;
            if (form.charAt(i)==')')
                countRound--;
                  if (form.charAt(i)=='+'||form.charAt(i)=='-'){
                      num[indOArr]=1+countRound;
                      op[indOArr]=form.charAt(i);
                      indOArr++;
                  }
                    if (form.charAt(i)=='*'||form.charAt(i)=='/'){
                        num[indOArr]=2+countRound;
                        op[indOArr]=form.charAt(i);
                        indOArr++;
                    }
            }
        for (int i = 0;i<indOArr-1;i++){
            for (int j = 0;j<indOArr-1-i;j++){
                if (num[j]<num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                    char swich = op[j];
                    op[j] = op[j+1];
                    op[j+1] = swich;
                }
            }
        }




}
    // Add your code here


    public SCell(String s) {
        // Add your code here
        setData(s);
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
