package com.ray.entities.enums;

public enum Perfil {
    
    ADMINISTRADOR(0),
    SECRETARIO(1),
    RECEPCIONISTA(2);
    
    private int code;
    
    private Perfil(int code) {
	this.code = code;
    }

    public int getCode() {
	return code;
    }

    public static Perfil valueOf(int code) {
	for (Perfil value : Perfil.values()) {
	    if (value.getCode() == code) {
		return value;
	    }
	}
	throw new IllegalArgumentException("Código de perfil inválido");
    }


}
