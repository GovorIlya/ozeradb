package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TypesProblems;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TypesProblems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypesProblemsRepository extends JpaRepository<TypesProblems, Long> {

}
