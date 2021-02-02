import java.util.ArrayList;

public class TowerModel {
    private int[][] tower;
    private ArrayList<int[][]> moves = new ArrayList<int[][]>();
    private final int DEFAULT_HEIGHT = 3;
    private final boolean DISABLE_MOVE_CHECK = false; // 10-20% faster
    private int height;
    private int numMoves;

    public TowerModel() {

    }
    public void init(int height) {
        this.height = height;
        this.tower = new int[3][height];
        this.numMoves = 0;
        for (int i = 0; i < tower[0].length; i++) {
            tower[0][i] = i+1;
        }
        moves.clear();
        moves.add(clone(tower));
    }

    private static int[][] clone(int[][] tower) {
        int[][] copy = new int[tower.length][tower[0].length];
        for (int i = 0; i < tower.length; i++) {
            for (int j = 0; j < tower[0].length; j++) {
                copy[i][j] = tower[i][j];
            }
        }
        return copy;
    }

    private void unsafeStep(int a, int b) {
        int block = 0;
        for (int i = 0; i < height; i++) {
            if (tower[a][i] != 0) {
                block = tower[a][i];
                tower[a][i]=0;
                break;
            }
        }
        for (int i = height-1; i >= 0; i--) {
            if (tower[b][i] == 0) {
                tower[b][i]=block;
                break;
            }
        }
        if(height <= 9) moves.add(clone(tower));
        numMoves++;
    }

    public void step(int a, int b) {
        if(DISABLE_MOVE_CHECK || checkStep(a,b)) {
            unsafeStep(a,b);
        } else {
            System.out.println("Error! Illegal move");
        }

    }

    private boolean checkStep(int a, int b) {
        if (tower[a][height-1]==0) {
            return false; // Check if a empty
        }
        if (tower[b][0]!=0) {
            return false; // Check if b full
        }
        int aNum = 0, bNum =0;
        for (int i = height-1; i >= 0; i--) {
            if (tower[a][i]!=0) {
                aNum = tower[a][i];
                break;
            }
        }
        for (int i = height-1; i >= 0; i--) {
            if (tower[b][i]!=0) {
                bNum = tower[b][i];
                break;
            }
        }
        return true;
    }


    public int[][] getTower() {
        return tower;
    }

    public ArrayList<int[][]> getMoves() {
        return moves;
    }

    public int getHeight() {
        return height;
    }

    public int getNumMoves() {
        return numMoves;
    }
}
