package com.coderforfun.conversordemoneda.modelo;

public record TasaDeConversionDTO(String time_last_update_utc,

                                  String base_code,

                                  String target_code,

                                  Double conversion_rate,

                                  Double conversion_result) {
}
