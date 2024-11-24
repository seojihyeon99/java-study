package prob06;

public class Money {
	private int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	public Money add(Money money) {
		return new Money(amount + money.amount);
	}
	
	public Money minus(Money money) {
		return new Money(amount - money.amount);
	}
	
	public Money multiply(Money money) {
		return new Money(amount * money.amount);
	}
	
	public Money divide(Money money) {
		if(money.amount == 0) {
			System.out.println("0으로 나눌수 없습니다.");
			return null;
		}
		
		return new Money(amount / money.amount);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Money) {
			Money money = (Money)obj;
			return amount == money.amount;
		}
		
		return false;
	}
}
