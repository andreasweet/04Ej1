package com.example.a04_ej1;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04_ej1.Modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.a04_ej1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Piso> pisos;
    private ActivityResultLauncher<Intent> launcherCrearPisos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pisos = new ArrayList<>();
        inicializaLauncher();

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPisos.launch(new Intent(MainActivity.this, Piso_add_Activity.class));
            }
        });
    }

    private void inicializaLauncher() {
        launcherCrearPisos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null  && result.getData().getExtras() != null){
                                Piso piso =(Piso) result.getData().getExtras().getSerializable("PISO");
                                pisos.add(piso);
                                pintarElementos();
                            }
                        }
                    }
                }
        );

    }

    private void pintarElementos() {

        binding.content.contenedor.removeAllViews();
        for (Piso piso : pisos) {

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View pisoView = inflater.inflate(R.layout.piso_model_view, null);

            TextView lblDireccion = pisoView.findViewById(R.id.lblDireccionPisoView);
            TextView lblNumero = pisoView.findViewById(R.id.lblNumeroPisoView);
            TextView lblProvincia = pisoView.findViewById(R.id.lblProvinciaPisoView);
            RatingBar rtbValoracion = pisoView.findViewById(R.id.rtbValoracionPisoView);

            lblDireccion.setText(piso.getDireccion());
            lblNumero.setText(String.valueOf(piso.getNumero()));
            lblProvincia.setText(piso.getProvincia());
            rtbValoracion.setRating(piso.getValoracion());

            binding.content.contenedor.addView(pisoView);
        }


    }


}