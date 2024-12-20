package com.Alura.Gutenex.Gutenex.Abstractions;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected Entity() {}
    protected Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
