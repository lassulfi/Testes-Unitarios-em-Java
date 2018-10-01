/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

/**
 *
 * @author luis.assulfi
 */
public class FilmeBuilder {
    
    private Filme filme;
    
    private FilmeBuilder(){
    
    }
    
    public static FilmeBuilder umFilme(){
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setNome("Zorra total");
        builder.filme.setEstoque(2);
        builder.filme.setPrecoLocacao(5.0);
        
        return builder;
    }
    
    public FilmeBuilder semEstoque(){
        filme.setEstoque(0);
        
        return this;
    }
    
    public Filme agora(){
        return filme;
    }
    
}
