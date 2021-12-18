package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.OperacionEmprendimiento;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Tag;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.TagRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmprendimientoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository,
                                 UsuarioRepository usuarioRepository,
                                 TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
    }

    public Emprendimiento createEmprendimiento(OperacionEmprendimiento operacionEmprendimiento) {
        Usuario usuario = usuarioRepository.findById(operacionEmprendimiento.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado"));
        List<Tag> tags = tagRepository.findAllById(operacionEmprendimiento.getTags());
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setCapturas(operacionEmprendimiento.getCapturas());
        emprendimiento.setCuerpo(operacionEmprendimiento.getCuerpo());
        emprendimiento.setObjetivo(operacionEmprendimiento.getObjetivo());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setPublicado(operacionEmprendimiento.getPublicado());
        emprendimiento.setUsuario(usuario);
        emprendimiento.getTags().addAll(tags);

        return emprendimientoRepository.save(emprendimiento);
    }

    public ResponseEntity<?> updateEmprendimiento(Long id, Emprendimiento emprendimiento) {
        Optional<Emprendimiento> emprendimientoViejo = emprendimientoRepository.findById(id);

        if (emprendimientoViejo.isPresent()) {
            Emprendimiento emprendimientoActualizado = emprendimientoViejo.get();
            emprendimientoActualizado.setObjetivo(emprendimiento.getObjetivo());
            emprendimientoActualizado.setCuerpo(emprendimiento.getCuerpo());
            emprendimientoActualizado.setNombre(emprendimiento.getNombre());
            emprendimientoActualizado.setCapturas(emprendimiento.getCapturas());
            emprendimientoActualizado.setTags(emprendimiento.getTags());
            emprendimientoActualizado.setDescripcion(emprendimiento.getDescripcion());
            emprendimientoActualizado.setPublicado(emprendimiento.getPublicado());
            return new ResponseEntity<>(emprendimientoRepository.save(emprendimientoActualizado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientoRepository.findAll();
    }

    public List<Emprendimiento> getEmprendimientosByTag(String tagName) {
        Tag tag = tagRepository.findTagByNombre(tagName);
        return emprendimientoRepository.findEmprendimientoByTags(tag);
    }

    public List<Emprendimiento> getEmprendimientosNoPublicados(Boolean publicado) {
        return emprendimientoRepository.findEmprendimientoByPublicado(publicado);
    }

    public void deleteEmprendimiento(Long id) {
        emprendimientoRepository.deleteById(id);
    }
}
