package com.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;



@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;

    private int cottonPart;

    private int quantity;

}
