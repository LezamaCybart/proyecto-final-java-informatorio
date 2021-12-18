package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findTagByNombre(String nombre);
}
