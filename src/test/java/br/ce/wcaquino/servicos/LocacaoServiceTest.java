package br.ce.wcaquino.servicos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.servicos.daos.LocacaoDAO;
import br.ce.wcaquino.servicos.daos.LocacaoDAOFAKE;
import br.ce.wcaquino.utils.DataUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author luis.assulfi
 */
public class LocacaoServiceTest {

    private LocacaoService service;
    
    private LocacaoDAO dao;
            
    private SPCService spcService;

    //Definição do contador
    //private static int count = 0;
    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        //System.out.println("Before");
        service = new LocacaoService();
        //incremento
        //count++;
        //impressão do contator
        //System.out.println("Count: " + count);
        dao = Mockito.mock(LocacaoDAO.class);
        service.setLocacaoDAO(dao);
        
        spcService = Mockito.mock(SPCService.class);
        service.setSPCService(spcService);
    }

    @After
    public void tearDown() {
        //System.out.println("After");
    }

    @BeforeClass
    public static void setupClass() {
        //System.out.println("Before Class");
    }

    @AfterClass
    public static void tearDownClass() {
        // System.out.println("After Class");
    }

    @Test
    public void deveAlugarFilmeComSucesso() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(FilmeBuilder.umFilme().comValor(5.0).agora());

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

        locacao = service.alugarFilme(usuario, filmes);

        //Verificação
        error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        //Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
                CoreMatchers.is(true));
        error.checkThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(),
                DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
        error.checkThat(locacao.getDataRetorno(),
                MatchersProprios.ehHojeComDiferencaDias(1));
    }

    /**
     * Forma elegante do tratamento de exceções
     *
     * @throws Exception
     */
    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancaExcecaoAoAlugarFilmeSemEstoque() throws Exception {

        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
        filmes.add(FilmeBuilder.umFilme().agora());
        filmes.add(FilmeBuilder.umFilme().agora());

        //Acao
        service.alugarFilme(usuario, filmes);
    }

    /*
    Forma robusta de validação de exceções
     */
    @Test
    public void deveLancaExcecaoAoAlugarFilmeSemEstoque2() {

        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(FilmeBuilder.umFilme().semEstoque().agora());
        filmes.add(FilmeBuilder.umFilme().semEstoque().agora());
        filmes.add(FilmeBuilder.umFilme().semEstoque().agora());

        try {
            //Acao
            service.alugarFilme(usuario, filmes);
            Assert.fail("Deveria ter lançado uma exceção");
        } catch (Exception ex) {
            Assert.assertThat(ex.getMessage(), CoreMatchers.is("Filme sem estoque"));
        }
    }

    /*
    Forma nova de validação da exceções
     */
    @Test
    public void deveLancaExcecaoAoAlugarFilmeSemEstoque3() throws Exception {

        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        //Acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
        //cenario

        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
        filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());

        try {
            //acao
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException ex) {
            Assert.assertThat(ex.getMessage(), CoreMatchers.is("Usuário inválido"));
        }
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {

        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        exception.expect(LocadoraException.class);
        exception.expectMessage("Nenhum filme encontrado");

        //Acao
        service.alugarFilme(usuario, null);
    }

    @Test
    public void devePagar75PorcentoNoFilme3() throws FilmeSemEstoqueException,
            LocadoraException {
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora());

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        //Valor correto:  4 + 4 + 3 = 11
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(11.0));
    }

    @Test
    public void devePagar50PorcentoNoFilme4() throws FilmeSemEstoqueException,
            LocadoraException {
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora());

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        //Valor correto:  4 + 4 + 3 + 2 = 13
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(13.0));
    }

    @Test
    public void devePagar0PorcentoNoFilme6() throws FilmeSemEstoqueException,
            LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Asdrubal");
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora());

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        //Valor correto:  4 + 4 + 3 + 2 + 1 = 14
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));
    }

    @Test
    public void devePagar25PorcentoNoFilme5() throws FilmeSemEstoqueException,
            LocadoraException {
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora()
        );

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        //Valor correto:  4 + 4 + 3 + 2 + 1 + 0 = 14
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));
    }

    @Test
    //@Ignore
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException,
            LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora()
        );

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        boolean isSegunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(),
                Calendar.MONDAY);

        //Assert.assertTrue(isSegunda);
        //Assert.assertThat(resultado.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        //Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
        Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiNumaSegunda());
    }
    
    public void naoDeveAlugarFilmeParaNegativadoSPC() 
            throws FilmeSemEstoqueException, LocadoraException{
        
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Usuario usuario2 = UsuarioBuilder.umUsuario().comNome("Zezé").agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
        
        Mockito.when(spcService.possuiNegativacao(usuario)).thenReturn(true);
        
        exception.expect(LocadoraException.class);
        exception.expectMessage("Usuario negativado");
        
        //acao
        service.alugarFilme(usuario, filmes);
        
        
    }

}
