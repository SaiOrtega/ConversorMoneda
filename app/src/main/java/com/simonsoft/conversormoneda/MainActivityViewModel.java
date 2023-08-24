package com.simonsoft.conversormoneda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.simonsoft.conversormoneda.modelo.Moneda;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> result ;




    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getResult(){
        if(result == null){
            result = new MutableLiveData<>();
        }
        return result;
    }


    public void conversion(String euroConvertSt, String dolarConvertSt, boolean euroADollar, boolean dollarEuro) {
        double euroConvert, dolarConvert;

        //con esto me aseguro que el string no se " " o contenga letras que puedan parar la ejecución
        if (euroConvertSt.isEmpty() || euroConvertSt.matches("[a-zA-Z]+")) {
            euroConvert = 0;
        } else {
            euroConvert = Double.parseDouble(euroConvertSt);
        }
        if (dolarConvertSt.isEmpty() || dolarConvertSt.matches("[a-zA-Z]+")) {
            dolarConvert = 0;
        } else {
            dolarConvert = Double.parseDouble(dolarConvertSt);

            Moneda moneda = new Moneda(euroConvert, dolarConvert);
            double convertido;

            if (euroADollar) {
                convertido = moneda.convertirADolar(euroConvert);
                result.setValue(String.valueOf(convertido));

            } else if(dollarEuro) {
                convertido = moneda.convertirAEuro(dolarConvert);
                result.setValue(String.valueOf(convertido));

            }
        }
    }
}
