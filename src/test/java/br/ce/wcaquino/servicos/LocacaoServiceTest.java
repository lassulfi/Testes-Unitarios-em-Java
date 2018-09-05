package br.ce.wcaquino.servicos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

/**
 *
 * @author luis.assulfi
 */
public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testeLocacao() throws FilmeSemEstoqueException, LocadoraException{
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");

        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Acao
        Locacao locacao;
        /*
        try {
            locacao = service.alugarFilme(usuario, filme);

            //Verificação
            error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
            //Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
            error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
                     CoreMatchers.is(true));
            error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(),
                     DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Não deveria lançar essa exceção");
        }
         */

        locacao = service.alugarFilme(usuario, filme);

        //Verificação
        error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        //Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
                CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(),
                DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
    }
    
    /**
     * Forma elegante do tratamento de exceções
     * @throws Exception 
     */
    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacaoFilmeSemEstoque() throws Exception{
    
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");

        Filme filme = new Filme("Filme 1", 0, 5.0);

        //Acao
        service.alugarFilme(usuario, filme);
    }
    
    /*
    Forma robusta de validação de exceções
    */
    @Test
    public void testLocacaoFilmeSemEstoque2(){
    
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");

        Filme filme = new Filme("Filme 1", 0, 5.0);

        try {
            //Acao
            service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ter lançado uma exceção");
        } catch (Exception ex) {
            Assert.assertThat(ex.getMessage(),CoreMatchers.is("Filme sem estoque"));
        }
    }
    
    /*
    Forma nova de validação da exceções
    */
    @Test
    public void testLocacaoFilmeSemEstoque3() throws Exception{
    
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");

        Filme filme = new Filme("Filme 1", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");
        
        //Acao
        service.alugarFilme(usuario, filme);
    }
    
    @Test
    public void testLocacaoUsuarioVazio() throws FilmeSemEstoqueException{
        //cenario
        LocacaoService service = new LocacaoService();
        
        Filme filme = new Filme("Filme 1", 1, 5.0);
        
        try {
            //acao
            service.alugarFilme(null, filme);
            Assert.fail();
        }  catch (LocadoraException ex) {
            Assert.assertThat(ex.getMessage(), CoreMatchers.is("Usuário inválido"));
        }
    }
    
    @Test
    public void testLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");
        
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme inválido");
        
        //Acao
        service.alugarFilme(usuario, null);
    }
}
