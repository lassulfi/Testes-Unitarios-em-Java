/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author luis.assulfi
 */
public class CalculadoraMockTest {
    
    @Test
    public void teste(){
        Calculadora calc = Mockito.mock(Calculadora.class);
        Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);
        
        System.out.println(calc.somar(2, 8));
    }
    
}
