/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;

/**
 *
 * @author luis.assulfi
 */
public interface SPCService {
        
    public boolean possuiNegativacao(Usuario usuario) throws Exception;
}
