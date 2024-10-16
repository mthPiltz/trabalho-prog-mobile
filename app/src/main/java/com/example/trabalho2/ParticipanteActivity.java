package com.example.trabalho2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2.Entities.Participante;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityParticipanteBinding;

import java.util.List;

public class ParticipanteActivity extends AppCompatActivity {
    private AppDatabase db;
    private List<Participante> listParticipante;
    private ActivityParticipanteBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParticipanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db= AppDatabase.getDatabase(getApplicationContext());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionaParticipante();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        buscarParticipantes();
    }

    private void buscarParticipantes(){
        listParticipante = db.participanteDao().getAll();
        ArrayAdapter<Participante> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listParticipante);
        binding.listViewParticipante.setAdapter(adapter);
    }

    public void adicionaParticipante() {
        intent=new Intent(this, CadastrarParticipanteActivity.class);
        startActivity(intent);
    }
}