/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author luis.assulfi
 */
public class AssertTest {
    
    @Test
    public void test(){
        //Verifica apenas condições verdadeiras
        Assert.assertTrue(true);
        //Verifica se a expressão é falsa
        Assert.assertFalse(false);
        //Verifica se dois valores são iguais
        Assert.assertEquals(1, 1);
        //para tipos primitos double e float é necessário informar um parametro
        //que define o numero de casas decimais.
        Assert.assertEquals(0.51, 0.51, 0.01);
        //Assert.assertEquals também compara strings
        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        //Comparação de duas Strings ignorando letras maiusculas
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
               
        
        //Comparação entre dois objetos
        Usuario u1 = new Usuario("Usuario 1");
        Usuario u2 = new Usuario("Usuario 1");
        Usuario u3 = null;
        
        Assert.assertEquals(u1, u2);
        
        //Verifica se dois objetos são da mesma instancia de classe
        Assert.assertSame(u2, u2);
        
        //Verifica se um objeto é nulo
        Assert.assertNull(u3);
    }
}
