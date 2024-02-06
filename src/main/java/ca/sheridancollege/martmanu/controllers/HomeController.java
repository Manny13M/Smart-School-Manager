package ca.sheridancollege.martmanu.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.martmanu.beans.Course;
import ca.sheridancollege.martmanu.beans.Professor;
import ca.sheridancollege.martmanu.beans.Student;
import ca.sheridancollege.martmanu.repositories.CourseRepository;
import ca.sheridancollege.martmanu.repositories.ProfessorRepository;
import ca.sheridancollege.martmanu.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	//testing commit from eclipse
	
	StudentRepository studentRepo;
	ProfessorRepository professorRepo;
	CourseRepository courseRepo;

	@GetMapping("/")
	public String getRootPage(Model model) {
		
		model.addAttribute("students", studentRepo.findAll());
		model.addAttribute("professors", professorRepo.findAll());
		model.addAttribute("courses", courseRepo.findAll());
		return "root.html";
	}
	
	//----------------------------------------------------------------------------
	
	@GetMapping("/addProfessor")
	public String getAddProfessor(Model model) {
		
		model.addAttribute("professor", new Professor());
		model.addAttribute("courses", courseRepo.findAll());
		
		return "addProfessor.html";
	}
	
	@PostMapping("/addProfessor")
	public String processAddProfessor(@ModelAttribute Professor professor) {
		
		professorRepo.save(professor);
		
		return "redirect:/addProfessor";
	}
	
	@GetMapping("/viewProfessor/{id}")
	public String getViewProfessor(Model model, @PathVariable Long id) {
		
		Optional<Professor> professor = professorRepo.findById(id);
		
		if(professor.isPresent()) {
			Professor selectedProfessor = professor.get();
			model.addAttribute("professor", selectedProfessor);
		}
		else {
			return "redirect:/";
		}
		
		return "viewProfessor.html";
	}
	
	//----------------------------------------------------------------------------
	
	@GetMapping("/addStudent")
	public String getAddStudent(Model model) {
		
		model.addAttribute("student", new Student());
		return "addStudent.html";
	}
	
	@PostMapping("/addStudent")
	public String processAddStudent(@ModelAttribute Student student) {
		
		studentRepo.save(student);
		
		return "redirect:/addStudent";
	}
	
	@GetMapping("/viewStudent/{id}")
	public String getViewStudent(Model model, @PathVariable Long id) {
		
		Optional<Student> student = studentRepo.findById(id);
		
		if(student.isPresent()) {
			Student selectedStudent = student.get();
			model.addAttribute("student", selectedStudent);
		}
		else {
			return "redirect:/";
		}
		
		return "viewStudent.html";
	}
	
	@GetMapping("/removeStudentFromCourse")
	public String removeStudentFromCourse(	
											@RequestParam Long studentId, 
											@RequestParam Long courseId, 
											Model model) {
		
		System.out.println("student id: " + studentId);
		System.out.println("course id: " + courseId);
		
		Optional<Course> courseToRemoveStudentFrom = courseRepo.findById(courseId);
		Optional<Student> studentToRemove = studentRepo.findById(studentId);
		
		if(courseToRemoveStudentFrom.isPresent() && studentToRemove.isPresent()) {
			
			Course course = courseToRemoveStudentFrom.get();
			Student student = studentToRemove.get();
			
			course.removeStudentById(student.getId());
			
			courseRepo.save(course);
			studentRepo.save(student);
		}

		return "redirect:/";
	}	
	
	//----------------------------------------------------------------------------
	
	@GetMapping("/addCourse")
	public String getAddCourse(Model model) {
		
		model.addAttribute("course", new Course());
		return "addCourse.html";
	}
	
	@PostMapping("/addCourse")
	public String processAddCourse(@ModelAttribute Course course) {
		
		courseRepo.save(course);
		
		return "redirect:/addCourse";
	}
	
	@GetMapping("/viewCourse/{id}")
	public String getViewCourse(Model model, @PathVariable Long id) {
		
		Optional<Course> course = courseRepo.findById(id);
		
		if(course.isPresent()) {
			Course selectedCourse = course.get();
			model.addAttribute("course", selectedCourse);
		}
		else {
			return "redirect:/";
		}
		
		return "viewCourse.html";
	}
	
	//----------------------------------------------------------------------------
	
	@GetMapping("/assignProfessorToCourse")
	public String getAssignProfessorToCourse(Model model) {
		
		model.addAttribute("professors", professorRepo.findAll());
		model.addAttribute("courses", courseRepo.findAllByProfessorIsNull());
		
		return "assignProfessorToCourse.html";
	}
	
	@PostMapping("/assignProfessorToCourse")
	public String processAssignProfessorToCourse(
													@RequestParam Long professorId,
										            @RequestParam Long courseId,
													Model model) {
		
		Optional<Professor> professor = professorRepo.findById(professorId);
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(professor.isPresent() && course.isPresent()) {
			
			Professor selectedProfessor = professor.get();
			Course selectedCourse = course.get();
			
			selectedProfessor.getCourses().add(selectedCourse);
			selectedCourse.setProfessor(selectedProfessor);
			
			professorRepo.save(selectedProfessor);
			courseRepo.save(selectedCourse);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/assignStudentToCourse")
	public String getAssignStudentToCourse(Model model) {
		
		model.addAttribute("students", studentRepo.findAll());
		model.addAttribute("courses", courseRepo.findAll());
		
		return "assignStudentToCourse.html";
	}
	
	@PostMapping("/assignStudentToCourse")
	public String processAssignStudentToCourse(
													@RequestParam Long studentId,
										            @RequestParam Long courseId,
													Model model) {
		
		Optional<Student> student = studentRepo.findById(studentId);
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(student.isPresent() && course.isPresent()) {
			
			Student selectedStudent = student.get();
			Course selectedCourse = course.get();
			
			if(!selectedCourse.findStudentById(studentId) && !selectedStudent.findCourseById(courseId)) {
				
				selectedCourse.addStudent(selectedStudent);
				selectedStudent.addCourse(selectedCourse);
				
				courseRepo.save(selectedCourse);
				studentRepo.save(selectedStudent);
			}
			
		}
		
		return "redirect:/";
	}
	
	
}
