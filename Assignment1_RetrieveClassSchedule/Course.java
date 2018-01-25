import java.util.ArrayList;
import java.util.List;

public class Course {

	String code;
	String title;
	String itemNumber;
	String instructor;
	List<String> days;
	
	public Course() {
	    this.code="";
		this.title = "";
		this.itemNumber = "";
		this.instructor = "";
		this.days = new ArrayList<String>();
		
	}
	
	public void addDays(String day) {
		days.add(day);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String toString() {
		String course = "code : "+code+"\n";
		course +="title : "+title+"\n";
		course +="itemNumber# : "+itemNumber+"\n";
		course +="instructor : "+instructor+"\n";
		for(int i =0; i < days.size(); i++) {
			course +="days : "+days.get(i)+"\n";
		}
		return course;
	}
	
}
