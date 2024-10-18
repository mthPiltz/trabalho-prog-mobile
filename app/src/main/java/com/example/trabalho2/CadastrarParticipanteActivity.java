package com.example.trabalho2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2.Entities.Modalidade;
import com.example.trabalho2.Entities.Participante;
import com.example.trabalho2.Entities.ParticipanteModalidade;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityCadastrarParticipanteBinding;

import java.util.List;

public class CadastrarParticipanteActivity extends AppCompatActivity {
    private int dbUsuarioId;
    private ActivityCadastrarParticipanteBinding binding;
    private AppDatabase db;
    private Modalidade modalidadeSelecionada;

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

        binding.btnDellPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        binding.btnEditPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        buscarModalidades();
    }

    private void salvarParticipante(){
        String nome = binding.txtNome.getText().toString();
        String fone = binding.textTelefone.getText().toString();
        String cpf = binding.txtCpf.getText().toString();

        if(nome.isEmpty()){
            Toast.makeText(this, "Preencha o nome",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(fone.isEmpty()){
            Toast.makeText(this, "Preencha o telefone",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(cpf.isEmpty()){
            Toast.makeText(this, "Preencha o cpf",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(!verificaCPFValido(cpf)) {
            Toast.makeText(this, "CPF inválido", Toast.LENGTH_LONG).show();
            return;
        }

        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setTelefone(fone);
        participante.setCPF(cpf);

        long idGeradoLong = db.participanteDao().insert(participante);
        int idGerado = (int) idGeradoLong;
        Toast.makeText(this, "Participante Inserido",
            Toast.LENGTH_LONG).show();

        Modalidade modalidadeSelecionada = (Modalidade) binding.listModalidades.getSelectedItem();

        if (modalidadeSelecionada != null) {
            ParticipanteModalidade participanteModalidade = new ParticipanteModalidade(idGerado, modalidadeSelecionada.getId());

            db.participanteModalidadeDao().insert(participanteModalidade);
            Toast.makeText(this, "Participante e modalidade associados com sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Selecione uma modalidade antes de salvar o participante", Toast.LENGTH_LONG).show();
        }
    }

    private boolean verificaCPFValido(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        try {
            int peso = 10;
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;

            peso = 11;
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;

            return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;
        } catch (Exception e) {
            return false;
        }
    }

    private void buscarModalidades(){
        List<Modalidade> modalidades = db.modalidadeDao().getAll();
        ArrayAdapter<Modalidade> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, modalidades);
        binding.listModalidades.setAdapter(adapter);
    }

    private void excluir(){
        int idExcluido = Integer.parseInt(binding.delidPart.getText().toString());
        if(idExcluido == 0){
            Toast.makeText(this, "Preencha o id excluido.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Participante participante = db.participanteDao().getById(idExcluido);
        if(participante != null){
            db.participanteDao().delete(participante);
            Toast.makeText(this, "Participante excluída com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    private void editar(){
        int editId = Integer.parseInt(binding.editidPart.getText().toString());
        String nome = binding.txtNome.getText().toString();
        String fone = binding.textTelefone.getText().toString();
        String cpf = binding.txtCpf.getText().toString();

        if(nome.isEmpty()){
            Toast.makeText(this, "Preencha o nome",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(fone.isEmpty()){
            Toast.makeText(this, "Preencha o telefone",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(cpf.isEmpty()){
            Toast.makeText(this, "Preencha o cpf",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Participante participante = db.participanteDao().getById(editId);
        if(participante != null){
            participante.setCPF(cpf);
            participante.setNome(nome);
            participante.setTelefone(fone);

            db.participanteDao().update(participante);
            Toast.makeText(this, "Usuário atualizado",
                    Toast.LENGTH_LONG).show();
        }
    }
}