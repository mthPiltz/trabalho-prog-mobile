package com.example.trabalho2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2.Entities.Participante;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityCadastrarParticipanteBinding;

public class CadastrarParticipanteActivity extends AppCompatActivity {
    private int dbUsuarioId;
    private ActivityCadastrarParticipanteBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarParticipanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=AppDatabase.getDatabase(getApplicationContext());
        dbUsuarioId=getIntent().getIntExtra("usuario_id",-1);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarParticipante();
            }
        });
    }

    private void salvarParticipante(){
        Participante participante = new Participante();
        participante.setNome(binding.txtNome.getText().toString());
        participante.setTelefone(binding.textTelefone.getText().toString());
        participante.setCPF(binding.txtCpf.getText().toString());

        if(dbUsuarioId != 1){
            participante = db.participanteDao().getById(dbUsuarioId);
            participante.setId(dbUsuarioId);
            db.participanteDao().update(participante);
        }
        else{
            db.participanteDao().insert(participante);
            Toast.makeText(this, "Participante Inserido",
                    Toast.LENGTH_LONG).show();
        }
    }
}