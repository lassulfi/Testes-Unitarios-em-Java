/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

/**
 *
 * @author luis.assulfi
 */
public class UsuarioBuilder {
    
    private Usuario usuario;
    
    private UsuarioBuilder(){
    
    }
    
    public static UsuarioBuilder umUsuario(){
        //Instancia da classe builder
        UsuarioBuilder builder = new UsuarioBuilder();
        //Intancia do usuario
        builder.usuario = new Usuario();
        builder.usuario.setNome("Asbrubal");
        
        return builder;
    }
    
    public UsuarioBuilder comNome(String nome){
        usuario.setNome(nome);
        
        return this;
    }
    
    public Usuario agora(){
        return usuario;
    }
}
