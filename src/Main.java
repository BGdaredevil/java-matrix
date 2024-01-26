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
//        matrixLoader(sc);
//        matrixSumRowsCols(sc);
//        zeroRowCol(sc);
//        sumBoundary(sc);
//        rotateMatrix(sc);
//        excelColumnNames(sc);
//        checkChess(sc);
//        borderFlip(sc);
//        checkMagicSqare(sc);
//        traverseSpiral(sc);
//        checkerBoard(sc);
//        getMax3x3(sc);
        snowFlakes(sc);

    }

    private static void snowFlakes(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];
        boolean madeChange = true;
        String[][] board = new String[rows][cols];
        String[][] prevBoard = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] rowLine = sc.nextLine().split(" ");
            System.arraycopy(rowLine, 0, board[row], 0, cols);
            System.arraycopy(rowLine, 0, prevBoard[row], 0, cols);
        }

        while (madeChange) {
            madeChange = false;

            for (String[] resRow : board) {
                System.out.println(String.join(" ", resRow));
            }

            for (int row = 1; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    String prevSymbol = prevBoard[row - 1][col];
                    String currSymbol = board[row][col];

                    if (prevSymbol.equals("0")) {
                        continue;
                    }

                    if (prevSymbol.equals("#")) {
                        continue;
                    }

                    if (prevSymbol.equals("*") && currSymbol.equals("#")) {
                        continue;
                    }

                    if (prevSymbol.equals("*") && currSymbol.equals("0")) {
                        board[row][col] = prevSymbol;
                        board[row - 1][col] = "0";
                        madeChange = true;

                        continue;
                    }

                    madeChange = false;
                }
            }

            for (int i = 0; i < rows; i++) {
                String[] resRow = board[i];
//                System.out.println(String.join(" ", resRow));
                System.arraycopy(resRow, 0, prevBoard[i], 0, cols);
            }

            if (madeChange) {
                System.out.println("=".repeat(cols * 2 - 1));
            }

        }

    }

    private static void getMax3x3(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];
        int result = Integer.MIN_VALUE;
        int[][] bestMatrix = new int[3][3];

        int[][] board = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] rowLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(rowLine, 0, board[row], 0, cols);
        }

        for (int row = 0; row < rows; row++) {
            if ((row + 3) > rows) {
                continue;
            }

            for (int col = 0; col < cols; col++) {
                if ((col + 3) > cols) {
                    continue;
                }
                int[][] tempMatrix = new int[3][3];
                int tempSum = 0;

                for (int r = 0; r < (3); r++) {
                    for (int c = 0; c < (3); c++) {
                        tempSum += board[r + row][c + col];
                        tempMatrix[r][c] = board[r + row][c + col];
                    }
                }

                if (tempSum > result) {
                    result = tempSum;
                    bestMatrix = tempMatrix;
                }
            }
        }

        System.out.println("Sum = " + result);
        for (int[] resRow : bestMatrix) {
            System.out.println(Arrays.stream(resRow).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

    private static void checkerBoard(Scanner sc) {
        int size = sc.nextInt();
        boolean reverse = true;

        StringBuilder result = new StringBuilder(size * size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (reverse) {
                    result.append(col % 2).append(" ");
                } else {
                    result.append((col + 1) % 2).append(" ");
                }
            }
            reverse = !reverse;
            result.append("\n");
        }
        System.out.println(result);
    }

    private static void traverseSpiral(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        int[][] board = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] rowLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(rowLine, 0, board[row], 0, cols);
        }

        StringBuilder result = new StringBuilder(rows * cols);

        int startC = 0;
        int endC = cols;
        int startR = 0;
        int endR = rows;

        while (startC < endC && startR < endR) {
            for (int i = startC; i < endC; ++i) {
                result.append(board[startR][i]).append(" ");
            }
            startR++;
            for (int i = startR; i < endR; ++i) {
                result.append(board[i][endC - 1]).append(" ");
            }
            endC--;
            if (startR < endR) {
                for (int i = endC - 1; i >= startC; --i) {
                    result.append(board[endR - 1][i]).append(" ");
                }
                endR--;
            }
            if (startC < endC) {
                for (int i = endR - 1; i >= startR; --i) {
                    result.append(board[i][startC]).append(" ");
                }
                startC++;
            }
        }
        System.out.println(result);
    }

    private static void checkMagicSqare(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        int[] rowSums = new int[rows];
        int[] colSums = new int[cols];

        ArrayDeque<Integer> first = new ArrayDeque<>(rows);
        ArrayDeque<Integer> second = new ArrayDeque<>(cols);

        int[][] board = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] rowLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(rowLine, 0, board[row], 0, cols);

            first.add(rowLine[row]);
            second.push(rowLine[rows - 1 - row]);

            for (int col = 0; col < cols; col++) {
                rowSums[row] += rowLine[col];
                colSums[col] += rowLine[col];
            }
        }

        int sumFirst = first.stream().mapToInt(Integer::intValue).sum();
        int sumSecond = second.stream().mapToInt(Integer::intValue).sum();

        boolean sameRows = Arrays.stream(rowSums).allMatch(value -> value == sumFirst);
        boolean sameCols = Arrays.stream(colSums).allMatch(value -> value == sumFirst);

        if (sumFirst == sumSecond && sameRows && sameCols) {
            System.out.println("True");
        } else {
            System.out.println("False");

        }
    }

    private static void borderFlip(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        int[][] board = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] rowLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(rowLine, 0, board[row], 0, cols);
        }

        int r = 0;
        int c = 1;
        int temp = board[0][0];

        while (true) {
            int curr = board[r][c];
            board[r][c] = temp;
            temp = curr;

            if (r == 0 && c == 0) {
                break;
            }
            if (c < cols - 1 && r == 0) {
                c++;
                continue;
            }
            if (c == cols - 1 && r < rows - 1) {
                r++;
                continue;
            }
            if (c > 0 && r == rows - 1) {
                c--;
                continue;
            }
            if (c == 0) {
                r--;
                continue;
            }
            break;
        }

        for (int[] resRow : board) {
            System.out.println(Arrays.stream(resRow).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static void checkChess(Scanner sc) {
        // todo rework to check diagonals below only

        String result = "No";
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        int[][] board = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] rowLine = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(rowLine, 0, board[row], 0, cols);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int item = board[row][col];
                if (item == 0) {
                    continue;
                }

                int rowOcc = (int) Arrays.stream(board[row]).filter(e -> e == 1).count();
                if (rowOcc > 1) {
                    result = "Yes";
                    System.out.println(result);
                    return;
                }

                int colOcc = 0;
                for (int r = 0; r < rows; r++) {
                    if (board[r][col] == 1) {
                        colOcc++;
                    }
                }

                if (colOcc > 1) {
                    result = "Yes";
                    System.out.println(result);
                    return;
                }

                int firstR = 0;
                int firstC = (col - row);
                int secondR = 0;
                int secondC = (col + row);

                int firstFound = 0;
                while (firstR < rows && firstC < cols) {
                    if (firstC < 0) {
                        firstR++;
                        firstC++;
                        continue;
                    }
                    if (board[firstR][firstC] == 1) {
                        firstFound++;
                        if (firstFound > 1) {
                            result = "Yes";
                            System.out.println(result);
                            return;
                        }
                    }
                    firstR++;
                    firstC++;
                }

                int secondFound = 0;
                while (secondR < rows && secondC >= 0) {
                    if (secondC >= cols) {
                        secondR++;
                        secondC--;
                        continue;
                    }
                    if (board[secondR][secondC] == 1) {
                        secondFound++;
                        if (secondFound > 1) {
                            result = "Yes";
                            System.out.println(result);
                            return;
                        }
                    }

                    secondR++;
                    secondC--;
                }

                /*
                 * 0 - 0 - 0        0 0 0 0 0 0
                 * 0 0 1 0 0        0 0 0 0 0 0
                 * 0 - 0 - 0        0 0 0 0 0 0
                 * - 0 0 0 -        0 1 0 0 0 0
                 * 0 0 0 0 0        0 0 0 0 0 0
                 *                  0 0 0 0 0 0

                 */

            }
        }

        System.out.println(result);
    }

    private static void excelColumnNames(Scanner sc) {
        char[] input = sc.nextLine().toUpperCase().toCharArray();
        int result = 0;
        ArrayDeque<Integer> base26 = new ArrayDeque<>(input.length);
        for (char item : input) {
            int code = item - 'A' + 1;
            base26.push(code);
        }

        int size = base26.size();
        for (int i = 0; i < size; i++) {
            result += base26.pop() * (int) Math.pow(26, i);
        }

        System.out.println(result);
    }

    private static void rotateMatrix(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];
        int[][] result = new int[cols][rows];

        for (int row = 0; row < rows; row++) {
            int[] lineRow = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int col = 0; col < cols; col++) {
                result[col][rows - 1 - row] = lineRow[col];
            }
        }

        for (int[] item : result) {
            System.out.println(Arrays.stream(item).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static void sumBoundary(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];
        int sum = 0;

        for (int row = 0; row < rows; row++) {
            int[] lineRow = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (row == 0 || row == rows - 1) {
                sum += Arrays.stream(lineRow).sum();
                continue;
            }

            sum += lineRow[0] + lineRow[cols - 1];
        }

        System.out.println(sum);
    }

    private static void zeroRowCol(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        int[][] matrix = new int[rows][cols];
        int[][] result = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] lineRow = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = lineRow[col];
                result[row][col] = matrix[row][col];
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    for (int r = 0; r < rows; r++) {
                        result[r][col] = 0;
                    }

                    for (int c = 0; c < cols; c++) {
                        result[row][c] = 0;
                    }
                }
            }
        }

        for (int[] resRow : result) {
            System.out.println(Arrays.stream(resRow).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static void matrixSumRowsCols(Scanner sc) {
        int[] rowCol = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] rowSums = new int[rowCol[0]];
        int[] colSums = new int[rowCol[1]];

        for (int row = 0; row < rowCol[0]; row++) {
            int[] lineRow = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < rowCol[1]; col++) {
                rowSums[row] += lineRow[col];
                colSums[col] += lineRow[col];
            }
        }

        System.out.println("Row sums: " + Arrays.stream(rowSums).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println("Column sums: " + Arrays.stream(colSums).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
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