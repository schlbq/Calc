import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Этот калькулятор римских и арабских чисел"
                + System.lineSeparator() + "он умеет работать с целыми числами от 1 до 10 и знаками +,-,*,/"
                + System.lineSeparator() + "Введите выражение в формате \"2 + 5\" или \"II * IX\": ");
        String text = scanner.nextLine();
        System.out.println(calc(text));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        char operation;
        String[] roman = {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] str = input.split(" ");
        if (str.length < 2) {
            throw new Exception("Строка не является математической операцией");
        } else if (str.length > 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        } else {
            try {
                boolean flag = false;
                for (int i1 = 0; i1 < roman.length; i1++) {
                    if (roman[i1].equals(str[0])) {
                        for (int i2 = 0; i2 < roman.length; i2++) {
                            if (roman[i2].equals(str[2])) {
                                flag = true;
                            }
                        }
                    }
                }
                operation = str[1].charAt(0);
                if (flag) {
                    num1 = romanToArab(str[0]);
                    num2 = romanToArab(str[2]);
                    check(num1, num2, operation);
                    if (calculator(num1, num2, operation) < 1) {
                        throw new Exception("В римской системе нет отрицательных чисел");
                    } else {
                        return arabToRoman(calculator(num1, num2, operation));
                    }
                } else {
                    num1 = Integer.parseInt(str[0]);
                    num2 = Integer.parseInt(str[2]);
                    check(num1, num2, operation);
                    return String.valueOf(calculator(num1, num2, operation));
                }
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Одно из чисел введено некорректно");
            }
        }
    }

    public static int romanToArab(String letter) {
        if (letter.equals("I")) {
            return 1;
        } else if (letter.equals("II")) {
            return 2;
        } else if (letter.equals("III")) {
            return 3;
        } else if (letter.equals("IV")) {
            return 4;
        } else if (letter.equals("V")) {
            return 5;
        } else if (letter.equals("VI")) {
            return 6;
        } else if (letter.equals("VII")) {
            return 7;
        } else if (letter.equals("VIII")) {
            return 8;
        } else if (letter.equals("IX")) {
            return 9;
        } else if (letter.equals("X")) {
            return 10;
        } else return 0;
    }

    public static String arabToRoman(int number) {
        int[] arabValue = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanValue = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arabValue.length; i++) {
            while (number >= arabValue[i]) {
                number -= arabValue[i];
                res.append(romanValue[i]);
            }
        }
        return res.toString();
    }

    public static void check(int num1, int num2, char operation) throws Exception {
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new Exception("Числа не удовлетворяют условиям от 1 до 10");
        }
    }

    public static int calculator(int num1, int num2, char operation) {
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверный знак операции ");
        }
        return result;
    }
}