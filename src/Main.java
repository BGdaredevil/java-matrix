import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input");
//        compareMatrix(sc);
//        matrixAdder(sc);
//        getMatrixIntersect(sc);
        sumMatrixElements(sc);

    }

    private static void sumMatrixElements(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;

        for (int row = 0; row < rowCol[0]; row++) {
            int[] readLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < rowCol[1]; col++) {
                sum += readLine[col];
            }
        }
        System.out.println(rowCol[0]);
        System.out.println(rowCol[1]);
        System.out.println(sum);
    }

    private static void getMatrixIntersect(Scanner sc) {
        int rows = Integer.parseInt(sc.nextLine());
        int cols = Integer.parseInt(sc.nextLine());

        String[][] initMatrix = new String[rows][cols];
        String[][] result = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] readLine = sc.nextLine().split(" ");
            System.arraycopy(readLine, 0, initMatrix[row], 0, cols);
        }

        for (int row = 0; row < rows; row++) {
            String[] readLine = sc.nextLine().split(" ");

            for (int col = 0; col < cols; col++) {
                result[row][col] = initMatrix[row][col].equals(readLine[col]) ? initMatrix[row][col] : "*";
            }
        }

        for (String[] row : result) {
            System.out.println(String.join(" ", row));
        }
    }

    private static void matrixAdder(Scanner sc) {
        int[] widthHeight = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] first = new int[widthHeight[0]][widthHeight[1]];
        int[][] result = new int[widthHeight[0]][widthHeight[1]];

        for (int rows = 0; rows < widthHeight[0]; rows++) {
            int[] row = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (widthHeight[1] >= 0) {
                System.arraycopy(row, 0, first[rows], 0, widthHeight[1]);
            }
        }

        for (int rows = 0; rows < widthHeight[0]; rows++) {
            int[] row = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int cols = 0; cols < widthHeight[1]; cols++) {
                result[rows][cols] = first[rows][cols] + row[cols];
            }
        }

        for (int[] item : result) {
            System.out.println(Arrays.stream(item).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
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
                if (first[row][col] != second[row][col]) {
                    System.out.println("not equal");
                    return;
                }
            }
        }

        System.out.println("equal");
    }
}