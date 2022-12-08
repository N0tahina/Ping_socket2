package Graph;
import javax.swing.*;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.*;

public class Circle extends JPanel{

    int xrec1;
    int yrec1;
    int xrec2;
    int yrec2;
    int xo1;
    int yo1;
    String score1;
    String score2;

    public void setXrec1(int x){
        this.xrec1 = x;
    }

    public void setYrec1(int y){
        this.yrec1 = y;
    }

    public void setXrec2(int x){
        this.xrec2 = x;
    }

    public void setYrec2(int y){
        this.yrec2 = y;
    }

    public void setXo1(int xo1) {
        this.xo1 = xo1;
    }

    public void setYo1(int yo1) {
        this.yo1 = yo1;
    }

    public void setScore1(String xo1) {
        this.score1 = xo1;
    }

    public void setScore2(String yo1) {
        this.score2 = yo1;
    }



    public void paint(Graphics g){

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 400, 600);

        g.setColor(Color.BLACK);
        g.fillRect(this.xrec1, this.yrec1, 80, 10); 

        g.setColor(Color.RED);
        g.fillRect(this.xrec2, this.yrec2, 80, 10); 

        g.setColor(Color.WHITE);
        g.fillOval( this.xo1 , this.yo1, 20, 20);  

        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana",Font.BOLD,50));
        g.drawString(""+score1, 400/2-25, 600/2+20);

        g.setColor(Color.RED);
        g.setFont(new Font("Verdana",Font.BOLD,50));
        g.drawString(""+score2, 400/2-25, 600/2-50);

    }

}