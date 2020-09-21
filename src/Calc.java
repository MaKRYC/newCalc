import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;


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

            String[] mathSign = mathActionSign(isPlus, isMinus, isMultiple, isDivide);
            checkData(mathSign, operation);
        } while (!isExit);

    }

    //    Вывод результата
    static void printResult(int[] inputNumbers, String[] mathSign) {
        if (inputNumbers[0] >= 1 && inputNumbers[1] >= 1 && inputNumbers[2] == 0) {
            System.out.println("Результат = " + mathAction(inputNumbers[0], inputNumbers[1], mathSign[0]));
            System.out.println(inputNumbers[2]);
        } else if (inputNumbers[0] >= 1 && inputNumbers[1] >= 1 && inputNumbers[2] == 1) {
            double resultRoman = (mathAction(inputNumbers[0], inputNumbers[1], mathSign[0]));
            System.out.println("Результат = " + arabToRomanConvert(resultRoman));
        } else {
            System.out.println("Введены неверные данныe. Повторите корректный ввод данных.");
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
    static String[] mathActionSign(boolean plus, boolean minus, boolean multiple, boolean divide) {
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
        String result = latNum;
        int len = latNum.length();
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabianNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        if(((len <= 2) && (!latNum.equals("XX") && !latNum.equals("XI") && !latNum.equals("VV") && !latNum.equals("XV") && !latNum.equals("VX")))
                || ((len > 2 && len <= 4) && (latNum.equals("III") || latNum.equals("VII") || latNum.equals("VIII")))) {
            for (int i = 0; i < romanNumbers.length; i++) {
                if (latNum.equals(romanNumbers[i])) {
                    result = (arabianNumbers[i]);
                    break;
                }
            }
        } else {
            result = "";
        }

        return result;
    }

    //    Конвертация результата в арабские числа
    static String arabToRomanConvert(double arabResult) {
        int arabNum = (int) arabResult;
        String arabString = String.valueOf(arabNum);
        int len = arabString.length();
        String result = "";
        String value1 = "";
        String value2 = "";

        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] arabianNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 40, 50, 90, 100};
        String[] romanNumDozens = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] arabianStr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        if (arabNum > 0 && arabResult <= 10) {
            for (int i = 0; i < romanNumbers.length; i++) {
                if (arabNum == arabianNumbers[i]) {
                    result = romanNumbers[i];
                    break;
                }
            }
        }

        if (arabNum > 10 && arabResult < 100) {

            for (int l = 0; l < len; l++) {

                for (int i = 0; i < romanNumDozens.length; i++) {
                    if (l == 0 && arabString.startsWith(arabianStr[i])) {
                        value1 = romanNumDozens[i];
                    } else if (l == 1 && arabString.endsWith(arabianStr[i])) {
                        if (IntStream.range(0, romanNumbers.length - 1).anyMatch(j -> true)) {
                            value2 = romanNumbers[i];
                        }
                    }
                }
            }
            result = value1 + value2;
        }

        if (arabNum == 100) {
            result = romanNumDozens[romanNumDozens.length - 1];
        }

        if (arabNum < 1) {
            result = "полученный результат меньше I. Вводите числа в пределах допустимых возможностей";
        }

        if (arabNum > 100) {
            result = "Вводите числа в пределах допустимых возможностей";
        }



        return result;
    }

    //    Разделение полученной строки от пользователя и возврат массива чисел
    static int[] splitStr(String operation, String regSign) {
        int[] returnArray = {0, 0, 0};
        String[] resultSplit = operation.split(regSign);
        resultSplit[0] = resultSplit[0].trim();
        resultSplit[1] = resultSplit[1].trim();

        if ((resultSplit[0].contains("I") || resultSplit[0].contains("V") || resultSplit[0].contains("X"))
                && (resultSplit[1].contains("I") || resultSplit[1].contains("V") || resultSplit[1].contains("X"))) {
            resultSplit[0] = romanToArabConvert(resultSplit[0]);
            resultSplit[1] = romanToArabConvert(resultSplit[1]);
            if(!Objects.equals(resultSplit[0], "") && !resultSplit[1].equals("")){
                returnArray[0] = Integer.parseInt(String.valueOf(resultSplit[0]));
                returnArray[1] = Integer.parseInt(String.valueOf(resultSplit[1]));
                returnArray[2] = 1;
            }


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

