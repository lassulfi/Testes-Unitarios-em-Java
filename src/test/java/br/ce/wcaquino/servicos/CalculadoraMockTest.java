/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author luis.assulfi
 */
public class CalculadoraMockTest {
    
    @Mock
    private Calculadora calcMock;
    
    @Spy
    private Calculadora calcSpy;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void deveMostrarDiferencaEntreMockESpy(){
        //Mockito.when(calcMock.somar(1, 2)).thenReturn(8);
        Mockito.when(calcMock.somar(1, 2)).thenCallRealMethod();
        Mockito.when(calcSpy.somar(1, 2)).thenReturn(8);
        
        System.out.println("Mock: " + calcMock.somar(1, 2));
        System.out.println("Spy: " + calcSpy.somar(1, 2));
    }
    
    @Test
    public void teste(){
        Calculadora calc = Mockito.mock(Calculadora.class);
        
        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
        
        Assert.assertEquals(5, calc.somar(12345, -234));
        
        System.out.println(argCapt.getAllValues());
    }
    
}
