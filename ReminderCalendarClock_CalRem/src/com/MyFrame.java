package com;

import java.awt.Container;

class MyFrame extends javax.swing.JFrame // ��������� javax.swing.JFrame
{
    public MyFrame() // �����������
    {
        setTitle("CalRem"); // �������� �� ��������� �� ������
        MyPanel panel = new MyPanel();
        Container pane = getContentPane(); // ���������
        pane.add(panel); // �������� ������ � ��������� ��� �����������
    }

}