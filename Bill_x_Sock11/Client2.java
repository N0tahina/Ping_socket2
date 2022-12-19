package Mouvement;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import Graph.Wind;

public class Client2 implements KeyListener
{
	int xrec;
	int yrec;
	int xrec2;
	int yrec2;
    Socket socket;
    String sign;
	Boolean arret;
	int x;
	int y;
	boolean backX = true;
    boolean backY = true;
	int xplus; int yplus;
	int score1; int score2;
	

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int xx){
		x = xx;
	}
	public void setY(int yy){
		y = yy;
	}
	public int getXrec(){
		return xrec;
	}
	public int getYrec(){
		return yrec;
	}
	public int getXrec2(){
		return xrec2;
	}
	public int getYrec2(){
		return yrec2;
	}
	public Socket getSocket(){
		return socket;
	}
    public String getSign(){
		return sign;
	}
	public Boolean getArret(){
		return arret;
	}
	public boolean getBackX(){
		return backX;
	}
	public boolean getBackY(){
		return backY;
	}
	public void setBackX(boolean b){
		backX = b;
	}
	public void setBackY(boolean b){
		backY = b;
	}
    public int getXplus(){
		return this.xplus ;
	}
	public int getYplus(){
	return this.yplus ;
	}
	public void setXplus(int x){
		this.xplus = x;
	}
	public void setYplus(int x){
		this.yplus = x;
	}		
	public void setScore1(int x){
		this.score1 = x;
	}
	public void setScore2(int x){
		this.score2 = x;
	}	
	public int getScore1(){
		return this.score1 ;
	}
	public int getScore2(){
	return this.score2 ;
	}
	
	public Client2() throws UnknownHostException, IOException
	{
		this.xplus = 1; this.yplus = 1;
		xrec = (400/2)-40;
		yrec = 600-10;
		xrec2 = (400/2)-40;
		yrec2 = 0;
        socket = new Socket("127.0.0.1", 4000);
        sign = "cl2";
		arret = false;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{ 

		System.out.println("Manoratra");

        if(e.getKeyCode() == KeyEvent.VK_D){
			xrec2 = xrec2+15;
        }else if(e.getKeyCode() == KeyEvent.VK_A){
			xrec2 = xrec2-15;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
			sign = "close";
			arret = true;
		}else if(e.getKeyCode() == KeyEvent.VK_V){
			if(y == yrec2+10){
				backX = false;
				backY = false;
				xplus = 1;
				yplus = 1;
			}
        }

	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{}
	@Override
	public void keyTyped(KeyEvent e)
	{}
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		

		Wind wind = new Wind();
		wind.setNom("Client");

		Client2 c2 = new Client2();
		wind.addKeyListener(c2);
		wind.getPan().setXrec1(c2.getXrec());
		wind.getPan().setYrec1(c2.getYrec());
		wind.getPan().setXrec2(c2.getXrec2());
		wind.getPan().setYrec2(c2.getYrec2());
		int test = 0;
		String line;

			BufferedReader readerChannel = new BufferedReader(new InputStreamReader(c2.getSocket().getInputStream()));
		    BufferedWriter writerChannel = new BufferedWriter(new OutputStreamWriter(c2.getSocket().getOutputStream()));
			while(true){
			try {

				// System.out.println(c2.getXrec2()+"//"+c2.getYrec2()+"//"+c2.getXplus()+"//"+c2.getYplus());
                writerChannel.write(c2.getXrec2()+"//"+c2.getYrec2()+"//"+c2.getXplus()+"//"+c2.getYplus()+"//"+c2.getSign()+"\n");
            	writerChannel.flush();

				if(c2.getSign().equals("close")){
					c2.getSocket().close();
					System.out.println("fin de connection");
					break;
				}

                if((line = readerChannel.readLine()) != null){
                    String[] col = line.split("//");
					if(col[6].equals("close")){
						c2.getSocket().close();
						System.out.println("fin de connection depuis autre client");
						break;
					}
                        System.out.println(col[2]+"||"+col[3]);
                        wind.getPan().setXrec1(Integer.parseInt(col[0]));
                        wind.getPan().setYrec1(Integer.parseInt(col[1]));
						c2.setX(Integer.parseInt(col[2]));
						c2.setY(Integer.parseInt(col[3]));
						c2.setScore1(Integer.parseInt(col[4]));
						c2.setScore2(Integer.parseInt(col[5]));
				}


				wind.getPan().setXrec2(c2.getXrec2());
                wind.getPan().setYrec2(c2.getYrec2());
				wind.getPan().setXo1(c2.getX());
				wind.getPan().setYo1(c2.getY());
				wind.getPan().setScore1(String.valueOf(c2.getScore1()));
				wind.getPan().setScore2(String.valueOf(c2.getScore2()));

				if(c2.getX() < 0)
				c2.setBackX(true);

				if(c2.getX() > 400-20)
				c2.setBackX(false);

				if(c2.getY() < 0)
				c2.setBackY(true);
				
				if(c2.getY() > 600-20)
				c2.setBackY(false);

				if(!c2.getBackX()){
					c2.setX(c2.getX() - c2.getXplus());
					wind.getPan().setXo1(c2.getX());
				}else{
					c2.setX(c2.getX() + c2.getXplus());
					wind.getPan().setXo1(c2.getX());
				}

				if(!c2.getBackY()){
					c2.setY(c2.getY() - c2.getYplus());
					wind.getPan().setYo1(c2.getY());
				}else{
					c2.setY(c2.getY() + c2.getYplus());
					wind.getPan().setYo1(c2.getY());
				}

				if(c2.getXrec()>c2.getX()+10 && c2.getY()+20 == c2.getYrec() || c2.getX()+10>c2.getXrec()+80 && c2.getY()+20 == c2.getYrec()){
					c2.setX(c2.getXrec()+40); c2.setY(c2.getYrec()-20);
					c2.setXplus(0); c2.setYplus(0);
					c2.setBackX(true);
					c2.setBackY(true);
				}
				if(c2.getX()+10 < c2.getXrec2() && c2.getY() == c2.getYrec2()+10){
					c2.setX(c2.getXrec2()+40); c2.setY(c2.getYrec2()+10);
						c2.setBackX(false);
						c2.setBackY(false);
				}

				if(c2.getX() > c2.getXrec2()+80 && c2.getY() == c2.getYrec2()+10){
					c2.setX(c2.getXrec2()+40); c2.setY(c2.getYrec2()+10);
					c2.setBackX(false);
					c2.setBackY(false);
					c2.setXplus(0); c2.setYplus(0);
				}


                wind.getPan().repaint();

					try {
						Thread.sleep(5);
				 	} catch (InterruptedException e) {
						e.printStackTrace();
				  	}
               
        	} catch (IOException e1) {
            // TODO Auto-generated catch block
				System.out.println(e1.getMessage());
            	e1.printStackTrace();
        	}
		}
		    
	}
}
