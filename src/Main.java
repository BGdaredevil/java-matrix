import java.util.ArrayDeque;
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
//        sumMatrixElements(sc);
//        getMax2x2(sc);
//        printDiagonals(sc);
//        sumDiagonals(sc);
        matrixLoader(sc);
    }

    private static void matrixLoader(Scanner sc) {
        String[] params = sc.nextLine().split(" ");
        int size = Integer.parseInt(params[0]);
        int num = 1;
        String mode = params[1];

        int[][] result = new int[size][size];

        if (mode.equals("A")) {
            for (int col = 0; col < size; col++) {
                for (int row = 0; row < size; row++) {
                    result[row][col] = num++;
                }
            }
            for (int[] row : result) {
                System.out.println(Arrays.stream(row).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            }
            return;
        }

        if (mode.equals("B")) {
            for (int col = 0; col < size; col++) {
                if (col % 2 == 1) {
                    for (int row = 0; row < size; row++) {
                        result[size - 1 - row][col] = num++;
                    }
                } else {
                    for (int row = 0; row < size; row++) {
                        result[row][col] = num++;
                    }
                }
            }

            for (int[] row : result) {
                System.out.println(Arrays.stream(row).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            }
            return;
        }

        System.out.println("invalid mode");
    }

    private static void sumDiagonals(Scanner sc) {
        int size = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];

        ArrayDeque<Integer> first = new ArrayDeque<>(size);
        ArrayDeque<Integer> second = new ArrayDeque<>(size);

        for (int row = 0; row < size; row++) {
            int[] readLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            first.add(readLine[row]);
            second.push(readLine[size - 1 - row]);
        }

        int sum = first.stream().mapToInt(Integer::intValue).sum() + second.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum);
    }

    ;

    private static void printDiagonals(Scanner sc) {
        int size = Integer.parseInt(sc.nextLine());

        ArrayDeque<Integer> first = new ArrayDeque<>(size);
        ArrayDeque<Integer> second = new ArrayDeque<>(size);

        for (int row = 0; row < size; row++) {
            int[] readLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            first.add(readLine[row]);
            second.push(readLine[size - 1 - row]);
        }

        System.out.println(first.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(second.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void getMax2x2(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] matrix = new int[rowCol[0]][rowCol[1]];

        for (int row = 0; row < rowCol[0]; row++) {
            int[] readLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(readLine, 0, matrix[row], 0, rowCol[1]);
        }

        int rows = rowCol[0];
        int cols = rowCol[1];
        int maxSum = Integer.MIN_VALUE;
        int[][] out = new int[2][2];

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                int sum = matrix[row][col] + matrix[row - 1][col] + matrix[row][col - 1] + matrix[row - 1][col - 1];

                if (sum > maxSum) {
                    maxSum = sum;
                    out[0][0] = matrix[row - 1][col - 1];
                    out[0][1] = matrix[row - 1][col];
                    out[1][0] = matrix[row][col - 1];
                    out[1][1] = matrix[row][col];
                }

            }
        }

        System.out.println(maxSum);
        for (int[] item : out) {
            System.out.println(Arrays.stream(item).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

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