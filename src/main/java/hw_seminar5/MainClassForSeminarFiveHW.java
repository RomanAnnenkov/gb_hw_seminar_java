package hw_seminar5;

import java.util.*;

public class MainClassForSeminarFiveHW {
    public static void main(String[] args) {
        System.out.println("task1");
        Map<String, String> pb = createPhoneBook();
        printNameCount(pb);
        System.out.println();

        System.out.println("task2");
        printFieldAndCoordinates();
    }

    /*
    1. Реализуйте структуру телефонной книги с помощью HashMap,
    учитывая, что 1 человек может иметь несколько телефонов.
    <список сотрудников>
    Написать программу, которая найдёт и выведет повторяющиеся имена с количеством повторений.
    Отсортировать по убыванию популярности.
    */
    public static Map<String, String> createPhoneBook() {
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("Иван Иванов", "123456,98765");
        phoneBook.put("Светлана Петрова", "123456,98765");
        phoneBook.put("Кристина Белова", "123456,98765");
        phoneBook.put("Анна Мусина", "123456,98765");
        phoneBook.put("Анна Крутова", "123456,98765");
        phoneBook.put("Иван Юрин", "123456,98765");
        phoneBook.put("Петр Лыков", "123456,98765");
        phoneBook.put("Павел Чернов", "123456,98765");
        phoneBook.put("Петр Чернышов", "123456,98765");
        phoneBook.put("Мария Федорова", "123456,98765");
        phoneBook.put("Марина Светлова", "123456,98765");
        phoneBook.put("Мария Савина", "123456,98765");
        phoneBook.put("Мария Рыкова", "123456,98765");
        phoneBook.put("Марина Лугова", "123456,98765");
        phoneBook.put("Анна Владимирова", "123456,98765");
        phoneBook.put("Иван Мечников", "123456,98765");
        phoneBook.put("Петр Петин", "123456,98765");
        phoneBook.put("Иван Ежов", "123456,98765");
        return phoneBook;
    }

    public static void printNameCount(Map<String, String> book) {
        int nameIndex = 0;
        List<String> names = new ArrayList<>(book.keySet().stream().map(s -> s.split(" ")[nameIndex]).toList());
        Set<String> uniqueNames = new HashSet<>(names);
        Map<String, Integer> mapNameCount = new HashMap<>();
        for (String name : uniqueNames) {
            int count = Collections.frequency(names, name);
            mapNameCount.put(name, count);
        }
        List<Integer> values = new ArrayList<>();
        for (int value : mapNameCount.values()) {
            if (!values.contains(value)) {
                values.add(value);
            }
        }
        values.sort(Comparator.reverseOrder());
        for (int value : values) {
            for (String name : mapNameCount.keySet()) {
                if (value == mapNameCount.get(name)) {
                    System.out.println(name + " " + value);
                }
            }
        }
    }

    /*
    2. На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
    */
    public static void printFieldAndCoordinates() {
        int[][] field = new int[8][8];
        String[] coordinatesX = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] coordinatesY = new String[]{"8", "7", "6", "5", "4", "3", "2", "1"};
        StringBuilder queenCoordinates = new StringBuilder();
        List<Integer> horizontal = new ArrayList<>();
        List<Integer> vertical = new ArrayList<>();
        List<Integer> leftRightDiagonal = new ArrayList<>();
        List<Integer> rightLeftDiagonal = new ArrayList<>();
        int queen = 2;
        int countQueen = 0;
        int mark = 1;
        int i = 0;
        int j = 0;
        int emptyCell = 0;
        do {
            if (j > field.length - 1) {
                i++;
                if (i > field.length - 1) {
                    i = 0;
                    markPreviousQueen(field, mark);
                    countQueen = 0;
                    queenCoordinates.delete(0, queenCoordinates.length());
                }
                j = 0;
            }
            int currentCell = field[i][j];
            if (currentCell == mark) {
                j++;
                continue;
            }
            getHorizontal(horizontal, i, field);
            getVertical(vertical, j, field);
            getLeftRightDiagonal(leftRightDiagonal, i, j, field);
            getRightLeftDiagonal(rightLeftDiagonal, i, j, field);
            if (!horizontal.contains(queen) &&
                    !vertical.contains(queen) &&
                    !leftRightDiagonal.contains(queen) &&
                    !rightLeftDiagonal.contains(queen) &&
                    currentCell == emptyCell
            ) {
                field[i][j] = queen;
                countQueen++;
                queenCoordinates.append(coordinatesX[j]).append(coordinatesY[i]).append(" ");
                j = field.length;
            }
            j++;

        } while (countQueen != 8);


        printField(field, coordinatesX, coordinatesY, queen);
        System.out.print("Координаты расположения ферзей: ");
        System.out.println(queenCoordinates);
    }

    public static void getHorizontal(List<Integer> horizontal, int iIndex, int[][] fieldMatrix) {
        horizontal.clear();
        for (int i = 0; i < fieldMatrix.length; i++) {
            horizontal.add(fieldMatrix[iIndex][i]);
        }
    }

    public static void getVertical(List<Integer> vert, int jIndex, int[][] fieldMatrix) {
        vert.clear();
        for (int[] matrix : fieldMatrix) {
            vert.add(matrix[jIndex]);
        }
    }

    public static void getLeftRightDiagonal(List<Integer> diagonalLeftRight, int iIndex, int jIndex, int[][] fieldMatrix) {
        diagonalLeftRight.clear();
        int i = iIndex;
        int j = jIndex;
        while (i > 0 && j > 0) {
            i--;
            j--;
        }
        int maxLen = i > j ? fieldMatrix.length - i : fieldMatrix.length - j;
        for (int k = 0; k < maxLen; k++) {
            diagonalLeftRight.add(fieldMatrix[i][j]);
            i++;
            j++;
        }
    }

    public static void getRightLeftDiagonal(List<Integer> diagonalRightLeft, int iIndex, int jIndex, int[][] fieldMatrix) {
        diagonalRightLeft.clear();
        int i = iIndex;
        int j = jIndex;
        while (i > 0 && j < 7) {
            i--;
            j++;
        }
        int maxLen = i > j ? i - j : j - i;
        for (int k = 0; k < maxLen; k++) {
            diagonalRightLeft.add(fieldMatrix[i][j]);
            i++;
            j--;
        }
    }

    public static void printField(int[][] fieldMatrix, String[] coordinatesX, String[] coordinatesY, int queenMark) {
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j == 0) {
                    System.out.print(coordinatesY[i] + "\t");
                }
                if (fieldMatrix[i][j] == queenMark) {
                    System.out.print("Q\t");
                } else {
                    System.out.print("_\t");
                }
            }
            System.out.println();
        }
        System.out.print(" \t");
        for (String x : coordinatesX) {
            System.out.print(x + "\t");
        }
        System.out.println();
    }

    public static void markPreviousQueen(int[][] fieldMatrix, int mark) {
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (fieldMatrix[i][j] > 0) {
                    fieldMatrix[i][j] = mark;
                }
            }
        }
    }

}

