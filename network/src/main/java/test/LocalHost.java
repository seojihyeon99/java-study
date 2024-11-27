package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName();
			String hostIpAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostIpAddress);
			
			byte[] byteIpAddresses = inetAddress.getAddress();
			for(byte byteIpAddress : byteIpAddresses) {
//				System.out.print(Byte.toUnsignedInt(byteIpAddress) + " ");
				System.out.print((byteIpAddress & 0x000000ff) + " ");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
