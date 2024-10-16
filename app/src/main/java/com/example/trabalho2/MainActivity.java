 package com.example.trabalho2;

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
     private List<Participante> listParticipante;
     private AppDatabase db;

     private Participante participante = new Participante();


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=AppDatabase.getDatabase(getApplicationContext());

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                participante.setCPF("07777558774");
                participante.setNome("Teste");
                participante.setTelefone("67999999999");

                db.participanteDao().insert(participante);
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
         binding.listViewUsuarios.setAdapter(adapter);

     }
}