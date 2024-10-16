package com.example.trabalho2.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        primaryKeys = {"idParticipante", "idModalidade"},
        foreignKeys = {
                @ForeignKey(entity = Participante.class,
                        parentColumns = "id",
                        childColumns = "idParticipante",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Modalidade.class,
                        parentColumns = "id",
                        childColumns = "idModalidade",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class ParticipanteModalidade {
    int idParticipante;
    int idModalidade;

    public ParticipanteModalidade(int idParticipante, int idModalidade) {
        this.idParticipante = idParticipante;
        this.idModalidade = idModalidade;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(int idModalidade) {
        this.idModalidade = idModalidade;
    }
}
