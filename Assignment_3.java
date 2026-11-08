import java.lang.Character;
import java.lang.String;
import java.lang.System;

public class Main {
    public static int getPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        }
        if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    public static int applyOperation(char operator, int rightOperand, int leftOperand) {
        if (operator == '+') {
            return leftOperand + rightOperand;
        }
        if (operator == '-') {
            return leftOperand - rightOperand;
        }
        if (operator == '*') {
            return leftOperand * rightOperand;
        }
        if (operator == '/') {
            return leftOperand / rightOperand;
        }
        return 0;
    }

    public static int evaluate(String expr) {
        int length = expr.length();
        int[] values = new int[length];
        char[] operators = new char[length];
        int valueTop = -1;
        int operatorTop = -1;

        int index = 0;
        while (index < length) {
            char current = expr.charAt(index);

            if (current == ' ') {
                index++;
                continue;
            }

            if (Character.isDigit(current)) {
                int number = 0;
                while (index < length && Character.isDigit(expr.charAt(index))) {
                    number = number * 10 + (expr.charAt(index) - '0');
                    index++;
                }
                valueTop++;
                values[valueTop] = number;
                continue;
            }

            if (current == '(') {
                operatorTop++;
                operators[operatorTop] = current;
            } else if (current == ')') {
                while (operatorTop >= 0 && operators[operatorTop] != '(') {
                    int right = values[valueTop--];
                    int left = values[valueTop--];
                    char op = operators[operatorTop--];
                    values[++valueTop] = applyOperation(op, right, left);
                }
                operatorTop--;
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                while (operatorTop >= 0 && operators[operatorTop] != '(' && getPrecedence(operators[operatorTop]) >= getPrecedence(current)) {
                    int right = values[valueTop--];
                    int left = values[valueTop--];
                    char op = operators[operatorTop--];
                    values[++valueTop] = applyOperation(op, right, left);
                }
                operatorTop++;
                operators[operatorTop] = current;
            }

            index++;
        }

        while (operatorTop >= 0) {
            int right = values[valueTop--];
            int left = values[valueTop--];
            char op = operators[operatorTop--];
            values[++valueTop] = applyOperation(op, right, left);
        }

        return values[valueTop];
    }

    public static void main(String[] args) {
        String testCaseOne = "2 * (3 + 4) - 10 / 5";
        int resultOne = evaluate(testCaseOne);
        System.out.println(resultOne);

        String testCaseTwo = "(8 - 2) * (1 + 2) / 4";
        int resultTwo = evaluate(testCaseTwo);
        System.out.println(resultTwo);
    }
}
// Time Complexity: O(n) where n is the length of the expression
// Space Complexity: O(n) for the operand and operator stacks
