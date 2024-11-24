package prob04;

import java.util.Arrays;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		buffer = new String[capacity];
		top = -1;
	}

	public void push(String s) {
		if(top == (buffer.length-1)) resize();
		
		buffer[++top] = s;
	}

	public String pop() throws MyStackException {
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}

		return buffer[top--];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	private void resize() {
		String[] newBuffer = new String[buffer.length + 1];
		newBuffer = Arrays.copyOf(buffer, buffer.length + 1);
		buffer = newBuffer;
	}	
}