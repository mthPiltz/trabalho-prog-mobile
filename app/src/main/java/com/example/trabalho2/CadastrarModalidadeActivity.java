package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trabalho2.Entities.Modalidade;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityCadastrarModalidadeBinding;
import com.example.trabalho2.databinding.ActivityCadastrarParticipanteBinding;

public class CadastrarModalidadeActivity extends AppCompatActivity {
    private ActivityCadastrarModalidadeBinding binding;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarModalidadeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=AppDatabase.getDatabase(getApplicationContext());

        binding.btnAddModalidde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarModalidade();
            }
        });
    }

    private void salvarModalidade(){
        Modalidade modalidade = new Modalidade();
        modalidade.setDescricao(binding.txtDesc.getText().toString());

        db.modalidadeDao().insert(modalidade);
        Toast.makeText(this, "Modalidade Inserida",
                Toast.LENGTH_LONG).show();
    }
}