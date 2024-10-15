package com.example.trabalho2.Dao;

import androidx.room.Dao;
import androidx.room.PrimaryKey;

@Dao
public class ModalidadeDao {
    @PrimaryKey(autoGenerate = true)
    int id;

    String descricao;

    public ModalidadeDao() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
}
