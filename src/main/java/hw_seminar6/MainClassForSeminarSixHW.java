package hw_seminar6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainClassForSeminarSixHW {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        1. Подумать над структурой класса Ноутбук для
        магазина техники — выделить поля и методы. Реализовать в Java.
        Создать множество ноутбуков.
        Написать метод, который будет запрашивать у пользователя критерий
        (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру.
        */
        HashSet<Notebook> notebooks = getNotebooks();

        System.out.println("Программа для реализации фильтра множества ноутбуков.");
        String help = """
                Для выхода введите 'exit'.
                Для задания параметров фильтра введите 'filter'.
                Для сброса параметров фильтра введите 'clear'.
                Для применения фильтра и вывода результата введите 'print'.""";
        System.out.println(help);
        HashMap<String, String> filterParameters = new HashMap<>();
        while (true) {
            System.out.print("Ввод: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("filter")) {
                setFilterParameters(filterParameters);
            } else if (input.equalsIgnoreCase("clear")) {
                filterParameters.clear();
            } else if (input.equalsIgnoreCase("print")) {
                if (filterParameters.size() == 0) {
                    for (Notebook nb : notebooks) {
                        System.out.println(nb);
                    }
                } else {
                    applyFilterAndPrint(notebooks, filterParameters);
                }
            } else {
                System.out.println("'" + input + "'" + " - команда не найдена");
                System.out.println(help);
            }
        }
    }

    public static void setFilterParameters(HashMap<String, String> filterParameters) {
        String inputParameter;

        do {
            System.out.println("""
                    Параметры фильтра:
                    1 - ОЗУ(Gb)
                    2 - Объём ЖД(Gb)
                    3 - Бренд
                    4 - Цвет
                    5 - Цена""");

            System.out.print("Введите цифру, соответствующую необходимому параметру: ");
            inputParameter = scanner.nextLine();
            if (inputParameter.matches("^[3-4]$")) {
                System.out.print("введите значение: ");
            } else if (inputParameter.matches("^[1,2,5]$")) {
                System.out.print("введите минимальное значение: ");
            } else {
                System.out.println("'" + inputParameter + "'" + " - параметр не существует");
            }

            switch (inputParameter) {
                case "1" -> {
                    String ram = scanner.nextLine();
                    if (ram.matches("^\\d+$")) {
                        filterParameters.put("ОЗУ(Gb)", ram);
                    } else {
                        System.out.println("параметр не добавлен, введите числовое значение");
                    }
                }
                case "2" -> {
                    String disk = scanner.nextLine();
                    if (disk.matches("^\\d+$")) {
                        filterParameters.put("Объём ЖД(Gb)", disk);
                    } else {
                        System.out.println("параметр не добавлен, введите числовое значение");
                    }
                }
                case "3" -> filterParameters.put("Бренд", scanner.nextLine());
                case "4" -> filterParameters.put("Цвет", scanner.nextLine());
                case "5" -> {
                    String price = scanner.nextLine();
                    if (price.matches("^\\d+$")) {
                        filterParameters.put("Цена", price);
                    } else {
                        System.out.println("параметр не добавлен, введите числовое значение");
                    }
                }
            }

            System.out.print("Параметры: ");
            System.out.println(filterParameters.toString());
            System.out.print("Задать еще параметры(y/n): ");
            inputParameter = scanner.nextLine();


        } while (inputParameter.equalsIgnoreCase("y"));
    }

    public static void applyFilterAndPrint(HashSet<Notebook> notebooks, HashMap<String, String> parameters) {
        HashSet<Notebook> result = new HashSet<>(notebooks);
        int minRamGB = 0;
        int minStorageCapacityGb = 0;
        String brand = "";
        String color = "";
        int minPrice = 0;
        for (String parameter : parameters.keySet()) {
            switch (parameter) {
                case "ОЗУ(Gb)" -> minRamGB = Integer.parseInt(parameters.get(parameter));
                case "Объём ЖД(Gb)" -> minStorageCapacityGb = Integer.parseInt(parameters.get(parameter));
                case "Бренд" -> brand = parameters.get(parameter);
                case "Цвет" -> color = parameters.get(parameter);
                case "Цена" -> minPrice = Integer.parseInt(parameters.get(parameter));
            }
        }
        for (Notebook nb : notebooks) {
            if ((minRamGB != 0 && nb.ramGb < minRamGB) ||
                    (minStorageCapacityGb != 0 && nb.storageCapacityGb < minStorageCapacityGb) ||
                    (minPrice != 0 && nb.price != minPrice) ||
                    (!brand.isEmpty() && !nb.brand.equalsIgnoreCase(brand)) ||
                    (!color.isEmpty() && !nb.color.equalsIgnoreCase(color))
            ) {
                result.remove(nb);
            }
        }
        if (result.size() == 0) {
            System.out.println("Нет элементов соответствующих фильтру.");
        }
        for (Notebook nb : result) {
            System.out.println(nb);
        }
    }

    public static HashSet<Notebook> getNotebooks() {
        HashSet<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("HP", "H4", 8, "hdd", 1024, "black", 10000));
        notebooks.add(new Notebook("HP", "H5", 16, "ssd", 256, "green", 20000));
        notebooks.add(new Notebook("HP", "H6", 32, "ssd", 512, "yellow", 30000));
        notebooks.add(new Notebook("Lenovo", "L10", 8, "hdd", 1024, "black", 10000));
        notebooks.add(new Notebook("Lenovo", "L20", 16, "ssd", 256, "purple", 20000));
        notebooks.add(new Notebook("Lenovo", "L30", 32, "ssd", 512, "silver", 30000));
        notebooks.add(new Notebook("Asus", "A500", 16, "ssd", 128, "red", 20000));
        notebooks.add(new Notebook("Asus", "A600", 32, "ssd", 256, "silver", 40000));
        notebooks.add(new Notebook("Asus", "A700", 64, "ssd", 1024, "green", 60000));
        notebooks.add(new Notebook("Apple", "A2000", 8, "ssd", 512, "silver", 30000));
        notebooks.add(new Notebook("Apple", "A3000", 16, "ssd", 1024, "silver", 60000));
        notebooks.add(new Notebook("Apple", "A4000", 32, "ssd", 2048, "silver", 90000));
        return notebooks;
    }
}
