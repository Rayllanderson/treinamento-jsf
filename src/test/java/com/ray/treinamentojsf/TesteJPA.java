package com.ray.treinamentojsf;

import javax.persistence.Persistence;

/**
 * Unit test for simple App.
 */
public class TesteJPA {
   
    public static void main(String[] args) {
	Persistence.createEntityManagerFactory("treinamento-jsf");
    }
}
