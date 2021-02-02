import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    private final int MAX_WIDTH = 10;
    public View(){

    }

    public void displayTower(int[][] tower) {
        for (int i = 0; i < tower[0].length; i++) {
            for (int j = 0; j < tower.length; j++) {
                if (tower[j][i]==0) System.out.print(" ");
                else System.out.print(tower[j][i]);
            }
            System.out.println();
        }
        for (int[] x: tower) System.out.print("□");
        System.out.println();
    }

    public void displayMoves(ArrayList<int[][]> moves, int height) {
        for (int i = 0; i < MAX_WIDTH; i++) {
            System.out.print("□□□□□□□□");
        }
        System.out.println("□");
        for (int row = 0; row < Math.ceil(moves.size()/ (double) MAX_WIDTH); row++) {
            int[][][] rowTowers = new int[MAX_WIDTH][3][height];
            for (int cell = 0; cell < (moves.size()-MAX_WIDTH*row >= MAX_WIDTH ? MAX_WIDTH : moves.size()-MAX_WIDTH*row); cell++) {
                rowTowers[cell] = moves.get(MAX_WIDTH*row+cell);
            }
            for (int slice = 0; slice < height; slice++) {
                System.out.print("□");
                for (int i = 0; i < MAX_WIDTH; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(" ");
                        if (rowTowers[i][j][slice]==0) {
                            System.out.print(" ");
                        } else {
                            System.out.print(rowTowers[i][j][slice]);
                        }
                    }
                    System.out.print(" □");
                }
                System.out.println();
            }
            for (int i = 0; i < MAX_WIDTH; i++) {
                System.out.print("□□□□□□□□");
            }
            System.out.println("□");
        }
    }

    public void displayGreeter() {
        System.out.println("---- TOWER OF HANOI ----");
    }

    public int askForMenu(){
        System.out.println("Please enter a number (size of tower with min 3) Displays moves in towers under 10 height. Enter x to exit:");
        Scanner sc = new Scanner(System.in);
        try {
            int size = sc.nextInt();
            if (size<3) throw new InputMismatchException();
            return size;
        } catch (InputMismatchException e) {
            System.out.println("[Exit] Thank you for using.");
            return -1;
        }

    }

    public void displayStats(int height, int moves, long time) {
        System.out.println("A tower of height "+height+" was solved:");
        System.out.println("--- in "+ moves+" moves");
        System.out.println("--- in "+ String.format("%.3f", time/1000000000.0) + "seconds");
    }
}
