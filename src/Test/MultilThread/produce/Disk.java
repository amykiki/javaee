package Test.MultilThread.produce;

/**
 * Created by Amysue on 2016/1/21.
 */
public class Disk {
    private String  food;
    private boolean empty;
    private boolean end;

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Disk() {
        empty = true;
        end = false;

    }

    public String getFood() {

        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
