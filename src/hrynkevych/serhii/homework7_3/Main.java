package hrynkevych.serhii.homework7_3;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //готовые строки для вывода в консоль
    public static final String [] outTextResult = {"Заданый символ не найден(", "Колличество символа '", "': "};
    public static final String outTextTime = "Операция выполнена за: ";

    public static void main(String[] args) {
        String str; //переменная введенной строки
        char symbol; //переменная для символа подсчета
        int counter; //переменная для счетчика
        long start; //переменная для записи начала времени выполнения
        long end; //переменная для записи конча времени выполнения
        long elapsedTime; //переменная для результата времени выполнения
        HashMap map; //объявляем HashMap для подсчета

        System.out.print("Введи строку для подсчета символов: ");
        str = inputData(); //запускаем функцию ввода строки

        System.out.print("Введи символ, количество которого нужно отобразить: ");
        symbol = inputData().charAt(0); //запускаем функцию ввода строки, но отбираем только первый символ

        start = System.nanoTime();

        System.out.println();
        System.out.println("Посчитаем смиволы по всей строке через HashMap");
        map = calculatingHashMap(str); //запускаем функцию посчета HashMap'ом

        if (map.get(symbol) == null) {
            System.out.println(outTextResult[0]);
        } else {
            System.out.println(outTextResult[1] + symbol + outTextResult[2] + map.get(symbol));
        }

        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println(outTextTime + elapsedTime);

        start = System.nanoTime();

        System.out.println();
        System.out.println("Еще один способ, пройдём циклом по определенному символу");
        counter = fromBeginningOfLine(str, symbol); //запускаем функцию подсчета циклом
        outResult(counter, symbol);

        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println(outTextTime + elapsedTime);

        start = System.nanoTime();

        System.out.println();
        System.out.println("Посчитаем повторяющиеся символы в конце строки");
        counter = fromEndOfLine(str, symbol); //запускаем функцию подсчета циклом в конце строки
        outResult(counter, symbol);

        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println(outTextTime + elapsedTime);
    }

    public static String inputData () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void outResult (int current, char symbol) {
        if (current != 0) {
            System.out.println(outTextResult[1] + symbol + outTextResult[2] + current);
        } else {
            System.out.println(outTextResult[0]);
        }
    }

    public static HashMap calculatingHashMap (String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (map.containsKey(ch)) {
                counter = map.get(ch);
                map.put(ch, ++counter);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }

    public static int fromBeginningOfLine (String str, char symbol) {
        int counter = 0;
        for (int n = 0; n < str.length(); n++) {
            if (str.charAt(n) == symbol) {
                counter = counter+1;
            }
        }
        return counter;
    }

    public static int fromEndOfLine (String str, char symbol) {
        int counter = 0;
        for (int i = str.length()-1; i >= 0; i--) {
            if (str.charAt(i) == symbol) {
                counter = counter+1;
            } else break;
        }
        return counter;
    }
}
