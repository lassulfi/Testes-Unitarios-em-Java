/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.suites;

import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author luis.assulfi
 */

@RunWith(Suite.class)
@SuiteClasses({
    CalculadoraTest.class,
    CalculoValorLocacaoTest.class,
    LocacaoServiceTest.class
})
public class SuiteExecucao {
    
    //Remova se puder
    @BeforeClass
    public static void before(){
        System.out.println("before");
    }
    
    @AfterClass
    public static void after(){
        System.out.println("after");
    }
}
