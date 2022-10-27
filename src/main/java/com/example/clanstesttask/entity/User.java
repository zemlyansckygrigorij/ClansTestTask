package com.example.clanstesttask.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private long id;     // id пользователя
    private String name; // имя пользователя
    private int gold;    // текущее количество золота у пользователя
}
