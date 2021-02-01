package com;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

class Texture extends JPanel {
    public Texture() {
        change = Toolkit.getDefaultToolkit().getImage("images/change.png");
        Del = Toolkit.getDefaultToolkit().getImage("images/del.png");
        Add = Toolkit.getDefaultToolkit().getImage("images/add.png");
        point2 = Toolkit.getDefaultToolkit().getImage("images/point2.png");
        point1 = Toolkit.getDefaultToolkit().getImage("images/point1.png");
        Top = Toolkit.getDefaultToolkit().getImage("images/top.png");

        point = Toolkit.getDefaultToolkit().getImage("images/point.png");


        clockRoll = Toolkit.getDefaultToolkit().getImage("images/clockRoll4.png");
        clockKeyH = Toolkit.getDefaultToolkit().getImage("images/clockKeyH2.png");
        clockKeyM = Toolkit.getDefaultToolkit().getImage("images/clockKeyM2.png");
        secondRoll = Toolkit.getDefaultToolkit().getImage("images/clockKeyRed3.png");
        standH = Toolkit.getDefaultToolkit().getImage("images/plankH4.png");
        standM = Toolkit.getDefaultToolkit().getImage("images/plankM6.png");
        standS = Toolkit.getDefaultToolkit().getImage("images/standS3.png");
        standHo = Toolkit.getDefaultToolkit().getImage("images/plankHo1.png");
        helpTopRu = Toolkit.getDefaultToolkit().getImage("images/helpTopRu.png");


        MediaTracker track = new MediaTracker(this);

        track.addImage(point, 0);

        track.addImage(clockRoll, 0);
        track.addImage(clockKeyH, 0);
        track.addImage(clockKeyM, 0);
        track.addImage(secondRoll, 0);
        track.addImage(standH, 0);
        track.addImage(standM, 0);
        track.addImage(standS, 0);
        track.addImage(standHo, 0);
        track.addImage(helpTopRu, 0);


    }

    Image change;
    Image Del;
    Image Add;
    Image point2;
    Image point1;
    Image Top;
    Image point;


    Image clockRoll;
    Image clockKeyH;
    Image clockKeyM;
    Image secondRoll;
    Image standH;
    Image standM;
    Image standS;
    Image standHo;

    Image helpTopRu;


}

