package ca.sheridancollege.martmanu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.martmanu.beans.Course;

public interface CourseRepository extends JpaRepository <Course, Long>{

}
