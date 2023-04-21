package hw_seminar3;

import java.util.*;

public class MainClassFromSeminarThreeHW {
    public static void main(String[] args) {
        System.out.println("task1");
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList("air", "milk", "40", "banana", "-20", "777", "train"));
        System.out.println(list);
        removeIntFromList(list);
        System.out.println(list);
        System.out.println();

        System.out.println("task2");
        List<ArrayList<String>> booksStorage = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.print("Добавить книгу в каталог(yes/no): ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("yes") || choice.equals("y")) {
                addBook(booksStorage, getGenre(), getBookName());
                System.out.println("Текущий каталог книг:");
                System.out.println(booksStorage);
            } else {
                stop = true;
            }
        }
        System.out.println();

        System.out.println("task3");
        ArrayList<String> planetsList = getPlanetsList(20);
        System.out.println(planetsList);
        removeRepeats(planetsList);
        System.out.println(planetsList);

    }

    public static void removeIntFromList(ArrayList<String> arrayList) {
        /*
        1. Создать список типа ArrayList<String>. Поместить в него как строки, так и целые числа.
        Пройти по списку, найти и удалить целые числа.
        Пример: {"Яблоко", "11", "13", "Апельсин", "Дыня", "17"} -> {"Яблоко", "Апельсин", "Дыня"}
        */
        int index = 0;
        while (index < arrayList.size()) {
            String currentElement = arrayList.get(index);
            try {
                Integer.parseInt(currentElement);
                arrayList.remove(index);
            } catch (Exception e) {
                index++;
            }
        }
    }

    public static void addBook(List<ArrayList<String>> books, String genre, String bookName) {
        /*
        2. Каталог товаров книжного магазина сохранен в виде двумерного списка List<ArrayList<String>> так,
            что на 0й позиции каждого внутреннего списка содержится название жанра,
        а на остальных позициях - названия книг.
        Напишите метод для заполнения данной структуры(можно через консоль).
        Пример:
        "Классика", "Преступление и наказание", "Война и мир", "Анна Каренина".
        "Научная фантастика", "Солярис", "Ночной дозор", "Братья Стругацкие".
        "Детектив", "Десять негритят".
        "Фантастика", "Хроники Нарнии", "Гарри Поттер и философский камень", "Грозовой перевал".
        */
        if (books.size() == 0) {
            books.add(new ArrayList<>(Arrays.asList(genre, bookName)));
        } else {
            ArrayList<String> existGenres = new ArrayList<>();
            int genreIndex = 0;
            for (ArrayList<String> book : books) {
                String existGenre = book.get(genreIndex);
                existGenres.add(existGenre);
            }
            int indexAddedGenre = existGenres.indexOf(genre);
            if (indexAddedGenre >= 0) {
                if (!books.get(indexAddedGenre).contains(bookName)) {
                    books.get(indexAddedGenre).add(bookName);
                }
            } else {
                books.add(new ArrayList<>(Arrays.asList(genre, bookName)));
            }
        }
    }

    public static String getGenre() {
        Scanner scanner = new Scanner(System.in);
        String genre = "";
        while (genre.length() == 0) {
            System.out.print("Введите жанр: ");
            genre = scanner.nextLine();
        }
        return genre;
    }

    public static String getBookName() {
        Scanner scanner = new Scanner(System.in);
        String bookName = "";
        while (bookName.length() == 0) {
            System.out.print("Введите название книги: ");
            bookName = scanner.nextLine();
        }
        return bookName;
    }

    public static ArrayList<String> getPlanetsList(int listLen) {
        /*
        Заполнить список названиями планет Солнечной системы в произвольном порядке с повторениями.
        Вывести название каждой планеты и количество его повторений в списке.
        */
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Neptune", "Uranus", "Pluto"};
        ArrayList<String> list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < listLen; i++) {
            int randomPlanetIndex = rnd.nextInt(planets.length);
            list.add(planets[randomPlanetIndex]);
        }
        return list;
    }

    public static void removeRepeats(ArrayList<String> list) {
        /*
        Пройти по списку из предыдущего задания и удалить повторяющиеся элементы.
        */
        Collections.sort(list);
        int index = 0;
        while (index < list.size() - 1) {
            if (list.get(index).equals(list.get(index + 1))) {
                list.remove(index);
            } else {
                index++;
            }
        }
    }

}
