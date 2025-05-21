import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 2 - Notation Assignment
 */
public class Notation {
	
	public Notation() {
		
	}
	
	private static int precedence(char operator) {
		switch(operator) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return -1;
		}	
	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		Stack<Double> valueStack = new Stack<>();
		
		int index = 0;
		while(index < postfixExpr.length()) {
			char nextCharacter = postfixExpr.charAt(index);
			if(Character.isDigit(nextCharacter) || nextCharacter == ')') {
				valueStack.push((double) Character.getNumericValue(nextCharacter));
			}
			else if(nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
				if(valueStack.size() < 2) throw new InvalidNotationFormatException();
				double op2 = valueStack.pop();
				double op1 = valueStack.pop();
				double result = 0;
				
				if(nextCharacter == '+') {
					result = op1 + op2;
				}
				else if(nextCharacter == '-') {
					result = op1 - op2;
				}
				else if(nextCharacter == '*') {
					result = op1 * op2;
				}
				else if(nextCharacter == '/') {
					if(op2 == 0) throw new InvalidNotationFormatException();
					result = op1 / op2;
				}
				else throw new InvalidNotationFormatException();
				valueStack.push(result);
			}
			index++;
		}
		if(valueStack.size() != 1) {
			throw new InvalidNotationFormatException();
		}
		return valueStack.pop();
		
	}
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		Stack<String> stack = new Stack<>();
		
		int index = 0;
		while(index < postfix.length()) {
			char nextCharacter = postfix.charAt(index);
		if(nextCharacter == ' ') continue;
		if(Character.isDigit(nextCharacter)) {
			stack.push(String.valueOf(nextCharacter));
		}
		else if(nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
			if(stack.size() < 2) {
				throw new InvalidNotationFormatException();
			}
			else {
				String op2 = stack.pop();
				String op1 = stack.pop();
				String strOpr = "(" + op1 + nextCharacter + op2 + ")";
				stack.push(strOpr);
			}
		}
		index++;
		}
		if(stack.size() != 1) {
			throw new InvalidNotationFormatException();
		}
		
		return stack.pop();
	}
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		Stack<Character> operatorStack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		
		int index  = 0;
		while(index < infix.length()) {
			char nextCharacter = infix.charAt(index);
			
			if(nextCharacter == ' ') continue;
			
			if(Character.isDigit(nextCharacter)) {
				postfix.append(nextCharacter);
			}
			else if(nextCharacter == '(') {
				operatorStack.push(nextCharacter);
			}
			else if(nextCharacter == ')') {
				while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postfix.append(operatorStack.pop());
				}
				if(operatorStack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				operatorStack.pop();
			}
			else if(nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
				while(!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(nextCharacter)){
					postfix.append(operatorStack.peek());
					operatorStack.pop();
				}
				operatorStack.push(nextCharacter);
			}
			else {
				throw new InvalidNotationFormatException();
			}
				index++;
			}
			
			while(!operatorStack.isEmpty()) {
				if(operatorStack.peek() == '(') {
					throw new InvalidNotationFormatException();
				}
				postfix.append(operatorStack.pop());
			}
			return postfix.toString();
			
	}

}
