package prob04;

public class Depart extends Employee {
	private String department;
	
	public Depart(String name, int salary, String depart) {
		super(name, salary);
		department = depart;
	}
	
	public void print() {
		System.out.println( "이름:" + super.getName() + " 연봉:" + super.getSalary() + " 부서:" + department);
	}

}
