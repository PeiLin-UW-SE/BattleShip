import java.lang.String;

public class Cell{
    boolean isHit;
    String shipType;
    public static int shipsCellsRemaining = 5+4+3+3;

    Cell(){
        this.isHit = false;
        this.shipType = "x";
    }

    void hit(){
        this.isHit = true;
        if(!this.shipType.equals("x"))
            shipsCellsRemaining--;
    }

    String showCell(){
        if(isHit)
            return this.shipType;
        return "-";
    }
}