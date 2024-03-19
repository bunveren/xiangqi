import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Game extends AbstractGame{

    General kirmiziGen;
    General siyahGen;

    private boolean isRedTurn=true;

    public Game(String player1_name, String player2_name) {
        red = new Player(player1_name,true);//red olmalı
        black = new Player(player2_name, false);
        board=new Board();
        kirmiziGen = (General) board.items[board.getItemFromPos("a5")];
        siyahGen = (General) board.items[board.getItemFromPos("j5")];

    }

    public Board getBoard() {
        // TODO Auto-generated method stub
        return board;
    }

    public void setBoard(Board newBoard) {
        this.board=newBoard;
    }

    boolean notAgainstNewRules(String from, String to) {///////////////////////////
        boolean kontrol=true;

        if(board.getItemFromPos(from)!=-1) {
            //poshistory
            int posHistorySize = board.items[board.getItemFromPos(from)].posHistory.size();
            if( posHistorySize>=5) {
                String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                if( pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to)) {
                    kontrol=false;
                }
            }
            //threats
            if(!(board.items[board.getItemFromPos(from)] instanceof General )){
                //general degil tekrar generale hareket edebilir oldu
                //&&  board.items[board.getItemFromPos(from)].canMove("", board)
                //şah çekebilir oldu. üç şah olsa ..iki şah çekmenin aynı pos ve aynı taş olmaması lazım bu üçüncü
                if(board.items[board.getItemFromPos(from)].getIsRedColor()
                        && board.items[board.getItemFromPos(from)].canMove(siyahGen.getPosition(), board)) {
                    int threatSize =siyahGen.threats.size();
                    int threatPosHistorySize=board.items[board.getItemFromPos(from)].posHistory.size();
                    if(threatSize>=2 && threatPosHistorySize>=5) {
                        String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                        String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                        String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                        String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                        String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                        String thr2 = siyahGen.threats.get(threatSize-1);
                        String thr1 = siyahGen.threats.get(threatSize-2);

                        if( (pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to))
                                &&  (thr1.equals(thr2) && thr1.equals(board.items[board.getItemFromPos(from)]+to)) ) {
                            kontrol=false;
                        }
                    }
                }
                else if(!(board.items[board.getItemFromPos(from)].getIsRedColor())
                        && board.items[board.getItemFromPos(from)].canMove(kirmiziGen.getPosition(), board)) {
                    int threatSize =kirmiziGen.threats.size();
                    int threatPosHistorySize=board.items[board.getItemFromPos(from)].posHistory.size();
                    if(threatSize>=2 && threatPosHistorySize>=5) {
                        String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                        String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                        String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                        String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                        String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                        String thr2 = kirmiziGen.threats.get(threatSize-1);
                        String thr1 = kirmiziGen.threats.get(threatSize-2);

                        if( (pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to))
                                &&  (thr1.equals(thr2) && thr1.equals(board.items[board.getItemFromPos(from)]+" "+to)) ) {
                            kontrol=false;
                        }
                    }
                }
            }
        }
        return kontrol;
    }

    boolean notAgainstNewRules(String from, String to, Board board) {
        boolean kontrol=true;

        if(board.getItemFromPos(from)!=-1) {
            //poshistory
            int posHistorySize = board.items[board.getItemFromPos(from)].posHistory.size();
            if( posHistorySize>=5) {
                String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                if( pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to)) {
                    kontrol=false;
                }
            }
            if(!(board.items[board.getItemFromPos(from)] instanceof General )){
                //general degil tekrar generale hareket edebilir oldu
                //&&  board.items[board.getItemFromPos(from)].canMove("", board)
                //şah çekebilir oldu. üç şah olsa ..iki şah çekmenin aynı pos ve aynı taş olmaması lazım bu üçüncü
                if(board.items[board.getItemFromPos(from)].getIsRedColor()
                        && board.items[board.getItemFromPos(from)].canMove(siyahGen.getPosition(), board)) {
                    int threatSize =siyahGen.threats.size();
                    int threatPosHistorySize=board.items[board.getItemFromPos(from)].posHistory.size();
                    if(threatSize>=2 && threatPosHistorySize>=5) {
                        String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                        String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                        String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                        String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                        String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                        String thr2 = siyahGen.threats.get(threatSize-1);
                        String thr1 = siyahGen.threats.get(threatSize-2);

                        if( (pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to))
                                &&  (thr1.equals(thr2) && thr1.equals(board.items[board.getItemFromPos(from)]+to)) ) {
                            kontrol=false;
                        }
                    }
                }
                else if(!(board.items[board.getItemFromPos(from)].getIsRedColor())
                        && board.items[board.getItemFromPos(from)].canMove(kirmiziGen.getPosition(), board)) {
                    int threatSize =kirmiziGen.threats.size();
                    int threatPosHistorySize=board.items[board.getItemFromPos(from)].posHistory.size();
                    if(threatSize>=2 && threatPosHistorySize>=5) {
                        String pos5 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-1);
                        String pos4 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-2);
                        String pos3 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-3);
                        String pos2 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-4);
                        String pos1 = board.items[board.getItemFromPos(from)].posHistory.get(posHistorySize-5);

                        String thr2 = kirmiziGen.threats.get(threatSize-1);
                        String thr1 = kirmiziGen.threats.get(threatSize-2);

                        if( (pos1.equals(from) && pos1.equals(pos3) && pos1.equals(pos5) && pos2.equals(pos4) && pos2.equals(to))
                                &&  (thr1.equals(thr2) && thr1.equals(board.items[board.getItemFromPos(from)]+" "+to)) ) {
                            kontrol=false;
                        }
                    }
                }
            }
        }
        return kontrol;
    }
    void play(String from, String to) {

        boolean basarili=false;
        boolean closeGame=false;
//		System.out.println(from+"  "+to);
//		System.out.println(notAgainstNewRules(from,to) );    //////////////////////
//		System.out.println(board.getItemFromPos(from)!=-1);
//		System.out.println("!isFG:  "+!isFlyingGeneral(from,to));
//		System.out.println(!willCheck_illegalMoves(from,to) );
//		System.out.println(board.items[board.getItemFromPos(from)].canMove(to, board));
//		System.out.println(isRedTurn == board.items[board.getItemFromPos(from)].getIsRedColor() ); //false
//		System.out.println(board.getItemFromPos(to)!=-1); //false

        if(notAgainstNewRules(from,to)
                && board.getItemFromPos(from)!=-1
                && !isFlyingGeneral(from,to)
                && !willCheck_illegalMoves(from,to) ) {
            if( (isRedTurn == board.items[board.getItemFromPos(from)].getIsRedColor() )
                    && board.items[board.getItemFromPos(from)].canMove(to, board)){

                if(board.getItemFromPos(to)!=-1) {
                    board.items[board.getItemFromPos(to)].setPosition("xx");
                }
                board.items[board.getItemFromPos(from)].setPosition(to);

                board.boardArrFiller();
                basarili=true;
                updateCalculation();
                willCheck_addThreats();
                if(isCheckmate(board,isRedTurn)) {
                    closeGame=true;
                    if(isRedTurn) System.out.println("ŞAH MAT! "+red.name+" oyunu kazandı. "+red.name+"'in puanı: "+red.puan+", "+black.name+"'nin puanı: "+black.puan);
                    else System.out.println("ŞAH MAT! "+black.name+" oyunu kazandı. "+black.name+"'in puanı: "+black.puan+", "+red.name+"'nin puanı: "+red.puan);
                }
            }
        }
        if(basarili) {
            if(! (board.items[board.getItemFromPos(to)] instanceof General))
                board.items[board.getItemFromPos(to)].posHistory.add(to);
            board.items[board.getItemFromPos(to)].posHistory.trimToSize();
            isRedTurn= !isRedTurn;
        }
        else if(closeGame) {
            System.out.println("hatali hareket");
        }
        else {
            System.out.println("hatali hareket");
        }
    }

    void updateCalculation() {
        red.puan=0;
        black.puan=0;
        //reset
        for(int i = 0; i<32; i++) {
            if(board.items[i].getPosition().equals("xx")) {
                if(board.items[i].getIsRedColor()) {
                    black.puan+=board.items[i].puan();
                }
                else {
                    red.puan+=board.items[i].puan();
                }
            }
        }
    }

    boolean isFlyingGeneral(String from,String to) {
        Board tempBoard =tempBoardMaker(board);

        if(tempBoard.getItemFromPos(from)!=-1
                && (tempBoard.items[tempBoard.getItemFromPos(from)].getIsRedColor()==isRedTurn
                && !willCheck_illegalMoves(from,to,tempBoard))) {
            if(tempBoard.getItemFromPos(to)!=-1 ) tempBoard.items[tempBoard.getItemFromPos(to)].setPosition("xx");
            tempBoard.items[tempBoard.getItemFromPos(from)].setPosition(to);
        }
        General tempKirmizi=null, tempSiyah=null;
        for(int x = 0; x<32; x++) {
            if(tempBoard.items[x] instanceof General) {
                if(tempBoard.items[x].getIsRedColor()) tempKirmizi=(General)tempBoard.items[x];
                else tempSiyah=(General)tempBoard.items[x];
            }
        }


        if(tempKirmizi.getPosition().charAt(1)==tempSiyah.getPosition().charAt(1)) {
            int ortaksayi = Integer.parseInt(tempKirmizi.getPosition().substring(1))-1;
            int kir= tempKirmizi.getPosition().charAt(0)-'a';
            int siy = tempSiyah.getPosition().charAt(0)-'a';
            int bigger=siy; int smaller=kir;
            if(kir>siy) {bigger=kir; smaller=siy;}


            for(int testc = smaller+1; testc<bigger; testc++) {
                String position=(char)(testc+'a')+""+(ortaksayi+1);
                if(tempBoard.getItemFromPos(position)!=-1) return false;
            }
            return true;

        }
        return false;

    }


    boolean isFlyingGeneral(String from, String to, Board board) {
        Board tempBoard =tempBoardMaker(board);

        if(tempBoard.getItemFromPos(from)!=-1
                && (tempBoard.items[tempBoard.getItemFromPos(from)].getIsRedColor()==isRedTurn
                && !willCheck_illegalMoves(from,to,tempBoard))) {
            if(tempBoard.getItemFromPos(to)!=-1 ) tempBoard.items[tempBoard.getItemFromPos(to)].setPosition("xx");
            tempBoard.items[tempBoard.getItemFromPos(from)].setPosition(to);
        }
        General tempKirmizi=null, tempSiyah=null;
        for(int x = 0; x<32; x++) {
            if(tempBoard.items[x] instanceof General) {
                if(tempBoard.items[x].getIsRedColor()) tempKirmizi=(General)tempBoard.items[x];
                else tempSiyah=(General)tempBoard.items[x];
            }
        }


        if(tempKirmizi.getPosition().charAt(1)==tempSiyah.getPosition().charAt(1)) {
            int ortaksayi = Integer.parseInt(tempKirmizi.getPosition().substring(1))-1;
            int kir= tempKirmizi.getPosition().charAt(0)-'a';
            int siy = tempSiyah.getPosition().charAt(0)-'a';
            int bigger=siy; int smaller=kir;
            if(kir>siy) {bigger=kir; smaller=siy;}

            for(int testc = smaller+1; testc<bigger; testc++) {
                String position=(char)(testc+'a')+""+(ortaksayi+1);
                if(tempBoard.getItemFromPos(position)!=-1) return false;
            }
            return true;

        }
        return false;
    }


    public boolean willCheck_illegalMoves(String from, String to) {
        Board tempBoard =tempBoardMaker(board);
        // Find the positions of both generals
        General tempKirmizi=null, tempSiyah=null;
        General biz=null, karsi = null;
        for(int x = 0; x<32; x++) {
            if(tempBoard.items[x] instanceof General) {
                if(tempBoard.items[x].getIsRedColor()) tempKirmizi=(General)tempBoard.items[x];
                else tempSiyah=(General)tempBoard.items[x];
            }
        }
        if(isRedTurn) {karsi=tempSiyah; biz=tempKirmizi;}
        else {karsi=tempKirmizi; biz=tempSiyah;}
        if(tempBoard.getItemFromPos(to)!=-1) tempBoard.items[tempBoard.getItemFromPos(to)].setPosition("xx");
        tempBoard.items[tempBoard.getItemFromPos(from)].setPosition(to);

        if(isCheck(biz,board) && isCheck(biz,tempBoard)) {
            return true;
        }
        else if(!isCheck(biz,board)&& isCheck(biz,tempBoard))return true;
        return false;
    }

    public boolean willCheck_illegalMoves(String from, String to, Board board) {
        Board tempBoard =tempBoardMaker(board);
        General tempKirmizi=null, tempSiyah=null;
        General biz=null, karsi = null;
        for(int x = 0; x<32; x++) {
            if(tempBoard.items[x] instanceof General) {
                if(tempBoard.items[x].getIsRedColor()) tempKirmizi=(General)tempBoard.items[x];
                else tempSiyah=(General)tempBoard.items[x];
            }
        }
        if(isRedTurn) {karsi=tempSiyah; biz=tempKirmizi;}
        else {karsi=tempKirmizi; biz=tempSiyah;}
        if(tempBoard.getItemFromPos(to)!=-1) tempBoard.items[tempBoard.getItemFromPos(to)].setPosition("xx");
        tempBoard.items[tempBoard.getItemFromPos(from)].setPosition(to);

        if(isCheck(biz,board) && isCheck(biz,tempBoard)) {
            return true;
        }
        else if(!isCheck(biz,board)&& isCheck(biz,tempBoard))return true;
        return false;
    }


    void willCheck_addThreats() {
        for(int i = 0; i<32; i++) {
            if(board.items[i].getPosition()!="xx" && !(board.items[i] instanceof General)) {//aktif bir taş olmalı
                if(board.items[i].getIsRedColor()) {//taş kırmızı, siyahGen'e gidebilecek mi?
                    if(board.items[i].canMove(siyahGen.getPosition(), board)) {
                        if(siyahGen.canMove("j4", board) || siyahGen.canMove("j5", board) || siyahGen.canMove("j6", board)
                                || siyahGen.canMove("i4", board) || siyahGen.canMove("i5", board) || siyahGen.canMove("i6", board)
                                || siyahGen.canMove("h4", board) || siyahGen.canMove("h5", board) || siyahGen.canMove("h6", board)) {
                            //şah çekildi ama siyah kaçabildi o zaman devam eder
                            siyahGen.threats.add(board.items[i]+""+board.items[i].getPosition());
                        }
                    }
                }else if(!board.items[i].getIsRedColor()) {//taş siyah, kırmızıGen'e gidebilecek mi?
                    if(board.items[i].canMove(kirmiziGen.getPosition(), board)) {
                        if(kirmiziGen.canMove("c4", board) || kirmiziGen.canMove("c5", board) || kirmiziGen.canMove("c6", board)
                                || kirmiziGen.canMove("b4", board) || kirmiziGen.canMove("b5", board) || kirmiziGen.canMove("b6", board)
                                || kirmiziGen.canMove("a4", board) || kirmiziGen.canMove("a5", board) || kirmiziGen.canMove("a6", board)) {
                            //şah çekildi ama kırmızı kaçabildi o zaman devam eder
                            kirmiziGen.threats.add(board.items[i]+""+board.items[i].getPosition());
                        }

                    }
                }
            }
        }
    }


    public boolean isCheck(General g, Board board) {
        boolean c = !(g.getIsRedColor());
        for(int u = 0; u<32; u++) {
            if(board.items[u].getIsRedColor()==c
                    && board.items[u].getPosition()!="xx"
                    && board.items[u].canMove(g.getPosition(),board)) return true;
        }
        return false;
    }


    public boolean isCheckmate(Board board, boolean isRedTurn) {
        Board tempBoard =tempBoardMaker(board);

        General tempKirmizi=null, tempSiyah=null;
        General biz=null, karsi = null;
        for(int x = 0; x<32; x++) {
            if(tempBoard.items[x] instanceof General) {
                if(tempBoard.items[x].getIsRedColor()) tempKirmizi=(General)tempBoard.items[x];
                else tempSiyah=(General)tempBoard.items[x];
            }
        }
        if(isRedTurn) {karsi=tempSiyah; biz=tempKirmizi;}
        else {karsi=tempKirmizi; biz=tempSiyah;}

        if(isCheck(karsi,tempBoard)) {
            for(int harf=0; harf<10; harf++) {
                for(int sayi=0; sayi<9; sayi++) {
                    String poss=(char)('a'+harf)+""+(sayi+1);
                    for(int zx=0; zx<32; zx++) {
                        if(tempBoard.items[zx].getIsRedColor()!=isRedTurn) {
                            if(tempBoard.items[zx].canMove(poss, tempBoard)
                                    && notAgainstNewRules(tempBoard.items[zx].getPosition(),poss,tempBoard)
                                    && !isFlyingGeneral(tempBoard.items[zx].getPosition(),poss,tempBoard)
                                    && !willCheck_illegalMoves(tempBoard.items[zx].getPosition(),poss,tempBoard)) {
                                if(tempBoard.getItemFromPos(poss)!=-1) tempBoard.items[tempBoard.getItemFromPos(poss)].setPosition("xx");
                                tempBoard.items[zx].setPosition(poss);
                                boolean ch = isCheck(karsi,tempBoard);
                                tempBoard =tempBoardMaker(board);
                                if(!ch) {
                                    return false; //not chcm8
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }return false;//basta check yoktu
    }
    @Override
    void save_binary(String address) {
        try {
            ObjectOutputStream binarysaver = new ObjectOutputStream(new FileOutputStream(address));

            binarysaver.writeObject(board);
            binarysaver.writeObject(red);
            binarysaver.writeObject(black);
            binarysaver.writeObject(kirmiziGen);
            binarysaver.writeObject(siyahGen);
            binarysaver.writeObject(isRedTurn);

            binarysaver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    void load_binary(String address) {
        try {
            ObjectInputStream binaryloader = new ObjectInputStream(new FileInputStream(address));

            board=(Board)binaryloader.readObject();
            red=(Player)binaryloader.readObject();
            black=(Player)binaryloader.readObject();
            kirmiziGen=(General)binaryloader.readObject();
            siyahGen=(General)binaryloader.readObject();
            isRedTurn=(boolean)binaryloader.readObject();

            binaryloader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void save_text(String address) {
        try {
            PrintWriter a=new PrintWriter(new FileOutputStream(address));
            for(int i = 0; i<32; i++) {
                String list = "";
                if(board.items[i] instanceof General) {
                    General tempGen = (General)board.items[i];
                    for(String s : tempGen.threats) {
                        list+=s+" ";
                    }
                }
                else {
                    for(String s : board.items[i].posHistory) {
                        list+=s+" ";
                    }
                }
                //format: ad lastpos firstpos1 firstpos2 ... firstposn
                a.println(board.items[i]+" "+board.items[i].getPosition()+" "+list);
            }
            a.println(red.name+" "+red.puan);
            a.println(black.name+" "+black.puan);
            a.println(isRedTurn);
            a.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void load_text(String address) {

        Item[]newItems=new Item[32];
        try {
            Scanner b = new Scanner(new FileInputStream(address));
            for(int i=0; i<32; i++) {
                Item blank = new Item();
                String itemInfo = b.nextLine();
                if(Character.isLowerCase(itemInfo.charAt(0))) {
                    blank.setIsRedColor(true);
                }
                else
                    blank.setIsRedColor(false);
                blank.name=itemInfo.charAt(0)+"";
                blank.setPosition(itemInfo.substring(2,4));
                ArrayList<String> newPosHistory=new ArrayList<String>();
                ArrayList<String> newThreats=new ArrayList<String>();
                if(!blank.name.equalsIgnoreCase("ş")  &&  itemInfo.length()>=7) {
                    Scanner historyScan = new Scanner(itemInfo.substring(5));
                    while(historyScan.hasNext()) {
                        newPosHistory.add(historyScan.next());
                    }
                }else if(blank.name.equalsIgnoreCase("ş")  &&  itemInfo.length()>=4){
                    Scanner threatScan = new Scanner(itemInfo.substring(5));
                    while(threatScan.hasNext()) {
                        newThreats.add(threatScan.next());
                    }
                }

                if(blank.name.equalsIgnoreCase("ş")) {
                    General tempGeneral=new General(blank.getIsRedColor(),blank.getPosition());
                    tempGeneral.threats=newThreats;
                    newItems[i]=tempGeneral;
                }
                else if(blank.name.equalsIgnoreCase("v")) {
                    newItems[i]=new Advisor(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else if(blank.name.equalsIgnoreCase("f")) {
                    newItems[i]=new Elephant(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else if(blank.name.equalsIgnoreCase("a")) {
                    newItems[i]=new Horse(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else if(blank.name.equalsIgnoreCase("k")) {
                    newItems[i]=new Chariot(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else if(blank.name.equalsIgnoreCase("t")) {
                    newItems[i]=new Cannon(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else if(blank.name.equalsIgnoreCase("p")) {
                    newItems[i]=new Soldier(blank.getIsRedColor(),blank.getPosition());
                    newItems[i].posHistory=newPosHistory;
                }
                else {

                }
            }

            board.items=newItems;//****
            String ww= b.nextLine();//bu a
            String s=ww.substring(0, ww.indexOf(".")-2);

            red = new Player(s,true);
            red.puan=Float.parseFloat(ww.substring(ww.indexOf(" ")+1));//****


            String xx= b.nextLine();//bu a
            String q=xx.substring(0, xx.indexOf(".")-2);

            black = new Player(q,true);
            black.puan=Float.parseFloat(xx.substring(xx.indexOf(" ")+1));//****

            boolean siraKirmizidaMi=false;
            if(b.nextLine().equals("true")) {
                siraKirmizidaMi=true;
            }
            isRedTurn=siraKirmizidaMi; //*****

            for(int i = 0; i<32; i++) {
                if(board.items[i].toString().equals("Ş")) {
                    siyahGen=(General)board.items[i];
                }
                else if(board.items[i].toString().equals("ş")) {
                    kirmiziGen=(General)board.items[i];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board tempBoardMaker(Board board){

        Board tempBoard = new Board();
        tempBoard.items=new Item[32];

        for(int i = 0; i<32; i++) {
            if(board.items[i] instanceof General) {
                General idk = (General)board.items[i];
                General tempGeneral=new General(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempGeneral.threats=idk.threats;
                tempBoard.items[i]=tempGeneral;
            }
            else if(board.items[i] instanceof Advisor) {
                Advisor idk = (Advisor)board.items[i];
                Advisor tempAdvisor=new Advisor(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempAdvisor.posHistory=idk.posHistory;
                tempBoard.items[i]=tempAdvisor;
            }
            else if(board.items[i] instanceof Elephant) {
                Elephant idk = (Elephant)board.items[i];
                Elephant tempElephant=new Elephant(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempElephant.posHistory=idk.posHistory;
                tempBoard.items[i]=tempElephant;
            }
            else if(board.items[i] instanceof Horse) {
                Horse idk = (Horse)board.items[i];
                Horse tempHorse=new Horse(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempHorse.posHistory=idk.posHistory;
                tempBoard.items[i]=tempHorse;
            }
            else if(board.items[i] instanceof Chariot) {
                Chariot idk = (Chariot)board.items[i];
                Chariot tempChariot=new Chariot(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempChariot.posHistory=idk.posHistory;
                tempBoard.items[i]=tempChariot;
            }
            else if(board.items[i] instanceof Cannon) {
                Cannon idk = (Cannon)board.items[i];
                Cannon tempCannon=new Cannon(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempCannon.posHistory=idk.posHistory;
                tempBoard.items[i]=tempCannon;
            }
            else if(board.items[i] instanceof Soldier) {
                Soldier idk = (Soldier)board.items[i];
                Soldier tempSoldier=new Soldier(board.items[i].getIsRedColor(),board.items[i].getPosition());
                tempSoldier.posHistory=idk.posHistory;
                tempBoard.items[i]=tempSoldier;
            }
        }
        return tempBoard;
    }
}
