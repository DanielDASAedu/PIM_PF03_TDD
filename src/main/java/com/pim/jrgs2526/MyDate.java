package com.pim.jrgs2526;

import java.time.LocalDateTime;

public class MyDate {

    private int day;
    private Months month;
    private int year;


    boolean isValidDate(Months month, int day, int year) {
        boolean isValidDate;
        switch (month) {
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER: {
                isValidDate = (day > 0 && day <= 31);
            }
            break;
            case APRIL:
            case JANUARY:
            case SEPTEMBER:
            case NOVEMBER: {
                isValidDate = (day > 0 && day <= 30);
            }
            break;
            case FEBRUARY: {
                isValidDate = (day > 0 && day <= 28) || (day == 29 && isLeapYear(year));
            }
            break;
            default:
                isValidDate = false;
        }
        return isValidDate;
    }


    boolean isLeapYear(int year) {
        boolean isLeapYear;

        isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        return isLeapYear;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }

        if (getMonth() != null) {
            if (!isValidDate(month, day, year)) {
                throw new IllegalArgumentException(ERR_INVALID_DAY);
            }
        }
        this.day = day;
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {

        if (month == null) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }

        if (Months.toMonth(month.monthNumber) == null) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }

        if (day != 0) {
            if (!isValidDate(month, day, year)) {
                throw new IllegalArgumentException(ERR_INVALID_MONTH);
            }
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {

        if (month != null && day != 0) {
            if (!isValidDate(month, day, year)) {
                throw new IllegalArgumentException(ERR_INVALID_YEAR);
            }
        }
        this.year = year;
    }

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth(int monthNumber) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    public MyDate() {
        this.day = LocalDateTime.now().getDayOfMonth();
        this.month = MyDate.Months.toMonth(LocalDateTime.now().getMonthValue());
        this.year = LocalDateTime.now().getYear();
    }

    public MyDate(int day, MyDate.Months month, int year) {

        if (day == 29 && month == Months.FEBRUARY) {
            if (!isLeapYear(year)) {
                throw new IllegalArgumentException(ERR_INVALID_DATE);
            }

        }
        setDay(day);
        setYear(year);
        setMonth(month);
    }
}
