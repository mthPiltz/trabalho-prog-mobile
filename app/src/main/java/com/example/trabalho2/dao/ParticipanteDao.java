package com.example.trabalho2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.trabalho2.Entities.Participante;

import java.util.List;

@Dao
public interface ParticipanteDao {
    @Insert
    long insert(Participante participante);

    @Update
    void update(Participante participante);

    @Delete
    void delete(Participante participante);

    @Query("SELECT * FROM Participante")
    List<Participante> getAll();

    @Query("SELECT * FROM Participante WHERE id = :id")
    Participante getById(int id);
}
