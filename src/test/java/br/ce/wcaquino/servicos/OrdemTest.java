/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author luis.assulfi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
    
    public static int contador = 0;
    
    @Test
    public void inicia(){
        contador = 1;
    }
    
    @Test
    public void verifica(){
        Assert.assertEquals(1, contador);
    }
    
}
