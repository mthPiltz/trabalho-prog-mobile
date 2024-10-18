package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trabalho2.Entities.Modalidade;
import com.example.trabalho2.Entities.Participante;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityCadastrarModalidadeBinding;

import java.util.List;

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

        binding.btnDellModalidde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        binding.btnEditMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });
    }

    private void salvarModalidade(){
        String desc = binding.txtDesc.getText().toString();
        if(desc.isEmpty()){
            Toast.makeText(this, "Preencha a descrição",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Modalidade modalidade = new Modalidade();
        modalidade.setDescricao(desc);


        db.modalidadeDao().insert(modalidade);
        Toast.makeText(this, "Modalidade Inserida",
                Toast.LENGTH_LONG).show();
    }

    private void excluir(){
        int idExcluido = Integer.parseInt(binding.delidMod.getText().toString());
        if(idExcluido == 0){
            Toast.makeText(this, "Preencha o id excluido.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        List<Participante> participanteList = db.participanteDao().getByModalidade(idExcluido);
        if(participanteList.size() > 0){
            for(int i = 0; i < participanteList.size(); i++){
                db.participanteDao().delete(participanteList.get(i));
            }
        }


        Modalidade modalidade = db.modalidadeDao().getById(idExcluido);
        if(modalidade != null){
           db.modalidadeDao().delete(modalidade);
           Toast.makeText(this, "Modalidade excluída com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    private void editar(){
        int idEditar = Integer.parseInt(binding.txtId.getText().toString());
        if(idEditar == 0){
            Toast.makeText(this, "Preencha o id para editar.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String desc = binding.txtDesc.getText().toString();
        if(desc.isEmpty()){
            Toast.makeText(this, "Preencha a descrição",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Modalidade modalidade = db.modalidadeDao().getById(idEditar);
        if (modalidade != null){
            modalidade.setDescricao(desc);
            db.modalidadeDao().update(modalidade);
            Toast.makeText(this, "Modalidade atualizada",
                    Toast.LENGTH_LONG).show();
        }
    }
}