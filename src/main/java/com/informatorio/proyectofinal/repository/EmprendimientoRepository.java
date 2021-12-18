package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
    public List<Emprendimiento> findEmprendimientoByTags(Tag tag);
    public List<Emprendimiento> findEmprendimientoByPublicado(Boolean publicado);
}
