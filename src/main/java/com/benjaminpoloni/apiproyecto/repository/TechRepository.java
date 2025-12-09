package com.benjaminpoloni.apiproyecto.repository;

import com.benjaminpoloni.apiproyecto.models.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {


}
