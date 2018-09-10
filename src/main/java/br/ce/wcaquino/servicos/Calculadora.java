/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

/**
 *
 * @author luis.assulfi
 */
class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }    

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws NaoPodeDividirPorZeroException {
        
        if(b == 0){
            throw new NaoPodeDividirPorZeroException();
        }
        
        return a / b;
    }
}
