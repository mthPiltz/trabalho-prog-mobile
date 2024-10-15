package com.example.trabalho2.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Participante {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nome;
    String CPF;
    String telefone;
    int idMod;
}
