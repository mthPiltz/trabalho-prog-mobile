 package com.example.trabalho2;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.ArrayAdapter;

 import androidx.appcompat.app.AppCompatActivity;

 import com.example.trabalho2.Entities.Participante;
 import com.example.trabalho2.database.AppDatabase;
 import com.example.trabalho2.databinding.ActivityMainBinding;

 import java.util.List;

 public class MainActivity extends AppCompatActivity {
     private ActivityMainBinding binding;
     private AppDatabase db;
     private Intent intent;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=AppDatabase.getDatabase(getApplicationContext());

        binding.btnParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionaParticipante();
            }
        });

        binding.btnModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionaModalidade();
            }
        });
    }

     @Override
     protected void onResume(){
         super.onResume();
     }
     public void adicionaParticipante() {
         intent=new Intent(this, ParticipanteActivity.class);
         startActivity(intent);
     }

     public  void adicionaModalidade(){
         intent = new Intent(this, ModalidadeActivity.class);
         startActivity(intent);
     }

}