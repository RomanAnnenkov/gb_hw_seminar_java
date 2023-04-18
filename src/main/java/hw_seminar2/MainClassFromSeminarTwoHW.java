package hw_seminar2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class MainClassFromSeminarTwoHW {
    public static void main(String[] args) {
        System.out.println("task1");
        String jsonFilter = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        //String jsonFilter = "{\"name\":\"null\", \"country\":\"null\", \"city\":\"null\", \"age\":\"null\"}";
        String table = "user";
        System.out.println(getSelect(table, jsonFilter));
        System.out.println();

        System.out.println("task2");
        printExtensions();
    }

    public static String getSelect(String tableName, String filterJSON) {
        /*
        1. Дана строка sql-запроса "select * from students where ".
        Сформируйте часть WHERE этого запроса, используя StringBuilder.
        Данные для фильтрации приведены ниже в виде json-строки.
        Если значение null, то параметр не должен попадать в запрос.
        Пример 1:
        Параметры для фильтрации: {"name:Ivanov", "country:Russia", "city:Moscow", "age:null"}
        Результат:
        SELECT * FROM USER WHERE name = 'Ivanov' and country = 'Russia' and city = 'Moscow';
        Пример 2:
        Параметры для фильтрации: {"name:null", "country:null", "city:null", "age:null"}
        Результат:
        SELECT * FROM USER;
        */
        StringBuilder sb = new StringBuilder();
        ObjectMapper om = new ObjectMapper();
        sb.append("SELECT * FROM ").append(tableName);
        try {
            Map<String, String> filer = om.readValue(filterJSON, new TypeReference<>() {
            });
            for (String key : filer.keySet()) {
                String value = filer.get(key);
                if (!value.equals("null")) {
                    sb.append(" '").append(key).append("'");
                    sb.append(" = ");
                    sb.append(value).append(",");
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        char lastChar = sb.charAt(sb.length() - 1);
        int lastCharIndex = sb.length() - 1;
        if (lastChar == ',') {
            sb.deleteCharAt(lastCharIndex);
        }
        sb.append(";");
        return sb.toString();
    }

    public static void printExtensions() {
        /*
        2.Напишите метод, который определит тип (расширение) файлов из текущей папки и
        выведет в консоль результат вида:
        1 Расширение файла: txt
        2 Расширение файла: pdf
        3 Расширение файла:
        4 Расширение файла: jpg
        */
        String currentDir = System.getProperty("user.dir");
        File currentPath = new File(currentDir);
        String[] files = currentPath.list();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            int lastIndexOfDot = files[i].lastIndexOf('.');
            int rowNumber = i + 1;
            if (lastIndexOfDot > 0) {
                System.out.println(rowNumber + " Расширение файла: " + files[i].substring(lastIndexOfDot));
            } else {
                System.out.println(rowNumber + " Расширение файла: ");
            }
        }

    }
}
