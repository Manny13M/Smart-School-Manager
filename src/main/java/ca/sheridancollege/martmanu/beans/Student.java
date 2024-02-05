package ca.sheridancollege.martmanu.beans;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String firstName;
	
	@NonNull
	private String lastName;
	
	@ManyToMany(mappedBy="students", fetch=FetchType.LAZY)
	private List<Course> courses;
	
	public Boolean findCourseById(Long courseId) {
		
		Iterator<Course> iterator = courses.iterator();
		
		while(iterator.hasNext()) {
			
			Course course = iterator.next();
			
			if(course.getId().equals(courseId)) {
				
				return true; //Course is found in the courses list
			}
		}
		
		return false; //Course is NOT found in the courses list
	}
	
	public void addCourse(Course course) {
		
		this.getCourses().add(course);
	}
}
