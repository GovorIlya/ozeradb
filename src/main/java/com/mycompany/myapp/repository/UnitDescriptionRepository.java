package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.UnitDescription;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UnitDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitDescriptionRepository extends JpaRepository<UnitDescription, Long> {

}
