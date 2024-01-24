import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input");
        compareMatrix(sc);
    }

    private static void compareMatrix(Scanner sc) {
        int[] widthHeightOne = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] first = new int[widthHeightOne[0]][widthHeightOne[1]];

        for (int i = 0; i < widthHeightOne[0]; i++) {
            int[] row = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (widthHeightOne[1] >= 0) {
                System.arraycopy(row, 0, first[i], 0, widthHeightOne[1]);
            }
        }

        int[] widthHeightTwo = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (widthHeightOne[0] != widthHeightTwo[0] || widthHeightOne[1] != widthHeightTwo[1]) {
            System.out.println("not equal");
            return;
        }

        int[][] second = new int[widthHeightTwo[0]][widthHeightTwo[1]];

        for (int i = 0; i < widthHeightTwo[0]; i++) {
            int[] row = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (widthHeightTwo[1] >= 0) {
                System.arraycopy(row, 0, second[i], 0, widthHeightTwo[1]);
            }
        }

        for (int row = 0; row < first.length; row++) {
            for (int col = 0; col < first[row].length; col++) {
                if (first[row][col] != second[row][col])  {
                    System.out.println("not equal");
                    return;
                }
            }
        }

        System.out.println("equal");
    }
}