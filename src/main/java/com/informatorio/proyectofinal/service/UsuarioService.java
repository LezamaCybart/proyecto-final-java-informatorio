package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public ResponseEntity<?> updateUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioViejo =  usuarioRepository.findById(id);
        if (usuarioViejo.isPresent()) {
            Usuario usuarioActualizado = usuarioViejo.get();
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setApellido(usuario.getApellido());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPassword(usuario.getPassword());
            usuarioActualizado.setPais(usuario.getPais());
            usuarioActualizado.setCiudad(usuario.getCiudad());
            usuarioActualizado.setProvincia(usuario.getProvincia());
            return new ResponseEntity<>(usuarioRepository.save(usuarioActualizado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> getUsuariosByCiudad(String ciudad) {
        return usuarioRepository.findUsuarioByCiudad(ciudad);
    }

    public List<Usuario> getUsuariosPorFecha(LocalDate fechaDeCreacion) {
        return usuarioRepository.findUsuarioByFechaDeCreacionAfter(fechaDeCreacion);
    }
}
