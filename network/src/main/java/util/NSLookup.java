package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("주소를 입력하세요(exit 입력 시 종료): ");
			String s = sc.nextLine();
			
			if("exit".equals(s)) break;
			
			try {
				InetAddress[] inetAddresses = InetAddress.getAllByName(s);
				for(InetAddress inetAddress : inetAddresses) {
					System.out.print(s + ":");
					System.out.println(inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
