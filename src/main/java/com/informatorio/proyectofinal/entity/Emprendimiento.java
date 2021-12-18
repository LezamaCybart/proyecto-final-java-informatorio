package com.informatorio.proyectofinal.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;

    @NotEmpty(message = "La descripcion no puede ser vacia")
    private String descripcion;

    @NotEmpty(message = "El cuerpo no puede ser vacio")
    private String cuerpo;

    @CreationTimestamp
    private LocalDate fechaDeCreacion;

    private double objetivo;
    private Boolean publicado;

    @ElementCollection
    private List<String> capturas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "emprendimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<Tag>();

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public List<String> getCapturas() {
        return capturas;
    }

    public void setCapturas(List<String> capturas) {
        this.capturas = capturas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public double getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(double objetivo) {
        this.objetivo = objetivo;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }
}