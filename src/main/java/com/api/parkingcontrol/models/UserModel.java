package com.api.parkingcontrol.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "TB_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String email;
}
