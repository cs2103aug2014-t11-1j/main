/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Calendar;

/**
 *
 * @author Jireh
 */
public class TimePeriodTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        TimePeriod test = new TimePeriod();

        cal.set(2013, 1, 15);

        //test.setStartDate(cal.getTime());

        System.out.println(test);
    }

}
