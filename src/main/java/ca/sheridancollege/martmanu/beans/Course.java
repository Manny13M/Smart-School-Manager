package ca.sheridancollege.martmanu.beans;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	@NonNull
	private String courseName;
	
	@NonNull
	private String courseCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="PROFESSOR_COURSE",
				joinColumns=@JoinColumn(name="COURSE_ID"),
				inverseJoinColumns=@JoinColumn(name="PROFESSOR_ID"))
	private Professor professor;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Student> students;
	
	
	public void removeStudentById(Long studentId) {
		
		Iterator<Student> iterator = students.iterator();
		
		while(iterator.hasNext()) {
			
			Student student = iterator.next();
			
			if(student.getId().equals(studentId)) {
				
				// Ensure the many to many relationship is managed properly on both ends
				
				iterator.remove(); // Remove the student that the iterator is pointing to from the students list
				student.getCourses().remove(this); // Remove this course from the courses list from the student
			}
			
		}
	}
	
	public Boolean findStudentById(Long studentId) {
		
		Iterator<Student> iterator = students.iterator();
		
		while(iterator.hasNext()) {
			
			Student student = iterator.next();
			
			if(student.getId().equals(studentId)) {
				
				return true; //Student is found in the students list
			}
		}
		
		return false; //Student is NOT found in the students list
	}
	
	public void addStudent(Student student) {
		
		this.getStudents().add(student);
	}
	
}
