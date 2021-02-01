package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MyPanel extends javax.swing.JPanel {
    MyDateFormat date;
    ActionListener one;
    Texture textures;
    Timer t;
    int width, height;
    boolean TopFlag, TopButFlag, BigTimeFlag, MesFlag, ChangeFlag;
    Font FontTime, FontDate;
    GetMonthKalendar calend;
    boolean flag = true;
    int shiftMx = 0;
    int shiftMxR = 0;

    JButton ButtonTop = new JButton("");
    JButton ButtonAdd = new JButton("");
    JButton ButtonDel = new JButton("");
    JButton ButtonChange = new JButton("");

    JButton LButton = new JButton("<<");
    JButton MButton = new JButton(">>");

    JButton ok = new JButton("Ok");
    JButton save = new JButton("Save");
    JButton delete = new JButton("Delete");
    JComboBox<String> month = new JComboBox<String>(); // �����
    JComboBox<String> day = new JComboBox<String>(); // ����
    JComboBox<String> hours = new JComboBox<String>(); // ���
    JComboBox<String> minutes = new JComboBox<String>(); // ���
    ImageIcon imagico;
    boolean RemAddFlag = false;

    JButton LDelButton = new JButton("<<");
    JButton MDelButton = new JButton(">>");

    JTextField yourText = new JTextField();
    JTextField memWindowMon = new JTextField();
    JTextField memWindowDay = new JTextField();
    JTextField memWindowHour = new JTextField();
    JTextField memWindowMin = new JTextField();
    JTextField memWindowText = new JTextField();
    boolean datadelFlag = true;
    boolean Remflag = false;
    boolean asa = false;
    //Set<Memory> kipMemory = new HashSet<Memory>(10);
    String kip[][] = new String[100][9];
    int kop = 0;
    JButton remButton = new JButton("Ok");
    int del = 0;
    int m = 0;
    int x, y;

    public MyPanel() {
        addMouseMotionListener(new MyMove());
        addMouseListener(new MyMouse());
        one = new One();
        t = new Timer(200, one);
        date = new MyDateFormat();
        textures = new Texture();
        TopFlag = true;
        FontTime = new Font("Courier New", 1, 22);
        FontDate = new Font("Courier New", 1, 15);
        calend = new GetMonthKalendar(date.getShowM(), date.getShowY());
        ButtonTop.addActionListener(one);
        TopButFlag = false;
        BigTimeFlag = false;

        ButtonAdd.addActionListener(one);
        ButtonDel.addActionListener(one);
        ButtonChange.addActionListener(one);
        LButton.addActionListener(one);
        MButton.addActionListener(one);
        month.addActionListener(one);
        minutes.addActionListener(one);
        hours.addActionListener(one);
        day.addActionListener(one);
        yourText.addActionListener(one);
        ok.addActionListener(one);
        save.addActionListener(one);
        delete.addActionListener(one);
        LDelButton.addActionListener(one);
        MDelButton.addActionListener(one);
        remButton.addActionListener(one);
        this.add(ButtonTop);
        this.add(ButtonAdd);
        this.add(ButtonDel);
        this.add(ButtonChange);
        this.add(LButton);
        this.add(MButton);
        this.add(LDelButton);
        this.add(MDelButton);
        this.add(yourText);
        this.add(ok);
        this.add(save);
        this.add(delete);
        this.add(remButton);
        ButtonAdd.setVisible(false);
        ButtonDel.setVisible(false);
        ButtonChange.setVisible(false);
        yourText.setVisible(false);
        ok.setVisible(false);
        this.add(month);
        this.add(day);
        this.add(minutes);
        this.add(hours);
        month.setVisible(false);
        minutes.setVisible(false);
        hours.setVisible(false);
        day.setVisible(false);
        save.setVisible(false);
        delete.setVisible(false);
        LDelButton.setVisible(false);
        MDelButton.setVisible(false);
        MesFlag = false;
        ChangeFlag = false;
        remLoader();
        memAddItems();

        this.add(memWindowMon);
        this.add(memWindowDay);
        this.add(memWindowHour);
        this.add(memWindowMin);
        this.add(memWindowText);
        memWindowMon.setVisible(!datadelFlag);
        memWindowDay.setVisible(!datadelFlag);
        memWindowHour.setVisible(!datadelFlag);
        memWindowMin.setVisible(!datadelFlag);
        memWindowText.setVisible(!datadelFlag);
        remButton.setVisible(false);
    }

    public void dataScore() // �������� ������ ��� �����������
    {

        if (yourText.getText() != "") {
            kip[kop][0] = (String) month.getSelectedItem();
            kip[kop][1] = (String) day.getSelectedItem();
            kip[kop][2] = (String) hours.getSelectedItem();
            kip[kop][3] = (String) minutes.getSelectedItem();
            kip[kop][4] = yourText.getText();
            kip[kop][5] = "";
            kip[kop][6] = "";
            kip[kop][7] = "";
            kip[kop][8] = "";
            System.out.println(kip[kop]);
            kop++;
        }
        yourText.setText("");
    }

    public void dataDel() // �������� ������ ��� �����������
    {
        Font memKip = new Font("Courier New", 1, 16);
        memWindowMon.setBackground(Color.LIGHT_GRAY);
        memWindowMon.setFont(memKip);
        memWindowMon.setEditable(false);

        memWindowDay.setBackground(Color.LIGHT_GRAY);
        memWindowDay.setFont(memKip);
        memWindowDay.setEditable(false);

        memWindowHour.setBackground(Color.LIGHT_GRAY);
        memWindowHour.setFont(memKip);
        memWindowHour.setEditable(false);

        memWindowMin.setBackground(Color.LIGHT_GRAY);
        memWindowMin.setFont(memKip);
        memWindowMin.setEditable(false);

        memWindowText.setBackground(Color.LIGHT_GRAY);
        memWindowText.setFont(memKip);
        memWindowText.setEditable(false);

        memWindowMon.setText(kip[del][0]);
        memWindowDay.setText(kip[del][1]);
        memWindowHour.setText(kip[del][2]);
        memWindowMin.setText(kip[del][3]);
        memWindowText.setText(kip[del][4]);
        m = 0;
    }

    public void Delete() {
        if (kop != 0) {
            for (int i = del; i < kop; i++)
                for (int k = 0; k < 9; k++)
                    kip[i][k] = kip[i + 1][k];

            for (int k = 0; k < 9; k++)
                kip[kop][k] = "";

            for (int k = 0; k < 9; k++)
                System.out.println(kip[del][k]);

            memWindowMon.setText(kip[del + 1][0]);
            memWindowDay.setText(kip[del + 1][1]);
            memWindowHour.setText(kip[del + 1][2]);
            memWindowMin.setText(kip[del + 1][3]);
            memWindowText.setText(kip[del + 1][4]);
            kop--;
            remSaver();
        }
    }

    public void paintBackground(Graphics2D g2)// ���
    {
        setBackground(Color.BLACK);
        g2.setColor(new Color(36, 36, 36));

        for (int lin = 0; lin < width; lin += 10)
            g2.drawLine(lin, 0, lin, height);

        for (int lin = 0; lin < height; lin += 10)
            g2.drawLine(0, lin, width, lin);

    }

    public void paintTopTime(Graphics2D g2)// ����������� ����
    {
        Main.frame.setSize(142, 60);

        g2.drawImage(textures.Top, 5, 5, 85, 155, 0, 0, 60, 150, null);
        g2.setColor(Color.white);
        g2.drawRect(3, 3, 83, 28);
        g2.setColor(Color.black);
        g2.drawString(date.getHourMinSec(), 12, 23);
        imagico = new ImageIcon(textures.point1);
        ButtonTop.setIcon(imagico);
        ButtonTop.setBounds(90, 3, 42, 28);
        ButtonAdd.setVisible(false);
        LButton.setVisible(false);
        MButton.setVisible(false);
    }

    public void memAddItems() // ������� ����������� �������� �� ���� �����/����/�����/�����/���������
    {

        for (int itm = 0; itm < 60; itm++) {
            minutes.addItem("" + itm);
            if (itm < 24) hours.addItem("" + itm);
            if (itm > 0 && itm < 32) {
                day.addItem("" + itm);
                if (itm < 13) month.addItem("" + itm);
            }
        }
    }

    public void paintCalendar(Graphics2D g2) {
        g2.setFont(FontDate);
        g2.setColor(Color.green);
        g2.drawString(date.getDayMonthYear(), 34, 50);
        Font font = new Font("Courier New", Font.BOLD, 12);
        int monthX = 60;
        int monthXdig = 50;
        int monthXwk = 40;
        int monthY = 70;
        for (int ned = 0; ned < 8; ned++) {
            for (int dn = 0; dn < 7; dn++) {
                monthX = monthXwk;
                g2.setFont(font);
                g2.setColor(Color.green);
                if (dn > 4) g2.setColor(Color.orange);

                String dayWeek = "";
                if (dn < 4) {
                    if (dn == 0) dayWeek = "��";
                    else if (dn == 1) dayWeek = "��";
                    else if (dn == 2) dayWeek = "��";
                    else if (dn == 3) dayWeek = "��";
                } else {
                    if (dn == 4) dayWeek = "��";
                    else if (dn == 5) dayWeek = "��";
                    else if (dn == 6) dayWeek = "��";
                }

                g2.drawString(dayWeek, monthX, dn * 16 + monthY + 11);

                if (calend.monthBank[dn][ned] != 0) {
                    monthX = monthXdig;
                    if (date.getShowM() == date.getDayMonthYear(1) && date.getDayMonthYear(0) == calend.monthBank[dn][ned] && date.getShowY() - 2000 == date.getDayMonthYear(2))
                        g2.drawRect((ned + 1) * 19 + monthX - 2, dn * 16 + monthY, 17, 13);

                    g2.setColor(Color.gray);
                    g2.drawString("" + calend.monthBank[dn][ned], (ned + 1) * 19 + monthX, dn * 16 + monthY + 11);
                }

            }
        }
        LButton.setBackground(new Color(239, 228, 176));
        MButton.setBackground(new Color(239, 228, 176));
        LButton.setBounds(165, 10, 50, 30);
        MButton.setBounds(220, 10, 50, 30);

    }

    public void showMemScore() {
        hours.setSelectedIndex(date.getHourMinSec(0));
        minutes.setSelectedIndex(date.getHourMinSec(1));
        day.setSelectedIndex(date.getDayMonthYear(0) - 1);
        month.setSelectedIndex(date.getDayMonthYear(1) - 1);


    }

    public void paintComponent(Graphics g) // ������ �����
    {
        t.start();

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        width = Main.frame.getWidth();
        height = Main.frame.getHeight();

        paintBackground(g2);

        g2.setFont(FontTime);
        g2.setColor(Color.orange);
        date.GetDateFormat();

        if (flag) {
            flag = false;
            calend = new GetMonthKalendar(date.getDayMonthYear(1), date.getShowY());
        } else {
            for (int i = 0; i < 3; i++)
                if (date.getHourMinSec(i) != 0) {
                    flag = false;
                    break;
                } else flag = true;
        }
        Reminder();

        if (TopFlag) paintTopTime(g2);
        else {
            if (BigTimeFlag) paintBigTime(g2);
            else paintCalendar(g2);
            ButtonTop.setIcon(imagico);
            ButtonTop.setBounds(5, 200, 42, 28);
            g2.setColor(Color.white);
            g2.drawOval(5, 5, 40, 40);
            g2.setFont(new Font("Courier New", 1, 36));
            g2.drawString("?", 15, 35);
            g2.drawRect(220, 60, 52, 144);
            ButtonAdd.setIcon(new ImageIcon(textures.Add));
            ButtonAdd.setBounds(225, 70, 42, 28);
            ButtonAdd.setVisible(true);

            ButtonDel.setIcon(new ImageIcon(textures.Del));
            ButtonDel.setBounds(225, 118, 42, 28);
            ButtonDel.setVisible(true);

            ButtonChange.setIcon(new ImageIcon(textures.change));
            ButtonChange.setBounds(225, 166, 42, 28);
            ButtonChange.setVisible(true);
            ButtonTop.setVisible(true);

            LButton.setVisible(!BigTimeFlag);
            MButton.setVisible(!BigTimeFlag);

            if (MesFlag) {
                ButtonAdd.setVisible(false);
                ButtonDel.setVisible(false);
                ButtonChange.setVisible(false);
                ButtonTop.setVisible(false);
                LButton.setVisible(false);
                MButton.setVisible(false);
                g2.drawImage(textures.helpTopRu, 5, 5, null);

            }

            if (!RemAddFlag) {
                g2.setColor(Color.ORANGE);

                g2.setFont(new Font("Courier New", 4, 16));
                g2.drawString(" �����", 280, 34);
                g2.drawString("����", 348, 34);
                g2.drawString("���", 403, 34);
                g2.drawString("���", 458, 34);
                month.setBounds(295, 39, 40, 25);
                day.setBounds(345, 39, 40, 25);
                hours.setBounds(405, 39, 40, 25);
                minutes.setBounds(455, 39, 40, 25);
                g2.drawString(" ����������� ", 325, 90);
                yourText.setBounds(295, 100, 200, 20);
                ok.setBounds(340, 140, 50, 30);
                ok.setVisible(true);
                save.setBounds(400, 140, 70, 30);
                save.setVisible(true);
            } else {
                ok.setVisible(false);
                save.setVisible(false);
            }

            if (!datadelFlag) {
                memWindowMon.setBounds(295, 39, 40, 25);
                memWindowDay.setBounds(345, 39, 40, 25);
                memWindowHour.setBounds(405, 39, 40, 25);
                memWindowMin.setBounds(455, 39, 40, 25);
                memWindowText.setBounds(295, 100, 200, 20);
                g2.setColor(Color.ORANGE);

                g2.setFont(new Font("Courier New", 4, 16));
                g2.drawString(" �����", 280, 34);
                g2.drawString("����", 348, 34);
                g2.drawString("���", 403, 34);
                g2.drawString("���", 458, 34);
                g2.drawString(" ����������� ", 325, 90);
                delete.setBounds(350, 140, 70, 30);
                delete.setVisible(true);

                LDelButton.setBackground(new Color(239, 228, 176));
                MDelButton.setBackground(new Color(239, 228, 176));
                LDelButton.setBounds(335, 200, 50, 30);
                MDelButton.setBounds(390, 200, 50, 30);
                LDelButton.setVisible(true);
                MDelButton.setVisible(true);
            } else {
                delete.setVisible(false);
                LDelButton.setVisible(false);
                MDelButton.setVisible(false);
            }

            if (Remflag)// ��������� �����������
            {
                try {
                    goSound();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                g2.setFont(new Font("Courier New", 1, 18));
                g2.setColor(Color.orange);
                g2.drawString(kip[del][3], 45, 300);
                g2.drawString(kip[del][2], 20, 300);
                g2.setColor(new Color(195, 255, 125));
                g2.drawString(kip[del][4], 70, 300);
                remButton.setVisible(true);
                remButton.setBounds(70, 305, 50, 40);
            }
        }
    }

    public void goSound() throws Exception {


        File vorona = new File("sounds/vorona1.au");
        AudioInputStream sound = AudioSystem.getAudioInputStream(vorona);
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.addLineListener(new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            }
        });

        clip.start();
    }

    public void Reminder() {
        for (int i = 0; i < kop; i++)
            if (Integer.parseInt(kip[i][0]) == date.getDayMonthYear(1) && Integer.parseInt(kip[i][1]) == date.getDayMonthYear(0) && Integer.parseInt(kip[i][2]) <= date.getHourMinSec(0) && Integer.parseInt(kip[i][3]) <= date.getHourMinSec(1)) {

                del = i;
                Remflag = true;
                break;
            } else {


                del = 0;
                Remflag = false;
            }
        System.out.println("Reminder--------------------" + " " + Remflag);
    }

    public void paintBigTime(Graphics2D g2)// ���� ����
    {
        double copy = 5 * 0.10471975512;
        for (int pn = 1; pn < 61; pn++) {
            g2.rotate(copy, 110, 115);
            if (pn % 3 == 0) continue;
            g2.drawImage(textures.standHo, 109, 209, 3, -3, null);
        }

        g2.drawString("12", 100, 25);
        g2.drawString("9", 14, 119);
        g2.drawString("6", 104, 215);
        g2.drawString("3", 196, 119);

        copy = date.getHourMinSec(0) * 0.10471975512 * 5 + (date.getHourMinSec(1) / 12) * 0.10471975512;
        g2.rotate(copy, 110, 115);
        g2.drawImage(textures.standH, 108, 122, 5, -54, null);
        g2.rotate(copy * (-1), 110, 115);

        copy = date.getHourMinSec(1) * 0.10471975512;
        g2.rotate(copy, 110, 115);
        g2.drawImage(textures.standM, 109, 129, 3, -80, null);
        g2.rotate(copy * (-1), 110, 115);

        copy = date.getHourMinSec(2) * 0.10471975512;
        g2.rotate(copy, 110, 115);
        g2.drawImage(textures.standS, 106, 137, 9, -100, null);
        g2.rotate(copy * (-1), 110, 115);
    }


    private class One implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == ButtonTop)
                if (TopFlag) {
                    BigTimeFlag = TopFlag;
                    TopFlag = false;
                    Main.frame.setSize(Main.fSizeX, Main.fSizeY);
                    imagico.setImage(textures.point2);
                    if (Remflag) asa = true;
                    x = Main.fSizeX;
                    y = Main.fSizeY + 120;
                } else {
                    BigTimeFlag = TopFlag;
                    TopFlag = true;
                    Main.frame.setSize(142, 60);
                    imagico.setImage(textures.point1);
                    if (Remflag) asa = true;
                    x = 142;
                    y = 60 + 120;
                }

            if (event.getSource() == ButtonChange)
                if (ChangeFlag) {
                    BigTimeFlag = false;
                    ChangeFlag = false;
                    if (Remflag) asa = true;
                } else {
                    BigTimeFlag = true;
                    ChangeFlag = true;
                    if (Remflag) asa = true;
                }

            if (event.getSource() == LButton) {
                date.changeMonCal(-1);
                calend = new GetMonthKalendar(date.getShowM(), date.getShowY());
                if (Remflag) asa = true;
            }

            if (event.getSource() == MButton) {
                date.changeMonCal(1);
                calend = new GetMonthKalendar(date.getShowM(), date.getShowY());
                if (Remflag) asa = true;
            }

            if (event.getSource() == remButton) {
                Remflag = false;
                Delete();
                remButton.setVisible(false);
                remSaver();
                Reminder();
                if (Remflag) asa = true;
            }
            if (event.getSource() == ButtonAdd)
                if (RemAddFlag) {
                    if (Remflag) asa = true;
                    showMemScore();
                    Main.frame.setSize(550, 260);
                    x = 550;
                    y = 260 + 120;
                    RemAddFlag = false;
                    datadelFlag = true;
                    month.setVisible(!RemAddFlag);
                    minutes.setVisible(!RemAddFlag);
                    hours.setVisible(!RemAddFlag);
                    day.setVisible(!RemAddFlag);
                    yourText.setVisible(!RemAddFlag);
                    memWindowMon.setVisible(!datadelFlag);
                    memWindowDay.setVisible(!datadelFlag);
                    memWindowHour.setVisible(!datadelFlag);
                    memWindowMin.setVisible(!datadelFlag);
                    memWindowText.setVisible(!datadelFlag);

                } else {
                    if (Remflag) asa = true;
                    Main.frame.setSize(Main.fSizeX, Main.fSizeY);
                    x = Main.fSizeX;
                    y = Main.fSizeY + 120;
                    RemAddFlag = true;
                    datadelFlag = false;
                    month.setVisible(!RemAddFlag);
                    minutes.setVisible(!RemAddFlag);
                    hours.setVisible(!RemAddFlag);
                    day.setVisible(!RemAddFlag);
                    yourText.setVisible(!RemAddFlag);
                    memWindowMon.setVisible(!datadelFlag);
                    memWindowDay.setVisible(!datadelFlag);
                    memWindowHour.setVisible(!datadelFlag);
                    memWindowMin.setVisible(!datadelFlag);
                    memWindowText.setVisible(!datadelFlag);
                    if (kop != 0) {
                        remSaver();
                    }

                }

            if (event.getSource() == ButtonDel)
                if (datadelFlag) {
                    if (Remflag) asa = true;
                    showMemScore();
                    Main.frame.setSize(550, 260);
                    x = 550;

                    y = 260 + 120;
                    datadelFlag = false;
                    RemAddFlag = true;
                    memWindowMon.setVisible(!datadelFlag);
                    memWindowDay.setVisible(!datadelFlag);
                    memWindowHour.setVisible(!datadelFlag);
                    memWindowMin.setVisible(!datadelFlag);
                    memWindowText.setVisible(!datadelFlag);
                    month.setVisible(!RemAddFlag);
                    minutes.setVisible(!RemAddFlag);
                    hours.setVisible(!RemAddFlag);
                    day.setVisible(!RemAddFlag);
                    yourText.setVisible(!RemAddFlag);

                    dataDel();
                } else {
                    if (Remflag) asa = true;
                    Main.frame.setSize(Main.fSizeX, Main.fSizeY);
                    x = Main.fSizeX;
                    y = Main.fSizeY + 120;
                    datadelFlag = true;
                    RemAddFlag = false;

                    memWindowMon.setVisible(!datadelFlag);
                    memWindowDay.setVisible(!datadelFlag);
                    memWindowHour.setVisible(!datadelFlag);
                    memWindowMin.setVisible(!datadelFlag);
                    memWindowText.setVisible(!datadelFlag);
                    month.setVisible(!RemAddFlag);
                    minutes.setVisible(!RemAddFlag);
                    hours.setVisible(!RemAddFlag);
                    day.setVisible(!RemAddFlag);
                    yourText.setVisible(!RemAddFlag);
                    if (kop != 0) {
                        remSaver();
                    }

                }
            if (event.getSource() == delete) {
                if (Remflag) asa = true;

                Delete();
            }

            if (event.getSource() == LDelButton) {
                if (Remflag) asa = true;
                if (m > 0) {
                    m--;
                    del = m;
                    memWindowMon.setText(kip[m][0]);
                    memWindowDay.setText(kip[m][1]);
                    memWindowHour.setText(kip[m][2]);
                    memWindowMin.setText(kip[m][3]);
                    memWindowText.setText(kip[m][4]);

                }
            }
            if (event.getSource() == MDelButton) {
                if (Remflag) asa = true;
                x = 550;
                y = 260 + 120;
                if (m <= kop) {
                    m++;
                    del = m;
                    memWindowMon.setText(kip[m][0]);
                    memWindowDay.setText(kip[m][1]);
                    memWindowHour.setText(kip[m][2]);
                    memWindowMin.setText(kip[m][3]);
                    memWindowText.setText(kip[m][4]);

                }
            }
            if (event.getSource() == ok) {
                dataScore();
                x = 550;
                y = 260 + 120;
                Reminder();
                if (Remflag) asa = true;

                //	System.out.println( kipMemory.size() );
                //data.remSaver();

            }
            if (event.getSource() == save) {
                //remLoader();
                x = 550;
                y = 260 + 120;
                if (Remflag) asa = true;
                if (kop != 0)
                    remSaver();
            }
            if (Remflag && asa) {
                asa = false;
                Main.frame.setSize(x, y);
            }
            repaint();
        }
    }

    private class MyMove implements MouseMotionListener {
        private int posX, posY;

        public void mouseMoved(MouseEvent event) {
            posX = event.getX();
            posY = event.getY();

            if (!TopFlag) {
                if (posX > 5 && posX < 35 && posY > 5 && posY < 35) MesFlag = true;
                else MesFlag = false;

            }
        }

        public void mouseDragged(MouseEvent arg0) {

        }

    }

    private class MyMouse extends MouseAdapter {
        private int posX, posY;

        public void mousePressed(MouseEvent event) {

        }

        public void mouseClicked(MouseEvent event) {
            if (!TopFlag && !BigTimeFlag) {
                date.changeMonCal();
                calend = new GetMonthKalendar(date.getShowM(), date.getShowY());
            }
            Main.frame.setAlwaysOnTop(false);
        }

    }

    public void remSaver() {
        PrintWriter saver = null;
        try {
            saver = new PrintWriter(new FileOutputStream("keeper/data.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int rs = 0; rs < kop; rs++)
            for (int w = 0; w < 9; w++)
                saver.println(kip[rs][w]);


        for (int rs = 0; rs < kop; rs++)
            for (int w = 0; w < 9; w++)
                System.out.println(kip[rs][w]);
        saver.close();

    }

    public void remLoader() {
        String line = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("keeper/data.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int rs = 0;
        try {
            while ((line = reader.readLine()) != null) {

                kip[kop][rs] = line;
                rs++;
                if (rs == 9) {
                    rs = 0;
                    kop++;
                }

            }

            for (int k = 0; k < kop; k++)
                for (int w = 0; w < 9; w++)
                    System.out.println(kip[k][w]);

            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}