/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author luis.assulfi
 */
public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date>{

    private Integer qtdeDias;
    
    public DataDiferencaDiasMatcher(Integer qtdeDias){
        this.qtdeDias = qtdeDias;
        
    }
    
    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qtdeDias));
    }

    public void describeTo(Description d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static DataDiferencaDiasMatcher ehHojeComDiferencaDias(Integer qtdeDias){
        return new DataDiferencaDiasMatcher(qtdeDias);
    }
    
}
