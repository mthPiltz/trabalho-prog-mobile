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

    public Participante() {};

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCPF() { return CPF; }

    public void setCPF(String CPF) { this.CPF = CPF; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Id: " + id + "; Nome: " + nome + "; CPF: " + CPF + "; Telefone: " + telefone + "; idModalidade: 1";
    }

}
