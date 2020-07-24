package com.spring.euler.service.impl.solutions;

import java.util.Calendar;

public abstract class Problem19 {
    public static Integer run() {
        int result = 0;
        Calendar date = Calendar.getInstance();
        date.set(1901, Calendar.JANUARY, 1);

        while (date.get(Calendar.YEAR) < 2001) {
            date.add(Calendar.MONTH, 1);
            if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { result++; }
        }

        return result;
    }
}
