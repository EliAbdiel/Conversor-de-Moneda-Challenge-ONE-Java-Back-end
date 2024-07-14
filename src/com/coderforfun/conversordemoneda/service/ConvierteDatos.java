package com.coderforfun.conversordemoneda.service;

import com.coderforfun.conversordemoneda.modelo.TasaDeConversion;
import com.coderforfun.conversordemoneda.modelo.TasaDeConversionDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvierteDatos {

    public TasaDeConversionDTO convierteDatos(String json){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        TasaDeConversionDTO tasaDeConversionDTO = gson.fromJson(json, TasaDeConversionDTO.class);
        //System.out.println("Tasa de Conversion API: " + tasaDeConversionDTO);

        TasaDeConversion tasaDeConversion = new TasaDeConversion(tasaDeConversionDTO);
        //System.out.println("Tasa de Conversion objeto: " + tasaDeConversion);

        return tasaDeConversionDTO;

    }
}
