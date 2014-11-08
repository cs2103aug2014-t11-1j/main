//@author A0111370Y
package com;

import java.util.Calendar;


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
