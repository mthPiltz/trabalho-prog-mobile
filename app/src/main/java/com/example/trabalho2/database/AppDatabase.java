package com.example.trabalho2.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.trabalho2.Entities.Participante;
import com.example.trabalho2.Entities.Modalidade;
import com.example.trabalho2.Entities.ParticipanteModalidade;
import com.example.trabalho2.dao.ModalidadeDao;
import com.example.trabalho2.dao.ParticipanteModalidadeDao;
import com.example.trabalho2.dao.ParticipanteDao;


@Database(entities = {Participante.class, Modalidade.class, ParticipanteModalidade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // DAOs
    public abstract ParticipanteDao participanteDao();
    public abstract ModalidadeDao modalidadeDao();
    public abstract ParticipanteModalidadeDao participanteModalidadeDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "trabalho2_database")
                        .allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
