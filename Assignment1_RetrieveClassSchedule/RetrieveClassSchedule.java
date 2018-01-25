/*
 * Beverly ACKAH
 * Winter 2018
 * Professor: Fatma Serce
 * Description: A code that that provides the user with the course schedule for a 
 * particular course in a given program at BC. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RetrieveClassSchedule {
	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter quarter: ");
		String quarter = scanner.nextLine();

		System.out.println("Enter year: ");
		int year = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter the initial of the program: ");
		String program = scanner.nextLine();	
		//Link 1
		URL url = new URL("https://www.bellevuecollege.edu/classes" + "/" + quarter  + year + "?letter=" + program);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String input = "";
		String text = "";
		while((input = in.readLine()) != null) {

			text += input + "\n";	

		}

		Pattern pattern = Pattern.compile("<li class=\"subject-name\">\\s*<a\\shref=\"/classes/.*/(.*)\">(.*)</a>\\s(.*)"); 
		Matcher matcher = pattern.matcher(text);

		System.out.println(" ");
		System.out.println("Programs:");
		String match="";
		while(matcher.find()) {

			System.out.println(matcher.group(2) + " " + matcher.group(3));
			match	= matcher.group(1);		// this gets the "CS"

		}		
		System.out.println(" ");
		System.out.println("Enter the program's name: ");
		String programsName = scanner.nextLine();
		//pattern for course id
		System.out.println("Enter the course ID: ");
		String courseTitle = scanner.next();
		String courseID = (courseTitle+" "+scanner.next()).toUpperCase();



		System.out.println("https://www.bellevuecollege.edu/classes/" + quarter  + year + "/" + courseTitle);
		//Link 2

		URL url2 = new URL("https://www.bellevuecollege.edu/classes/" + quarter  + year + "/" + courseTitle);
		BufferedReader in2 = new BufferedReader(new InputStreamReader(url2.openStream()));

		String input2 = "";
		String text2 = "";

		Pattern cTitle = Pattern.compile("<span class=\"courseID\">"+courseID+"<\\/span>\\s*<span class=\"courseTitle\">(.*)\\s*<\\/span");

		String code = courseID , title="", itemNumber="", instructor="", days="";
		boolean found = false;

		//Different patters for different categories we want to grab
		Pattern codePattern = Pattern.compile("<span class=\"courseID\">([a-zA-Z]*\\s[0-9]*)<\\/span>");
		Pattern itemNumberPattern = Pattern.compile("Item number:\\s<\\/span>([0-9]*)<\\/span>");
		Pattern titlePattern = Pattern.compile("class=\"courseTitle\">(.*)<\\/span>");
		Pattern instructorPattern = Pattern.compile("SearchString=.*>(.*)<\\/a>");
		Pattern daysPattern = Pattern.compile("<abbr title=\"([a-zA-Z]*\\/?[a-zA-Z]*?)\">");
		Pattern onlinePattern = Pattern.compile("<span class=\"days online\">(Online)<\\/span>");
		ArrayList<Course> courses = new ArrayList<Course>(); // Array list of Course object
		Course course = new Course();
		while((input2 = in2.readLine()) != null) {

			text2 = input2 + "\n";	

			Matcher codeMatch = codePattern.matcher(text2);
			if(codeMatch.find()) {

				if(codeMatch.group(1).equals(courseID)) {

					code = codeMatch.group(1);

					found = true;

					course.setCode(code);


				}
				if(!codeMatch.group(1).equals(courseID)) {
					found = false;

				}



			}


			if(found == true) {
				Matcher titleMatch = cTitle.matcher(text2);
				Matcher itemNumberMatch = itemNumberPattern.matcher(text2);
				Matcher instructorMatch = instructorPattern.matcher(text2);
				Matcher daysMatch = daysPattern.matcher(text2);
				Matcher onlineMatch = onlinePattern.matcher(text2);

				if(titleMatch.find()) {

					title = titleMatch.group(1);

					course.setTitle(title);
				}
				if(itemNumberMatch.find()) {
					itemNumber = itemNumberMatch.group(1);
					course = new Course();
					course.setCode(code);
					course.setTitle(title);
					course.setItemNumber(itemNumber);


				}
				if(instructorMatch.find()) {
					instructor = instructorMatch.group(1);

					course.setInstructor(instructor);
				}
				if(daysMatch.find() ) {

					days = daysMatch.group(1);


					if(courses.size()!=0) {
						if(courses.get(courses.size()-1).instructor.equals(instructor)) {

							courses.get(courses.size()-1).addDays(days);

						}
						else {

							course.addDays(days);
							courses.add(course);
						}

					}
					else {

						course.addDays(days);
						courses.add(course);
					}


				}
				if(onlineMatch.find()) {
					days = onlineMatch.group(1);


					course.addDays(days);
					courses.add(course);
				}


			}


		}
		for(int i =0; i < courses.size(); i ++) {
			System.out.println(courses.get(i));
			System.out.println("========================================");
		}



	}
}
