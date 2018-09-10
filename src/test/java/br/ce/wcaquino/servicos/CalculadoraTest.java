/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author luis.assulfi
 */
public class CalculadoraTest {
    
    private Calculadora calc;
    
    @Before
    public void setup(){
        calc = new Calculadora();
    }
    
    @Test
    public void deveSomarDoisValores(){
        //cenario
        int a = 5;
        int b = 3;
               
        //acao
        int resultado = calc.somar(a, b);
        
        //verificacao
        Assert.assertEquals(8, resultado);
        
    }
    
    @Test
    public void deveSubstrairDoisValores(){
        //cenario
        int a = 8;
        int b = 5;
        
        Calculadora calc = new Calculadora();
        
        //acao
        int resultado = calc.subtrair(a, b);
        
        //verificacao
        Assert.assertEquals(3, resultado);
    }
    
    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
        //cenario
        int a = 6;
        int b = 3;
        
        //acao
        int resultado = calc.dividir(a, b);
        
        //verificacao
        Assert.assertEquals(2, resultado);
    }
    
    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException{
        //cenario
        int a = 10;
        int b = 0;
        
        //acao
        calc.dividir(a, b);
        
        //verificacao
    }
}
