import java.io.Serializable;
public class Board extends AbstractBoard implements Serializable{

    Item[][]boardArr=new Item[10][9];

    public Item[][]getBoardArr(){
        return boardArr;
    }

    public Item[]getItems(){
        return items;
    }

    public int getItemFromPos(String position) {
        for(int i = 0; i<32; i++) {
            if(items[i].getPosition().equals(position)) {
                return i;
            }
        }
        return -1;
    }

    Board(){
        items=new Item[32];
        boardArr[0][0]=new Chariot(false, "j1");
        boardArr[0][1]=new Horse(false, "j2");
        boardArr[0][2]=new Elephant(false, "j3");
        boardArr[0][3]=new Advisor(false, "j4");
        boardArr[0][4]=new General(false, "j5");
        boardArr[0][5]=new Advisor(false,"j6");
        boardArr[0][6]=new Elephant(false,"j7");
        boardArr[0][7]=new Horse(false,"j8");
        boardArr[0][8]=new Chariot(false,"j9");

        boardArr[2][1]=new Cannon(false,"h2");
        boardArr[2][7]=new Cannon(false,"h8");

        boardArr[3][0]=new Soldier(false,"g1");
        boardArr[3][2]=new Soldier(false,"g3");
        boardArr[3][4]=new Soldier(false,"g5");
        boardArr[3][6]=new Soldier(false,"g7");
        boardArr[3][8]=new Soldier(false,"g9");

        boardArr[6][0]=new Soldier(true,"d1");
        boardArr[6][2]=new Soldier(true,"d3");
        boardArr[6][4]=new Soldier(true,"d5");
        boardArr[6][6]=new Soldier(true,"d7");
        boardArr[6][8]=new Soldier(true,"d9");

        boardArr[7][1]=new Cannon(true,"c2");
        boardArr[7][7]=new Cannon(true,"c8");

        boardArr[9][0]=new Chariot(true,"a1");
        boardArr[9][1]=new Horse(true,"a2");
        boardArr[9][2]=new Elephant(true,"a3");
        boardArr[9][3]=new Advisor(true,"a4");
        boardArr[9][4]=new General(true,"a5");
        boardArr[9][5]=new Advisor(true,"a6");
        boardArr[9][6]=new Elephant(true,"a7");
        boardArr[9][7]=new Horse(true,"a8");
        boardArr[9][8]=new Chariot(true,"a9");
        boolean control=false;
        for(int j=0; j<10; j++) {
            for(int k=0; k<9; k++) {
                if(boardArr[j][k]!=null) {
                    for(int i = 0; i<32; i++) {
                        if(items[i]==null) {
                            items[i]=boardArr[j][k];
                            control=true;
                            break;
                        }
                    }
                }
                if(control) {
                    control=false;
                    continue;
                }
            }
        }
    }

    public void boardArrFiller() {
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<9; j++) {
                boardArr[i][j]=null;
            }
        }

        for(int i = 0; i<32; i++) {
            if(!items[i].getPosition().equals("xx")) {

                int a = items[i].getPosition().charAt(0)-'a';
                int b = Integer.parseInt(items[i].getPosition().substring(1))-1;
                boardArr[a][b]=items[i];
            }
        }
    }

    public void print() {
        boardArrFiller();
        String s="";
        char header = 'j';
        for(int i = 9;i>-1; i--) {
            s+=header+"\t";
            for(int j=0; j<9; j++) {
                if(boardArr[i][j]==null) s+="-";
                else s+=boardArr[i][j];
                if(j!=8) s+="--";
            }
            header--;
            s+="\n";
            switch(i) {
                case 9 : {s+=" \t|  |  |  |\\ | /|  |  |  |"+"\n"; continue;}
                case 8 : {s+=" \t|  |  |  |/ | \\|  |  |  |"+"\n"; continue;}
                case 7 : {s+=" \t|  |  |  |  |  |  |  |  |"+"\n"; continue;}
                case 6 : {s+=" \t|  |  |  |  |  |  |  |  |"+"\n"; continue;}
                case 5 : {s+=" \t|                       |"+"\n"; continue;}
                case 4 : {s+=" \t|  |  |  |  |  |  |  |  |"+"\n"; continue;}
                case 3 : {s+=" \t|  |  |  |  |  |  |  |  |"+"\n"; continue;}
                case 2 : {s+=" \t|  |  |  |/ | \\|  |  |  |"+"\n"; continue;}
                case 1 : {s+=" \t|  |  |  |\\ | /|  |  |  |"+"\n"; continue;}
                case 0 : {s+="\n"+" \t1--2--3--4--5--6--7--8--9"; continue;}
            }
        }
        System.out.println(s);
    }
}