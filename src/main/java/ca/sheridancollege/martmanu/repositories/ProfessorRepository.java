package ca.sheridancollege.martmanu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.martmanu.beans.Professor;

public interface ProfessorRepository extends JpaRepository <Professor, Long>{

}
