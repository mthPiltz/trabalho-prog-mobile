package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.trabalho2.Entities.Modalidade;
import com.example.trabalho2.database.AppDatabase;
import com.example.trabalho2.databinding.ActivityModalidadeBinding;
import com.example.trabalho2.databinding.ActivityParticipanteBinding;

import java.util.List;

public class ModalidadeActivity extends AppCompatActivity {

    private ActivityModalidadeBinding binding;
    private Intent intent;
    private AppDatabase db;
    private List<Modalidade> modalidadeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModalidadeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db= AppDatabase.getDatabase(getApplicationContext());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionaModalidade();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        buscarModalidades();
    }

    private void buscarModalidades(){
        modalidadeList = db.modalidadeDao().getAll();
        ArrayAdapter<Modalidade> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, modalidadeList);
        binding.listViewModalidade.setAdapter(adapter);
    }

    private void adicionaModalidade(){
        intent=new Intent(this, CadastrarModalidadeActivity.class);
        startActivity(intent);
    }
}