package hw_seminar1;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class MainClassFromSeminarOneHW {
    public static void main(String[] args) {
        System.out.println("task1");
        int[] array = createArray(4, 5);
        System.out.println(Arrays.toString(array));

        System.out.println("task2");
        //2. Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 7, -23, 80};
        System.out.println(Arrays.toString(intArray));
        Arrays.sort(intArray);
        int min = intArray[0];
        int max = intArray[intArray.length - 1];
        System.out.printf("min : %d, max : %d", min, max);
        System.out.println();

        System.out.println("task3");
        int[][] arrayMatrix = generateSquareMatrix(5);
        printMatrix(arrayMatrix);

        System.out.println("task4");
        hello();

        System.out.println("task5");
        int[] arrayInt = {1, 1, 1, 0, 0, 0, 0, 0};
        System.out.println("before\t" + Arrays.toString(arrayInt));
        System.out.println("after\t" + Arrays.toString(displaceElements(arrayInt, 1)));

    }

    /*
     * 1. Написать метод, принимающий на вход два аргумента:
     * len и initialValue, и возвращающий одномерный массив
     * типа int длиной len, каждая ячейка которого равна initialValue;
     * */
    public static int[] createArray(int len, int initialValue) {
        int[] newArray = new int[len];
        Arrays.fill(newArray, initialValue);
        return newArray;
    }

    /*
     * 3. Создать квадратный двумерный целочисленный массив
     * (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
     * заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
     * Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов
     * равны, то есть [0][0], [1][1], [2][2], …, [n][n];
     * */
    public static int[][] generateSquareMatrix(int side) {
        int[][] matrix = new int[side][side];
        int reverseDot = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 1;
                }
                if (j == reverseDot) {
                    matrix[i][j] = 1;
                    reverseDot--;
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /*
     * 4. В консоли запросить имя пользователя. В зависимости от текущего времени, вывести приветствие вида:
     * "Доброе утро, <Имя>!", если время от 05:00 до 11:59
     * "Добрый день, <Имя>!", если время от 12:00 до 17:59;
     * "Добрый вечер, <Имя>!", если время от 18:00 до 22:59;
     * "Доброй ночи, <Имя>!", если время от 23:00 до 4:59
     * */
    public static void hello() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder printHello = new StringBuilder();
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        if ((hour >= 5) && (hour < 12)) {
            printHello.append("Доброе утро");
        } else if ((hour >= 12) && (hour < 18)) {
            printHello.append("Добрый день");
        } else if ((hour >= 18) && (hour < 23)) {
            printHello.append("Добрый вечер");
        } else {
            printHello.append("Доброй ночи");
        }
        if (name.equals("")) {
            printHello.append("!");
        } else {
            printHello.append(", ").append(name).append("!");
        }
        System.out.println(printHello);
    }

    /*
     * 5.** Написать метод, которому на вход подается одномерный массив и число n
     * (может быть положительным, или отрицательным), при этом метод должен
     * сместить все элементы массива на n позиций. Элементы смещаются циклично.
     * Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     * Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
     * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
     * При каком n в какую сторону сдвиг можете выбирать сами.
     * */
    public static int[] displaceElements(int[] arr, int shift) {
        boolean forward = shift > 0;
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        while (shift != 0) {
            int savedVal;
            int nextIndex;
            if (forward) {
                savedVal = arr[firstIndex];
                for (int i = 0; i < arr.length; i++) {
                    if (i == lastIndex) {
                        nextIndex = firstIndex;
                    } else {
                        nextIndex = i + 1;
                    }
                    int nextVal = arr[nextIndex];
                    arr[nextIndex] = savedVal;
                    savedVal = nextVal;
                }
                shift--;
            } else {
                savedVal = arr[lastIndex];
                for (int i = lastIndex; i >= 0; i--) {
                    if (i == firstIndex) {
                        nextIndex = lastIndex;
                    } else {
                        nextIndex = i - 1;
                    }
                    int nextVal = arr[nextIndex];
                    arr[nextIndex] = savedVal;
                    savedVal = nextVal;
                }
                shift++;
            }
        }
        return arr;
    }
}
