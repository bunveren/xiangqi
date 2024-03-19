import java.io.Serializable;
public class Player implements Serializable{
    float puan;
    String name;
    boolean red;

    public Player(String name, boolean red) {
        this.name=name;
        this.red=red;
        puan=0;
    }

    public float getPuan() {
        return puan;
    }
}
