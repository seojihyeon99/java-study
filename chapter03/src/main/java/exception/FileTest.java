package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		// FileInputStream fis = new FileInputStream("hello.txt");
		// 파일 없다고 띄워줌. fileNotFound Exception - exception 바로 밑에 있음
		// try catch 처리 해줘야함, exception 처리 강제하는 checked exception

		FileInputStream fis = null; // 초기화 안시키면 finally에서 인식 못함
		// try catch가 너무 많다!!! -> runtime exception or aop
		try {
			fis = new FileInputStream("hello.txt");
			int data = fis.read();
			// IOexception
			System.out.println((char) data);
		} catch (FileNotFoundException e) {
			System.out.println("error: " + e);
		} catch (IOException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
