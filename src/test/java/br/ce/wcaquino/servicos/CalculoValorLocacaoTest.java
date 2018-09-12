/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Classe de testes parametriz�veis
 * 
 * @author luis.assulfi
 */
@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
    
    private LocacaoService service; 
    
    @Parameter
    public List<Filme> filmes;
    
    @Parameter(value = 1) 
    public double valorLocacao;
    
    @Parameter(value = 2)
    public String cenario;
    
    private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
    private static Filme filme7 = new Filme("Filme 7", 2, 4.0);
    
    @Before
    public void setup(){
        service = new LocacaoService();
    }
    
    @Parameters(name = "{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
            {Arrays.asList(filme1, filme2) , 8.0, "2 Filmes: Sem desconto"},
            {Arrays.asList(filme1, filme2, filme3) , 11.0, "3 Filmes: 25%"},
            {Arrays.asList(filme1, filme2, filme3, filme4) , 13.0, "4 Filmes: 50%"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5) , 14.0,  "5 Filmes: 75%"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6) , 14.0, "6 Filmes: 100%"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7) , 18.0, "2 Filmes: Sem desconto"}
        });
    }
    
    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, 
            LocadoraException{
        //cenario
        Usuario usuario = new Usuario("Asdrubal");
        
        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);
        
        //verificacao
        //Valor correto:  4 + 4 + 3 + 2 + 1 + 0 = 14
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(valorLocacao));
    }
    
    @Test
    public void print(){
        System.out.println(valorLocacao);
    }
}
