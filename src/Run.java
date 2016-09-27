import java.util.Scanner;


public class Run {
    Cell grid[][] = new Cell[10][10];
    boolean gameOver = false;
    randGen rg = new randGen();

    public void run(){
        for(int y=0; y<10; y++)
            for (int x = 0; x < 10; x++)
                grid[y][x] = new Cell();
        printGrid();
        setCarrier();

        while (!gameOver) {
            System.out.print("Column : ");
            int cCoord = new Scanner(System.in).nextInt();
            if(cCoord < 0 || cCoord > 10){
                System.out.println("Invalid Input");
                break;
            }

            System.out.print("Row : ");
            int rCoord = new Scanner(System.in).nextInt();
            if(rCoord < 0 || rCoord > 10) {
                System.out.println("Invalid Input");
                break;
            }

            // =========================== FORCE END GAME ===========================
            if(cCoord == 0 && rCoord == 0){
                revealGrid();
                break;
            }

            shot(rCoord-1, cCoord-1);
            printGrid();
            isGameOver();
        }
    }

    void setCarrier(){
        rg.carrierVal();
        // ============== DISTRIBUTE ACROSS ROW ==============
        if(rg.carrierRowCol == 0)
            for(int col=rg.carrierY; col<rg.carrierY+5; col++)
                    this.grid[rg.carrierX][col].shipType = "C";
        // ============== DISTRIBUTE ACROSS COLUMN ==============
        else
            for(int row=rg.carrierX; row<rg.carrierX+5; row++)
                    this.grid[row][rg.carrierY].shipType = "C";
        setDestroyer();
    }

    void setDestroyer(){
        int verified = 0;

        while (verified != 4){
            rg.destroyerVal();
            if(rg.destroyerRowCol == 0)
                for(int col=rg.destroyerX; col<rg.destroyerX+4; col++){
                    if(this.grid[rg.destroyerY][col].shipType.equals("x"))
                        verified++;
                    else
                        verified = 0;
                }
            else
                for(int row=rg.destroyerY; row<rg.destroyerY+4; row++){
                    if(this.grid[row][rg.destroyerX].shipType.equals("x"))
                        verified++;
                    else
                        verified = 0;
                }
        }
        if(rg.destroyerRowCol == 0)
            for(int col=rg.destroyerX; col<rg.destroyerX+4; col++)
                this.grid[rg.destroyerY][col].shipType = "D";
        else
            for(int row=rg.destroyerY; row<rg.destroyerY+4; row++)
                this.grid[row][rg.destroyerX].shipType = "D";
        setSub();
    }

    void setSub(){
        int subX = 0;
        int subY = 0;

        for(int i=0; i<10; i++){
            for(int j=0; j<8; j++){
                if (this.grid[i][j].shipType.equals("x")
                        && this.grid[i][j+1].shipType.equals("x")
                        && this.grid[i][j+2].shipType.equals("x")) {
                    subX = i;
                    subY = j;
                    break;
                }
            }
        }
        this.grid[subX][subY].shipType = "S";
        this.grid[subX][subY+1].shipType = "S";
        this.grid[subX][subY+2].shipType = "S";
        setPatrol();
    }

    void setPatrol(){
        int patrolX = 0;
        int patrolY = 0;

        for(int i=0; i<10; i++){
            for(int j=0; j<8; j++){
                if (this.grid[i][j].shipType.equals("x")
                        && this.grid[i][j+1].shipType.equals("x")
                        && this.grid[i][j+2].shipType.equals("x")) {
                    patrolX = j;
                    patrolY = i;
                    break;
                }
            }
        }
        this.grid[patrolX][patrolY].shipType = "P";
        this.grid[patrolX+1][patrolY].shipType = "P";
        this.grid[patrolX+2][patrolY].shipType = "P";
    }

    void printGrid(){
        System.out.println();
        for(int rowIndex=1; rowIndex<11; rowIndex++)
            System.out.print("\t" + rowIndex);
        System.out.println();

        for(int row=0; row<10; row++){
            System.out.print(row+1);
            for (int col=0; col<10; col++)
                System.out.print("\t" + this.grid[row][col].showCell());
            System.out.println();
        }
    }

    // ========================= DEVELOPER METHOD =========================
    private void revealGrid(){
        System.out.println();
        for(int rowIndex=1; rowIndex<11; rowIndex++)
            System.out.print("\t" + rowIndex);
        System.out.println();

        for(int row=0; row<10; row++){
            System.out.print(row+1);
            for (int col=0; col<10; col++)
                System.out.print("\t" + this.grid[row][col].shipType);
            System.out.println();
        }
    }

    void shot(int r, int c){ grid[r][c].hit(); }

    void isGameOver(){
        if(Cell.shipsCellsRemaining == 0){
            this.gameOver = true;
            System.out.println("========== YOU WIN ==========");
        }
    }
}
