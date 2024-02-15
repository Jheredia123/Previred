package com.previred.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.previred.entity.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

}
