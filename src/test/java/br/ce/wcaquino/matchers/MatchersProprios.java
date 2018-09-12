/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.matchers;

import java.util.Calendar;

/**
 *
 * @author luis.assulfi
 */
public class MatchersProprios {
    
    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }
    
    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }
}
