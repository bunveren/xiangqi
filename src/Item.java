import java.io.Serializable;
import java.util.ArrayList;
public class Item extends AbstractItem implements Serializable{
    //	private String position;
    private boolean isRedColor;
    public String name;

    ArrayList<String>posHistory=new ArrayList<String>();

    Item(boolean isRedColor, String position){
        this.isRedColor=isRedColor;
        this.setPosition(position);
        this.name="";
    }

    public Item() {

    }

    public boolean getIsRedColor() {
        return isRedColor;
    }
    public void setIsRedColor(boolean isRed) {
        this.isRedColor=isRed;
    }

    public boolean willBeInPalace(String destination) {
        int i=destination.charAt(0)-'a';
        int j = Integer.parseInt(destination.substring(1))-1;
        if(j>5 || j<3) return false;
        else {
            if(isRedColor) {
                if(i>=0 && i<=2) return true;
            }
            else {
                if(i>=7 && i<=9) return true;
            }
        }

        return false;
    }

    public boolean hasCrossedRiver() {
        //fil geçemez piyon geçtikten sonra yan gidebilir
        int a=this.getPosition().charAt(0)-'a';

        if(isRedColor) {
            if(a>=5)return true;
        }
        else {
            if(a<=3)return true;
        }
        return false;

    }
    public boolean isCrossingRiver(String destination) {
        int i=destination.charAt(0)-'a';
        if(isRedColor) {
            if(i>=5)return true;
        }
        else {
            if(i<=3)return true;
        }
        return false;
    }

    public boolean canMove(String destination, Board board) {

        if(destination=="xx" || destination.length()!=2 || this.getPosition().equals("xx")) return false;
        if(board.getItemFromPos(this.getPosition())==-1)return false;
        int i=destination.charAt(0)-'a';
        int j = Integer.parseInt(destination.substring(1))-1;
        if(i>9 || i<0 || j>9 || j<0) {
            return false;//hata mesajı
        }

        return true;
    }

    public String toString() {
        return "";
    }

//	public String getPosition() {
//		return position;
//	}
//	
//	public void setPosition(String position) {
//		this.position = position;
//	}

    public float puan() {
        return 0.0f;
    }
}
//************************************************************************************************************

//MİRAS ALMIŞ TAŞLAR

//************************************************************************************************************
class General extends Item{
    ArrayList<String>threats=new ArrayList<String>();
    public General(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "ş";
            else return "Ş";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {
            int i=destination.charAt(0)-'a';
            int j = Integer.parseInt(destination.substring(1))-1;
            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;

            //palace çevresi olmalı, hareketi tek olmalı düz olmalı ve gittiği yerde taş olmamalı
            if((Math.abs(a-i)==1 && Math.abs(b-j)==0 ) || ( Math.abs(a-i)==0 && Math.abs(b-j)==1) ) {
//			System.out.println("deneme 1"+this.toString()+" "+this.getPosition()+" "+destination);
                //tek harekette sadece yan ya da dik gidebilir çapraz gidemez
                if(willBeInPalace(destination)) {
                    if(board.getItemFromPos(destination)==-1 ||
                            board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor()) return true;
                }
            }
        }
        return false;
    }

    public float puan() {
        return 0.0f;
    }

}

//************************************************************************************************************
class Advisor extends Item{

    public Advisor(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "v";
            else return "V";
        }
        return name;
    }


    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {
            int i=destination.charAt(0)-'a';
            int j = Integer.parseInt(destination.substring(1))-1;
            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;

            //palace çevresi olmalı, hareketi tek olmalı çapraz olmalı ve gittiği yerde taş olmamalı
            if(Math.abs(a-i)==1 && Math.abs(b-j)==1) {
                if(willBeInPalace(destination)) {
                    if(board.getItemFromPos(destination)==-1 ||
                            board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor()) return true;
                }
            }
        }
        return false;
    }

    public float puan() {
        return 2.0f;
    }
    //vezir
    //tek çapraz gidecek palacedan ayrılmayacak
}
//************************************************************************************************************
class Elephant extends Item{

    public Elephant(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "f";
            else return "F";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {
            int i=destination.charAt(0)-'a';
            int j = Integer.parseInt(destination.substring(1))-1;
            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;
            if(!isCrossingRiver(destination)) {
                if(Math.abs(i-a)==2 && Math.abs(j-b)==2&&
                        board.getItemFromPos((char)((a+i)/2)+""+(int)((b+j)/2)+1)==-1 ) {
                    if(board.getItemFromPos(destination)==-1 ||
                            board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor()) return true;
                }
            }
        }
        return false;
    }
    //iki parça çapraz gidecek ve aradaki parçaların üzerinden
    //atlayamayacak
    //nehri geçemez
    public float puan() {
        return 2.0f;
    }
}
//************************************************************************************************************
class Horse extends Item{

    public Horse(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "a";
            else return "A";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {
            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;


            String[]horsePos=new String[8];
            horsePos[0]=((char)(a+'a'+2)+""+(b-1+1));
            horsePos[1]=((char)(a+'a'+2)+""+(b+1+1));
            horsePos[2]=((char)(a+'a'-2)+""+(b-1+1));
            horsePos[3]=((char)(a+'a'-2)+""+(b+1+1));
            horsePos[4]=((char)(a+'a'+1)+""+(b-2+1));
            horsePos[5]=((char)(a+'a'-1)+""+(b-2+1));
            horsePos[6]=((char)(a+'a'+1)+""+(b+2+1));
            horsePos[7]=((char)(a+'a'-1)+""+(b+2+1));

            for(int count=0; count<8; count++) {
                if(horsePos[count].equals(destination)) {

                    if(count==0 || count==1) {
                        if(board.getItemFromPos((char)(a+'a'+1)+""+(b+1))==-1 ||
                                board.items[board.getItemFromPos((char)(a+'a'+1)+""+(b+1))].getIsRedColor()==this.getIsRedColor() ) return true;

                    }
                    else if(count==2 || count==3) {
                        if(board.getItemFromPos((char)(a+'a'-1)+""+(b+1))==-1 ||
                                board.items[board.getItemFromPos((char)(a+'a'-1)+""+(b+1))].getIsRedColor()==this.getIsRedColor() ) return true;

                    }
                    else if(count==4 || count==5) {
                        if(board.getItemFromPos((char)(a+'a')+""+(b-1+1))==-1 ||
                                board.items[board.getItemFromPos((char)(a+'a')+""+(b))].getIsRedColor()==this.getIsRedColor() ) return true;

                    }
                    else if(count==6 || count==7) {
                        if(board.getItemFromPos((char)(a+'a')+""+(b+1+1))==-1 ||
                                board.items[board.getItemFromPos((char)(a+'a')+""+(b+2))].getIsRedColor()==this.getIsRedColor() ) return true;

                    }
					
	/*			   -Gidilebilecek Yerler-
	                 a+2,b-1   a+2,b+1  0 ve 1
					 		a+1,b
					 	horse
		a+1,b-2		 	a,b arrayde             a+1,b+2
		a-1,b-2			a dikey b yatay         a-1,b+2
		a,b-2                 a-1,b                  a,b+2
       4 ve 5       a-2,b-1   a-2,b+1          6 ve 7
							a-2,b  2 ve 3
	
	kontrol: a,b-1 a,b+1 a-1,b a+1,b boş olacak 
	*/
                }
            }




        }return false;
    }

    public float puan() {
        return 4.0f;
    }

}
//************************************************************************************************************
class Chariot extends Item{

    public Chariot(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "k";
            else return "K";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {
            int i=destination.charAt(0)-'a';
            int j = Integer.parseInt(destination.substring(1))-1;

            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;

            if((a==i && b!=j)||(b==j && a!=i)) { //düz gidiyor

                if(a==i && b!=j) {
                    int bigger=0; int smaller=0;
                    if(b>j) { bigger=b; smaller=j;}
                    else {bigger=j; smaller=b;}
                    boolean isRoadClean=true;
                    for(int c=smaller+1; c<bigger; c++) {
                        String position=(char)(a+'a')+""+(c+1);

                        if(board.getItemFromPos(position)!=-1) isRoadClean=false;
                    }

                    return isRoadClean && (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                }
                else {//a!=i ve b==j
                    int bigger=0; int smaller=0;
                    if(a>i) { bigger=a; smaller=i;}
                    else {bigger=i; smaller=a;}
                    boolean isRoadClean=true;
                    for(int c=smaller+1; c<bigger; c++) {
                        String position=(char)(c+'a')+""+(b+1);

                        if(board.getItemFromPos(position)!=-1) isRoadClean=false;
                    }
                    return isRoadClean && (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                }
            }
        }
        return false;
    }

    public float puan() {
        return 9.0f;
    }
    //kale
    //kaleyle aynı, aradaki parçalardan atlayamaz
}
//************************************************************************************************************
class Cannon extends Item{

    public Cannon(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "t";
            else return "T";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {

            int i=destination.charAt(0)-'a'; //9
            int j = Integer.parseInt(destination.substring(1))-1; //4
            int a=getPosition().charAt(0)-'a'; //2
            int b = Integer.parseInt(getPosition().substring(1))-1; //1

            if(  (a==i && b!=j) || (b==j && a!=i)  ) {
                //düz gidiyor

                if(a==i && b!=j) {

                    int bigger=0; int smaller=0;
                    if(b>j) { bigger=b; smaller=j;}
                    else {bigger=j; smaller=b;}
//*********************************************attan örnek al
                    int countObstacles=0;
                    for(int c=smaller+1; c<bigger; c++) {
                        String position=(char)(a+'a')+""+(c+1);
                        if(board.getItemFromPos(position)!=-1){
                            ++countObstacles;
                        }
                    }
                    if(board.getItemFromPos(destination)==-1 && countObstacles==0) {
                        return true;
                    }
                    else if(board.getItemFromPos(destination)!=-1
                            && board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor()
                            && countObstacles==1) return true;
                    return false;
                }

                else if(a!=i && b==j){//a!=i ve b==j

                    int bigger=0; int smaller=0;
                    if(a>i) { bigger=a; smaller=i;}
                    else {bigger=i; smaller=a;}

                    int countObstacles=0;
                    for(int c=smaller+1; c<bigger; c++) {

                        String position=(char)(c+'a')+""+(b+1);


                        if(board.getItemFromPos(position)!=-1){
                            countObstacles++;
                        }


                    }
                    if(board.getItemFromPos(destination)==-1 && countObstacles==0) return true;
                    else if(board.getItemFromPos(destination)!=-1
                            && board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor()
                            && countObstacles==1) return true;
                    return false;
                }
            }
        }
        return false;
    }
    //(board.getItemFromPos(destination)==null || board.getItemFromPos(destination).getIsRedColor()!=this.getIsRedColor()); temel şart
    //top
    //kaleyle aynı şekilde gidebilir
    //bi düşmanı etkilemek içn aynı yoldaki başka bi düşmanının
    //üstünden atlar
    public float puan() {
        return 4.5f;
    }
}
//************************************************************************************************************
class Soldier extends Item{

    public Soldier(boolean b, String position) {
        super(b, position);
    }

    public String toString() {
        if(name.equals("")) {
            if(getIsRedColor()) return "p";
            else return "P";
        }
        return name;
    }

    public boolean canMove(String destination, Board board) {
        if(super.canMove(destination, board)) {

            int i=destination.charAt(0)-'a';
            int j = Integer.parseInt(destination.substring(1))-1;

            int a=this.getPosition().charAt(0)-'a';
            int b = Integer.parseInt(this.getPosition().substring(1))-1;

            if(this.hasCrossedRiver()) {
                if(i==a) {//yan gider
                    if(Math.abs(b-j)==1) return (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                }
                else { //i!=a yani b==j zprunlu
                    if(getIsRedColor() && (i-a)==1 && b==j) return (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                    else if(!getIsRedColor() && (a-i)==1 && b==j) return (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                }
            }
            else {
                if(i!=a && b==j) {
                    if(getIsRedColor()) {
                        if(i-a==1) return (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                    }
                    else {
                        if(a-i==1) return (board.getItemFromPos(destination)==-1 || board.items[board.getItemFromPos(destination)].getIsRedColor()!=this.getIsRedColor());
                    }
                }
            }
            return false;
        }
        return false;
    }
    //birer adım atarlar ileri dogru
    //nehri gecince yanlamasına birer adım da atabilirler
    public float puan() {
        return 1.0f;
    }

}