/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author luis.assulfi
 */
public class LocacaoBuilder {
    
    private Locacao locacao;
    
    private LocacaoBuilder(){}

    public static LocacaoBuilder umaLocacao(){
        LocacaoBuilder builder = new LocacaoBuilder();
        builder.locacao = new Locacao();
        builder.locacao.setUsuario(UsuarioBuilder.umUsuario().agora());
        builder.locacao.setFilmes(Arrays.asList(FilmeBuilder.umFilme().agora()));
        builder.locacao.setDataLocacao(new Date());
        builder.locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(1));
        builder.locacao.setValor(4.0);
        
        return builder;
    }
    
    public LocacaoBuilder comDataRetorno(Date dataRetorno){
        locacao.setDataRetorno(dataRetorno);
        
        return this;
    }
    
    public LocacaoBuilder comUsuario(Usuario usuario){
        locacao.setUsuario(usuario);
        
        return this;
    }
    
    public LocacaoBuilder comListaFilmes(Filme... params){
        locacao.setFilmes(Arrays.asList(params));
        
        return this;
    }
    
    public LocacaoBuilder atrasada(){
        locacao.setDataLocacao(DataUtils.obterDataComDiferencaDias(-4));
        locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(-2));
        
        return this;
    }
    
    public Locacao agora(){
        return locacao;
    }
}
