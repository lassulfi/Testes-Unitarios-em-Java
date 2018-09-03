package br.ce.wcaquino.servicos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author luis.assulfi
 */
public class LocacaoServiceTest {

    @Test
    public void teste() {
        //Cenario
        LocacaoService service = new LocacaoService();

        Usuario usuario = new Usuario("Usuario 1");

        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
