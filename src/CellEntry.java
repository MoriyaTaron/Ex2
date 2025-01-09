// Add your documentation below:

public class CellEntry  implements Index2D {
    private String indOfCell;
public CellEntry(String indOfCell){
    this.indOfCell=indOfCell;
}
    @Override
    public  boolean isValid() {
        if (this.indOfCell.length()>3||this.indOfCell.length()<2)
            return false;
        boolean validLet = false;
        char[] validInd = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            for (int i = 0; i<validInd.length; i++){
                if (this.indOfCell.charAt(0)==validInd[i]) {
                    validLet = true;
                    break;
                }
            }
            if (validLet==false)
                return false;
            try {
                Integer.parseInt(this.indOfCell.substring(1));
            }
            catch( Exception e){
                return false;
            }
            return true;
        //}
    }

    @Override
    public int getX() {
        if (!this.isValid()){
            return Ex2Utils.ERR;
        }
        String XLetter = this.indOfCell.toUpperCase().charAt(0) +"";
        for (int i =0;i<Ex2Utils.ABC.length;i++){
            if (Ex2Utils.ABC[i].equals(XLetter)){
                return i;
            }
        }
        return Ex2Utils.ERR;
    }


    @Override
    public int getY() {
        if (this.isValid()) {
            return Integer.parseInt(this.indOfCell.substring(1));
        }
        return Ex2Utils.ERR;
    }
    public String toString(){
    return this.indOfCell;
    }

}
