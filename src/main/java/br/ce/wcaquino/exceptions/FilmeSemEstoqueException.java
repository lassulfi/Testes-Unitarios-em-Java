/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.exceptions;

/**
 *
 * @author luis.assulfi
 */
public class FilmeSemEstoqueException extends Exception {
    
    public FilmeSemEstoqueException(String msg){
        super(msg);
    }
}
