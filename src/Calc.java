import java.util.Scanner;


public class Calc {


    public static void main(String[] args) {

        boolean isExit;
        boolean isPlus;
        boolean isMinus;
        boolean isMultiple;
        boolean isDivide;
        String operation;

        do {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите два числа от 1 до 10 включительно и укажите нужное действие (+, -, /, *). Пример: 5+5, либо V+V и нажмите Enter.");
            System.out.println("Для выхода введите Q");
            operation = in.nextLine();

            isPlus = operation.contains("+");
            isMinus = operation.contains("-");
            isMultiple = operation.contains("*");
            isDivide = operation.contains("/");
            isExit = operation.contains("Q");

            String[] mathSign = mathAction(isPlus, isMinus, isMultiple, isDivide);
            checkData(mathSign, operation);
        } while (!isExit);

    }

    //    Вывод результата
    static void printResult(int[] inputNumbers, String[] mathSign) {
        if (inputNumbers[0] == 0 || inputNumbers[1] == 0) {
            System.out.println("Введены неверные данны. Повторите корректный ввод данных.");
        } else if (inputNumbers[0] < 0 || inputNumbers[0] > 10 || inputNumbers[1] < 0 || inputNumbers[1] > 10) {
            System.out.println("Введены неверные данны. Повторите корректный ввод данных.");
        } else {
            System.out.println("Результат = " + mathAction(inputNumbers[0], inputNumbers[1], mathSign[0]));
        }
    }


    //    Проверка корректности данных
    static void checkData(String[] mathSign, String operation) {
        int[] inputNumbers;
        if (operation.contains("Q")) {
            System.out.println("Выходим из программы");
        } else if (mathSign[0].equals("") || mathSign[1].equals("")) {
            System.out.println("Введены неверные данны. Повторите корректный ввод данных.");
        } else {
            inputNumbers = splitStr(operation, mathSign[1]);
            printResult(inputNumbers, mathSign);
        }
    }


    //    Определения знака арифметической операции и регулярного выражения
    static String[] mathAction(boolean plus, boolean minus, boolean multiple, boolean divide) {
        String[] Actions = new String[2];
        if (plus) {
            Actions[0] = "+";
            Actions[1] = "[+]";
        } else if (minus) {
            Actions[0] = "-";
            Actions[1] = "[-]";
        } else if (multiple) {
            Actions[0] = "*";
            Actions[1] = "[*]";
        } else if (divide) {
            Actions[0] = "/";
            Actions[1] = "[/]";
        } else {
            Actions[0] = "";
            Actions[1] = "";
        }
        return Actions;
    }

    //    Обработка латинских цифр
    static String romanToArabConvert(String latNum) {
        String  result = latNum;
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX","X"};
        String[] arabianNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for(int i = 0; i < romanNumbers.length; i++){
            if(latNum.equals(romanNumbers[i])){
                result = (arabianNumbers[i]);
                break;
            }
        }
        return result;
    }

    //    Разделение полученной строки от пользователя и возврат массива чисел
    static int[] splitStr(String operation, String regSign) {
        int[] returnArray = {0, 0};
        String[] resultSplit = operation.split(regSign);
        resultSplit[0] = resultSplit[0].trim();
        resultSplit[1] = resultSplit[1].trim();

        if ((resultSplit[0].contains("I") || resultSplit[0].contains("V") || resultSplit[0].contains("X"))
                && (resultSplit[1].contains("I") || resultSplit[1].contains("V") || resultSplit[1].contains("X"))) {
            resultSplit[0] = romanToArabConvert(resultSplit[0]);
            resultSplit[1] = romanToArabConvert(resultSplit[1]);
            returnArray[0] = Integer.parseInt(String.valueOf(resultSplit[0]));
            returnArray[1] = Integer.parseInt(String.valueOf(resultSplit[1]));

        } else if (isNumeric(resultSplit[0]) && isNumeric(resultSplit[1]) && resultSplit.length == 2) {
            returnArray[0] = Integer.parseInt(String.valueOf(resultSplit[0]));
            returnArray[1] = Integer.parseInt(String.valueOf(resultSplit[1]));
        }
        return returnArray;
    }

    //Проверка строки на число
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    //      Выполнение вычисления
    static double mathAction(double num1, int num2, String sign) {
        double result;
        result = switch (sign) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> 0;
        };
        return result;
    }


}

