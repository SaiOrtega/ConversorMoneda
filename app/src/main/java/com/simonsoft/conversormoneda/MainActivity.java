package com.simonsoft.conversormoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.simonsoft.conversormoneda.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //esto funciona para otra instancia de contexto fuera de mi vista( yo utilizo el mismo contexto del mainActivity)
        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        //viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.rbDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolar.setEnabled(false);
                binding.etEuro.setEnabled(true);
                binding.rbEuro.setChecked(false);
                binding.rbDolar.setChecked(true);
                binding.etDolar.setText("0");


            }
        });

        binding.rbEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolar.setEnabled(true);
                binding.etEuro.setEnabled(false);
                binding.rbEuro.setChecked(true);
                binding.rbDolar.setChecked(false);
                binding.etEuro.setText("0");



            }
        });

        //aqui recupero los datos ingresados a la vista y los seteo al viewModel
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String euroMoneda = binding.etEuro.getText().toString();
                String dolarMoneda = binding.etDolar.getText().toString();
                boolean euroADollar = binding.rbDolar.isChecked();
                boolean dolarEuro = binding.rbEuro.isChecked();

                //el viewModel tiene la lógica y relación con el modelo
                viewModel.conversion(euroMoneda, dolarMoneda, euroADollar,dolarEuro);
            }
        });

        //el viewModel observa que esté generado el resultado y lo inyecta en el textView designado
        viewModel.getResult().observe(this, new Observer<String>(){
           @Override
           public void onChanged(String result){
               binding.txResultado.setText(result);
           }
        });



    }
}