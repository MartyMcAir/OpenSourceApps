package com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyDateFormat {
    private int[] hms, dmy;
    private boolean flag;
    private int showY, showMn;

    public MyDateFormat() {
        this.flag = true;
        this.showY = 2013;
        this.showMn = 4;
        this.hms = new int[3];
        this.dmy = new int[3];
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void GetDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String fullTime = dateFormat.format(date);
        boolean flagday = true;

        for (int i = 0, d = 0; i < 3; i++, d++) {
            this.hms[i] = Integer.parseInt(fullTime.substring(d, d += 2));
            if (this.hms[i] != 0) flagday = false;
        }

        if (flagday || this.flag) {
            this.flag = false;

            fullTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);

            for (int i = 0, d = 0; i < 3; i++, d++)
                if (Integer.parseInt(fullTime.substring(d, d + 1)) != 0)
                    this.dmy[i] = Integer.parseInt(fullTime.substring(d, d += 2));
                else this.dmy[i] = Integer.parseInt(fullTime.substring(d + 1, d += 2));

            showY = this.dmy[2] + 2000;
            showMn = this.dmy[1];
        }
    }

    public int getHourMinSec(int i) {
        return this.hms[i];
    }

    public int getDayMonthYear(int i) {
        return this.dmy[i];
    }

    public String getHourMinSec() {
        String time = "";

        if (this.hms[0] < 10) time += 0 + "" + this.hms[0] + ":";
        else time += this.hms[0] + ":";
        if (this.hms[1] < 10) time += 0 + "" + this.hms[1];
        else time += this.hms[1];

        return time;
    }

    public int getShowY() {
        return this.showY;
    }

    public int getShowM() {
        return this.showMn;
    }

    public void changeMonCal(int come) {
        this.showMn += come;

        if (this.showMn == 0) {
            this.showMn = 12;
            this.showY--;
        } else if (showMn == 13) {
            this.showMn = 1;
            this.showY++;
        }
    }

    public void changeMonCal() {
        showMn = dmy[1];
        showY = dmy[2] + 2000;
    }

    public String getDayMonthYear() {
        String month = "";

        if (this.showMn < 7) {
            if (this.showMn == 1) month = "  ������";
            else if (this.showMn == 2) month = " �������";
            else if (this.showMn == 3) month = "    ����";
            else if (this.showMn == 4) month = "  ������";
            else if (this.showMn == 5) month = "     ���";
            else if (this.showMn == 6) month = "    ����";
        } else {
            if (this.showMn == 7) month = "    ����";
            else if (this.showMn == 8) month = "  ������";
            else if (this.showMn == 9) month = "��������";
            else if (this.showMn == 10) month = " �������";
            else if (this.showMn == 11) month = "  ������";
            else if (this.showMn == 12) month = " �������";
        }

        return month + " " + showY;
    }

}
