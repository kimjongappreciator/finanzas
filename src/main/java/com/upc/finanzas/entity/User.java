package com.upc.finanzas.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 50, nullable = false)
    private String Name;
    @Column(length = 50, nullable = false)
    private String Email;
    @Column(length = 50, nullable = false)
    private String Pwd;

}
