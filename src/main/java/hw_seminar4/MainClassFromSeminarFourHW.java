package hw_seminar4;

import java.util.*;

public class MainClassFromSeminarFourHW {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("task1");
        task1();
        System.out.println();

        System.out.println("task2");
        LinkedList<String> someList = new LinkedList<>(Arrays.asList("one", "two", "three", "four", "five", "six"));
        System.out.println(someList);
        backwardList(someList);
        System.out.println(someList);

    }

    public static void task1() {
        /*
        1. Реализовать консольное приложение, которое:
        Принимает от пользователя и “запоминает” строки.
        Если введено print, выводит строки так, чтобы последняя введенная была первой в списке, а первая - последней.
        Если введено revert, удаляет предыдущую введенную строку из памяти.
        */
        LinkedList<String> list = new LinkedList<>();
        System.out.println("""
                Программа сохранения и вывода строк.
                Для выхода введите 'exit'.
                Для вывода введите 'print'.
                Для удаления последней введённой строки введите 'revert'.""");
        while (true) {
            System.out.print("Ввод: ");
            String inputStr = scanner.nextLine();
            if (inputStr.equalsIgnoreCase("exit")) {
                break;
            } else if (inputStr.equalsIgnoreCase("revert")) {
                System.out.println("Удалён элемент - " + list.removeLast());
            } else if (inputStr.equalsIgnoreCase("print")) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    System.out.println(list.get(i));
                }
            } else {
                if (inputStr.trim().length() != 0) {
                    list.add(inputStr);
                    System.out.println("Добавлен элемент - " + inputStr);
                }
            }
        }
    }

    public static void backwardList(List<String> list) {
        /*
        2. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
        */
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            int indexFromBack = size - 1 - i;
            String tmp = list.get(i);
            list.set(i, list.get(indexFromBack));
            list.set(indexFromBack, tmp);
        }
    }
}
