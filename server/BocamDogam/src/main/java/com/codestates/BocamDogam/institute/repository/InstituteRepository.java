package com.codestates.BocamDogam.institute.repository;

import com.codestates.BocamDogam.institute.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

}
