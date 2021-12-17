package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findUsuarioByCiudad(String ciudad);
}
