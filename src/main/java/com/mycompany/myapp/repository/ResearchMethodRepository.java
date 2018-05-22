package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ResearchMethod;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ResearchMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResearchMethodRepository extends JpaRepository<ResearchMethod, Long> {

}
