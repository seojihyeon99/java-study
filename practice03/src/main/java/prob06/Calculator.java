package prob06;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print(">> ");
			String expression = scanner.nextLine();
			
			if("quit".equals(expression)) {
				break;
			}
			
			String[] tokens = expression.split(" ");
			
			if(tokens.length != 3) {
				System.out.println(">> 알 수 없는 식입니다.");
				continue;
			}
			
			int lValue = Integer.parseInt(tokens[0]);
			int rValue = Integer.parseInt(tokens[2]);
			
			switch(tokens[1]) {
				case "+": {
					Add.setValue(lValue, rValue);
					System.out.println(">> " + Add.calculate());
					break;
				}
				case "-" : {
					Sub.setValue(lValue, rValue);
					System.out.println(">> " + Sub.calculate());
					break;
				}
				case "*" : {		
					Mul.setValue(lValue, rValue);
					System.out.println(">> " + Mul.calculate());
					break;
				}
				case "/" : {
					if(rValue == 0) {
						System.out.println("0으로 나눌 수 없습니다.");
						break;
					}
					
					Div.setValue(lValue, rValue);
					System.out.println(">> " + Div.calculate());					
					break;					
				}
				default :  {
					System.out.println(">> 알 수 없는 연산입니다.");
				}
			}
		}
		
		scanner.close();
	}

}
