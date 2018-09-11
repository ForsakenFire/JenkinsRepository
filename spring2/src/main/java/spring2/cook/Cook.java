package spring2.cook;

public class Cook {
	private String name;
	private Student student;
	public String cook(){
		return name+" is cooking,"+student.getName()+" is a student";
	}
	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setName(String name) {
		this.name = name;
	}
}
