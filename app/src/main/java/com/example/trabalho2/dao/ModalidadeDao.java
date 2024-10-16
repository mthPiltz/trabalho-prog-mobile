package com.example.trabalho2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.trabalho2.Entities.Modalidade;

import java.util.List;

@Dao
public interface ModalidadeDao {
    @Insert
    void insert(Modalidade modalidade);

    @Update
    void update(Modalidade modalidade);

    @Delete
    void delete(Modalidade modalidade);

    @Query("SELECT * FROM Modalidade")
    List<Modalidade> getAll();

    @Query("SELECT * FROM Modalidade WHERE id = :id")
    Modalidade getById(int id);
}
