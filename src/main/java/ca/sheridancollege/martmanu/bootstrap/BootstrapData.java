package ca.sheridancollege.martmanu.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.martmanu.beans.Course;
import ca.sheridancollege.martmanu.beans.Professor;
import ca.sheridancollege.martmanu.beans.Student;
import ca.sheridancollege.martmanu.repositories.CourseRepository;
import ca.sheridancollege.martmanu.repositories.ProfessorRepository;
import ca.sheridancollege.martmanu.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

	CourseRepository courseRepo;
	StudentRepository studentRepo;
	ProfessorRepository professorRepo;
	
	@Override
	public void run (String... args) throws Exception{
		
		Professor p1 = Professor.builder()
						.name("Paul Atkins")
						.courses(new ArrayList<Course>())
						.build();
	
		Professor p2 = Professor.builder()
						.name("Jane Welch")
						.courses(new ArrayList<Course>())
						.build();
		
		Professor p3 = Professor.builder()
						.name("Bob Snow")
						.courses(new ArrayList<Course>())
						.build();
		
		Course c1 = Course.builder()
						.courseName("Java")
						.courseCode("JAVA-1456")
						.professor(p1)
						.students(new ArrayList<Student>())
						.build();
		
		Course c2 = Course.builder()
						.courseName("iOS")
						.courseCode("IOS-2901")
						.professor(p1)
						.students(new ArrayList<Student>())
						.build();
		
		Course c3 = Course.builder()
						.courseName("Android")
						.courseCode("ANDR-3495")
						.professor(p2)
						.students(new ArrayList<Student>())
						.build();
		
		Course c4 = Course.builder()
						.courseName("Mobile Apps")
						.courseCode("MAPP-8736")
						.professor(p2)
						.students(new ArrayList<Student>())
						.build();
		
		Course c5 = Course.builder()
						.courseName("Capstone")
						.courseCode("CAPS-7612")
						.professor(p3)
						.students(new ArrayList<Student>())
						.build();
		
		Course c6 = Course.builder()
						.courseName("Hybrid Apps")
						.courseCode("HAPP-7612")
						.professor(p3)
						.students(new ArrayList<Student>())
						.build();
		
		
        // Assigning courses to professors
		p1.getCourses().addAll(Arrays.asList(c1, c2));
		p2.getCourses().addAll(Arrays.asList(c3, c4));
		p3.getCourses().addAll(Arrays.asList(c5, c6));
		
		professorRepo.save(p1);
		professorRepo.save(p2);
		professorRepo.save(p3);
		
		courseRepo.save(c1);
		courseRepo.save(c2);
		courseRepo.save(c3);
		courseRepo.save(c4);
		courseRepo.save(c5);
		courseRepo.save(c6);
		
		
		Student s1 = Student.builder().firstName("Brian").lastName("Smith").courses(new ArrayList<Course>()).build();
		
        Student s2 = Student.builder().firstName("Alice").lastName("Johnson").courses(new ArrayList<Course>()).build();
        
        Student s3 = Student.builder().firstName("John").lastName("Doe").courses(new ArrayList<Course>()).build();
        
        Student s4 = Student.builder().firstName("Emily").lastName("Williams").courses(new ArrayList<Course>()).build();
        
        Student s5 = Student.builder().firstName("Michael").lastName("Brown").courses(new ArrayList<Course>()).build();
        
        Student s6 = Student.builder().firstName("Sophia").lastName("Jones").courses(new ArrayList<Course>()).build();
        
        Student s7 = Student.builder().firstName("David").lastName("Miller").courses(new ArrayList<Course>()).build();
        
        Student s8 = Student.builder().firstName("Olivia").lastName("Davis").courses(new ArrayList<Course>()).build();
        
        Student s9 = Student.builder().firstName("Daniel").lastName("Moore").courses(new ArrayList<Course>()).build();
        
        Student s10 = Student.builder().firstName("Emma").lastName("Taylor").courses(new ArrayList<Course>()).build();
        
        Student s11 = Student.builder().firstName("Christopher").lastName("Wilson").courses(new ArrayList<Course>()).build();
        
        Student s12 = Student.builder().firstName("Mia").lastName("Anderson").courses(new ArrayList<Course>()).build();
        
        Student s13 = Student.builder().firstName("Matthew").lastName("Clark").courses(new ArrayList<Course>()).build();
        
        Student s14 = Student.builder().firstName("Ava").lastName("Hall").courses(new ArrayList<Course>()).build();
        
        Student s15 = Student.builder().firstName("William").lastName("Martin").courses(new ArrayList<Course>()).build();
        
        Student s16 = Student.builder().firstName("Sofia").lastName("Wright").courses(new ArrayList<Course>()).build();
        
        Student s17 = Student.builder().firstName("James").lastName("King").courses(new ArrayList<Course>()).build();
        
        Student s18 = Student.builder().firstName("Grace").lastName("Turner").courses(new ArrayList<Course>()).build();
        
        Student s19 = Student.builder().firstName("Alexander").lastName("White").courses(new ArrayList<Course>()).build();
        
        Student s20 = Student.builder().firstName("Lily").lastName("Harris").courses(new ArrayList<Course>()).build();
        
        Student s21 = Student.builder().firstName("Ethan").lastName("Baker").courses(new ArrayList<Course>()).build();
        
        Student s22 = Student.builder().firstName("Chloe").lastName("Fisher").courses(new ArrayList<Course>()).build();
        
        Student s23 = Student.builder().firstName("Benjamin").lastName("Allen").courses(new ArrayList<Course>()).build();
        
        Student s24 = Student.builder().firstName("Zoe").lastName("Scott").courses(new ArrayList<Course>()).build();
        
        Student s25 = Student.builder().firstName("Jackson").lastName("Hill").courses(new ArrayList<Course>()).build();
        
        Student s26 = Student.builder().firstName("Avery").lastName("Garcia").courses(new ArrayList<Course>()).build();
        
        Student s27 = Student.builder().firstName("Ryan").lastName("Young").courses(new ArrayList<Course>()).build();
        
        Student s28 = Student.builder().firstName("Victoria").lastName("Brown").courses(new ArrayList<Course>()).build();
        
        Student s29 = Student.builder().firstName("Dylan").lastName("Evans").courses(new ArrayList<Course>()).build();
        
        Student s30 = Student.builder().firstName("Nora").lastName("Adams").courses(new ArrayList<Course>()).build();

		
		// Creating two groups of students
		List<Student> studentsGroup1 = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14);
		List<Student> studentsGroup2 = Arrays.asList(s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30);
		
		for (Student student : studentsGroup1) {
			studentRepo.save(student);
		}

		for (Student student : studentsGroup2) {
			studentRepo.save(student);
		}
		
		// Creating two lists of courses
		List<Course> coursesGroup1 = Arrays.asList(c1, c2, c3);		
		List<Course> coursesGroup2 = Arrays.asList(c4, c5, c6);	
		
		courseRepo.save(c1);
		courseRepo.save(c2);
		courseRepo.save(c3);
		courseRepo.save(c4);
		courseRepo.save(c5);
		courseRepo.save(c6);
		
		// Assigning students to courses
		c1.getStudents().addAll(studentsGroup1);
		c2.getStudents().addAll(studentsGroup1);
		c3.getStudents().addAll(studentsGroup1);

		c4.getStudents().addAll(studentsGroup2);
		c5.getStudents().addAll(studentsGroup2);
		c6.getStudents().addAll(studentsGroup2);

		// Saving courses first
		courseRepo.save(c1);
		courseRepo.save(c2);
		courseRepo.save(c3);
		courseRepo.save(c4);
		courseRepo.save(c5);
		courseRepo.save(c6);
        
        
		// Saving students after assigning them to courses
		for (Student student : studentsGroup1) {
		    student.getCourses().addAll(coursesGroup1);
		}

		for (Student student : studentsGroup2) {
		    student.getCourses().addAll(coursesGroup2);
		}

		studentRepo.saveAll(studentsGroup1);
		studentRepo.saveAll(studentsGroup2);
		

	}
}
