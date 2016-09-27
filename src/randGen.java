import java.util.Random;


public class randGen {
    int carrierX;
    int carrierY;
    int carrierRowCol;

    int destroyerX;
    int destroyerY;
    int destroyerRowCol;

    public int rowCol(){
        Random rowColRand = new Random();
        return rowColRand.nextInt(2);
    }

    public void carrierVal(){
        this.carrierRowCol = rowCol();
        Random carrierRand = new Random();
        this.carrierX = carrierRand.nextInt(6);
        this.carrierY = carrierRand.nextInt(6);
    }

    public void destroyerVal(){
        this.destroyerRowCol = (this.carrierRowCol + 1) % 2;
        Random destroyerRand = new Random();
        this.destroyerX = destroyerRand.nextInt(8);
        this.destroyerY = destroyerRand.nextInt(8);
    }
}
