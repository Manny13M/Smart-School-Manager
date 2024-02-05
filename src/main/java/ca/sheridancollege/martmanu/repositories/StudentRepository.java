package ca.sheridancollege.martmanu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.martmanu.beans.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

}
