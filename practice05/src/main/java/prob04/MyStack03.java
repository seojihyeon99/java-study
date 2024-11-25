package prob04;


public class MyStack03<T> { // 제네릭 T는 컴파일 시점에 결정된다.
	private int top;
	private T[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		top = -1;
		buffer = (T[])new Object[capacity]; // 배열 생성(new)는 런타임에 실행된다.
	}

	public void push(T s) {
		if(top == (buffer.length-1)) resize();
		
		buffer[++top] = s;
	}

	public T pop() throws MyStackException {
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}

		return buffer[top--];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		T[] temp = (T[])new Object[buffer.length * 2];
		for (int i = 0; i <= top; i++) {
			temp[i] = buffer[i];
		}

		buffer = temp;
	}	
}