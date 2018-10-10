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
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.servicos.daos.LocacaoDAO;
import br.ce.wcaquino.utils.DataUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author luis.assulfi
 */
@RunWith(PowerMockRunner.class)
//@PrepareForTest({LocacaoService.class, DataUtils.class})
@PrepareForTest(DataUtils.class)
public class LocacaoServiceTestPowerMock {

    @InjectMocks
    private LocacaoService service;

    @Mock
    private LocacaoDAO dao;

    @Mock
    private SPCService spcService;
    
    @Mock
    private EmailService emailService;

    //Definição do contador
    //private static int count = 0;
    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        //System.out.println("Before");
        //service = new LocacaoService();
        //incremento
        //count++;
        //impressão do contator
        //System.out.println("Count: " + count);
        
        //dao = Mockito.mock(LocacaoDAO.class);
        //service.setLocacaoDAO(dao);

        //spcService = Mockito.mock(SPCService.class);
        //service.setSPCService(spcService);
        
        //emailService = Mockito.mock(EmailService.class);
        //service.setEmailService(emailService);
        
        service = PowerMockito.spy(service);
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
    public void deveAlugarFilmeComSucesso() throws Exception {
        //Cenario
        //Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        //PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(28, 4, 2017));
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
        
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
        //error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
        //        CoreMatchers.is(true));
        //error.checkThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
        //error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(),
        //        DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.obterData(28, 4, 2017)), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterData(29, 4, 2017)), CoreMatchers.is(true));
        //error.checkThat(locacao.getDataRetorno(),
        //        MatchersProprios.ehHojeComDiferencaDias(1));
    }

    @Test
    //@Ignore
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws Exception {
        //Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora(),
                FilmeBuilder.umFilme().agora()
        );
        
        //PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(29, 4, 2017));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
        
        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        boolean isSegunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(),
                Calendar.MONDAY);

        //Assert.assertTrue(isSegunda);
        //Assert.assertThat(resultado.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        //Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
        Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiNumaSegunda());
        //PowerMockito.verifyNew(Date.class, Mockito.times(2)).withNoArguments();
    
        PowerMockito.verifyStatic(Mockito.times(2));
        Calendar.getInstance();
    }

     
    @Test
    public void deveAlugarFilmeSemCacularValor() throws Exception{
        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
        
        PowerMockito.doReturn(1.0).when(service, "calcularValorLocacao", filmes);
        
        //Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);
        
        //verificacao
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(1.0));
        PowerMockito.verifyPrivate(service).invoke("calcularValorLocacao", filmes);
    }
    
    @Test
    public void deveCalcularValorLocacao() throws Exception{
        //Cenario
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
        
        //Acao
        Double valor = (Double) Whitebox.invokeMethod(service, "calcularValorLocacao", filmes);
        
        //verificacao
        Assert.assertThat(valor, CoreMatchers.is(4.0));
    }

}
