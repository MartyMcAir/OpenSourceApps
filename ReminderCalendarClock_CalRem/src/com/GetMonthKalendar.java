package com;

class GetMonthKalendar {
    public GetMonthKalendar() {

    }

    public GetMonthKalendar(int needMonth, int needYearFourDigit) {
        int needM = needMonth;
        int needY = needYearFourDigit;
        if (needY >= 1913) {
            for (int day = 6, month = 1, dayN = 1, feb = 29, year = 1913, ned = 1; ned < 9; day++) {
                if (day == 32) day = 1;
                else if (month == 2 && day == feb) day = 1;
                else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) day = 1;
                if (day == 1) {
                    month++;
                    if (month == 13) {
                        month = 1;
                        year++;
                        if (year % 4 == 0) feb = 30;
                        else feb = 29;

                    }

                }

                if ((month == needM) && (needY == year) && month < (needM + 1))
                    monthBank[dayN - 1][ned - 1] = day;

                dayN++;
                if (dayN == 8) {
                    dayN = 1;
                    if (((month >= needM) && (needY == year)) || (month == 1 && needM == 12 && needY < year)) ned++;
                }
            }

        } else System.out.println("Sorry - this kalendar has start of 1913 ");
    }

    int[][] monthBank = new int[7][9];


}