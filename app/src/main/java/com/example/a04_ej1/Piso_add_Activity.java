package com.example.a04_ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_ej1.Modelos.Piso;
import com.example.a04_ej1.databinding.ActivityPisoAddBinding;

public class Piso_add_Activity extends AppCompatActivity {

    //1. Activa el binding
    private ActivityPisoAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piso_add);

        //2. Construye el binding
        binding = ActivityPisoAddBinding.inflate(getLayoutInflater());

        //3.Asocial el Binding al activity
       setContentView(binding.getRoot());

       binding.btnCancelarPisoAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                   setResult(RESULT_CANCELED);
                   finish();
           }
       });


       binding.btnCrearPisoAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Piso piso = createdPiso();
               if(piso != null){
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("PISO", piso);
                   Intent intent = new Intent();
                   intent.putExtras(bundle);
                   setResult(RESULT_OK, intent);
                   finish();

               }else{
                   Toast.makeText(Piso_add_Activity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    private Piso createdPiso() {
        if(binding.txtDireccionPisoAdd.getText().toString().isEmpty() || binding.txtNumeroPisoAdd.getText().toString().isEmpty() || binding.txtProvinciaPisoAdd.getText().toString().isEmpty() ||
        binding.txtCpPisoAdd.getText().toString().isEmpty() || binding.spCiudadPisoAdd.getSelectedItemPosition() == 0)
            return null;


        String direccion = binding.txtDireccionPisoAdd.getText().toString();
        int numero = Integer.parseInt(binding.txtNumeroPisoAdd.getText().toString());
        String ciudad = (String)binding.spCiudadPisoAdd.getSelectedItem();
        String provincia = binding.txtProvinciaPisoAdd.getText().toString();
        String cp = binding.txtCpPisoAdd.getText().toString();
        float valoracion = binding.rtbValoracionPisoAdd.getRating();

        return new Piso (direccion, numero, ciudad, provincia, cp, valoracion);
    }

}