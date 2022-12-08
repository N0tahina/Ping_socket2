package Graph;

import javax.swing.*;

public class Wind extends JFrame{

    String nom;
    Circle pan = new Circle();

    public Circle getPan(){
        return pan;
    }
    public void setNom(String mon){
        nom = mon;
    }

    public Wind(){
        this.setTitle(nom);
        this.setSize(400, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pan);
        this.setVisible(true);
    }
}