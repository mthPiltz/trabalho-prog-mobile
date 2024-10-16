package com.example.trabalho2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.trabalho2.Entities.ParticipanteModalidade;

import java.util.List;

@Dao
public interface ParticipanteModalidadeDao {
    @Insert
    void insert(ParticipanteModalidade participanteModalidade);

    @Update
    void update(ParticipanteModalidade participanteModalidade);

    @Delete
    void delete(ParticipanteModalidade participanteModalidade);

    @Query("SELECT * FROM ParticipanteModalidade")
    List<ParticipanteModalidade> getAll();

    @Query("SELECT * FROM ParticipanteModalidade WHERE idParticipante = :idParticipante AND idModalidade = :idModalidade")
    ParticipanteModalidade getByIds(int idParticipante, int idModalidade);
}
