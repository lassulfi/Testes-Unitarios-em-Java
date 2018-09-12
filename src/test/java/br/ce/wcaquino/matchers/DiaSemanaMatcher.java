/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author luis.assulfi
 */
public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

    private Integer diaSemana;
    
    public DiaSemanaMatcher(Integer diaSemana){
        this.diaSemana = diaSemana;
    }
    
    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.verificarDiaSemana(data, diaSemana);
    }

    public void describeTo(Description desc) {

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, diaSemana);
        String dataExtenso = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        
        desc.appendText(dataExtenso);
    }
    
}
