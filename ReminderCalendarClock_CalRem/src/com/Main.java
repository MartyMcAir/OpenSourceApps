package com;

public class Main // TODO ������ ���������
{
    static MyFrame frame; // �����
    static int fSizeX = 290; // ��������� ������
    static int fSizeY = 260; // ��������� ������

    public static void main(String[] args) {
        frame = new MyFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(fSizeX, fSizeY); // ������ ��������� ������� ����
        frame.setResizable(false); // ���� ������ ����������� �������
        frame.setVisible(true); // ������� ��� ����������� ����
    }
}
