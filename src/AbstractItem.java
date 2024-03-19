
public abstract class AbstractItem implements ItemInterface {

    private String position;  // tahtadaki konumu gösterir. Örneğin, a1

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
