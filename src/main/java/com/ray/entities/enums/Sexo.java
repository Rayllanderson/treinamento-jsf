package com.ray.entities.enums;


public enum Sexo {
    
    MASCULINO(0),
    FEMININO(1);
    
    private int code;
    
    private Sexo(int code) {
	this.code = code;
    }

    public int getCode() {
	return code;
    }

    public static Sexo valueOf(int code) {
	for (Sexo value : Sexo.values()) {
	    if (value.getCode() == code) {
		return value;
	    }
	}
	throw new IllegalArgumentException("Código de sexo inválido");
    }

}
