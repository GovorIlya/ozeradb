package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RatingMethod;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RatingMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingMethodRepository extends JpaRepository<RatingMethod, Long> {

}
