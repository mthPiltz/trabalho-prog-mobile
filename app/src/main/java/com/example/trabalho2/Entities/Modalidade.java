package com.example.trabalho2.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Modalidade {
    @PrimaryKey(autoGenerate = true)
    int id;

    String descricao;

    public Modalidade() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "Id: " + id + "; Descrição: " + descricao + ";";
    }
}
