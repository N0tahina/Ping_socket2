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

public class Moov implements KeyListener
{
	int xrec;
	int yrec;
	int xrec2;
	int yrec2;
    Socket socket;
	String sign;
	boolean arret;
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
	public void setXrec2(int t){
		xrec2 = t;
	}
	public void setYrec2(int t){
		yrec2 = t;
	}
	public Socket getSocket(){
		return socket;
	}
	public String getSign(){
		return sign;
	}
	public boolean getArret(){
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

	
	public Moov() throws UnknownHostException, IOException
	{
		score1 = 0; score2 = 0;
		this.xplus = 1; this.yplus = 1;
		x = (400/2)-10; y=(600/2)-10;
		xrec = (400/2)-40;
		yrec = 600-10;
		xrec2 = (400/2)-40;
		yrec2 = 0;
        socket = new Socket("127.0.0.1", 4000);
		sign = "mv";
		arret = false;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{ 

		System.out.println("Manoratra");

		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			xrec = xrec+15;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			xrec = xrec-15;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
			sign = "close";
			arret = true;
		}else if(e.getKeyCode() == KeyEvent.VK_L){
			if(y+20 == yrec){
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

		Moov k = new Moov();
		wind.addKeyListener(k);
		wind.getPan().setXrec1(k.getXrec());
		wind.getPan().setYrec1(k.getYrec());
		wind.getPan().setXrec2(k.getXrec2());
		wind.getPan().setYrec2(k.getYrec2());
		int test = 0;
		String line;

			BufferedReader readerChannel = new BufferedReader(new InputStreamReader(k.getSocket().getInputStream()));
		    BufferedWriter writerChannel = new BufferedWriter(new OutputStreamWriter(k.getSocket().getOutputStream()));
			while(true){
			try {

				System.out.println(k.getX()+"//"+k.getY());
                writerChannel.write(k.getXrec()+"//"+k.getYrec()+"//"+k.getX()+"//"+k.getY()+"//"+k.getScore1()+"//"+k.getScore2()+"//"+k.getSign()+"\n");
            	writerChannel.flush();

				if(k.getSign().equals("close")){
					k.getSocket().close();
					System.out.println("fin de connection");
					break;
				}

                if((line = readerChannel.readLine()) != null){
                    String[] col = line.split("//");
						if(col[4].equals("close")){
							k.getSocket().close();
							System.out.println("fin de connection depuis autre client");
							break;
						}
						System.out.println(col[0]+"||"+col[1]);
						k.setXrec2(Integer.parseInt(col[0]));
						k.setYrec2(Integer.parseInt(col[1]));
						if(col[2].equals("1")){
							k.setXplus(Integer.parseInt(col[2]));
							k.setYplus(Integer.parseInt(col[3]));
						}
				}

				wind.getPan().setXrec1(k.getXrec());
				wind.getPan().setYrec1(k.getYrec());
				wind.getPan().setXrec2(k.getXrec2());
				wind.getPan().setYrec2(k.getYrec2());
				wind.getPan().setScore1(String.valueOf(k.getScore1()));
				wind.getPan().setScore2(String.valueOf(k.getScore2()));

				if(k.getX() < 0)
				k.setBackX(true);

				if(k.getX() > 400-20)
				k.setBackX(false);

				if(k.getY() < 0)
				k.setBackY(true);
				
				if(k.getY() > 600-20)
				k.setBackY(false);

				if(!k.getBackX()){
					k.setX(k.getX() - k.getXplus());
					wind.getPan().setXo1(k.getX());
				}else{
					k.setX(k.getX() + k.getXplus());
					wind.getPan().setXo1(k.getX());
				}

				if(!k.getBackY()){
					k.setY(k.getY() - k.getYplus());
					wind.getPan().setYo1(k.getY());
				}else{
					k.setY(k.getY() + k.getYplus());
					wind.getPan().setYo1(k.getY());
				}

				if(k.getXrec()>k.getX()+10 && k.getY()+20 > k.getYrec() || k.getX()+10>k.getXrec()+80 && k.getY()+20 > k.getYrec()){
					k.setX(k.getXrec()+40); k.setY(k.getYrec()-20);
					k.setXplus(0); k.setYplus(0);
					k.setScore2(k.getScore2()+1);
				}
				if(k.getX()+10 < k.getXrec2() && k.getY() < k.getYrec2()+10){
					k.setX(k.getXrec2()+40); k.setY(k.getYrec2()+10);
					k.setBackX(false);
					k.setBackY(false);
					k.setXplus(0); k.setYplus(0);
					k.setScore1(k.getScore1()+1);
				}

				if(k.getX() > k.getXrec2()+80 && k.getY() < k.getYrec2()+10){
					k.setX(k.getXrec2()+40); k.setY(k.getYrec2()+10);
					k.setBackX(false);
					k.setBackY(false);
					k.setXplus(0); k.setYplus(0);
					k.setScore1(k.getScore1()+1);
				}

                wind.getPan().repaint();
               
        	} catch (IOException e1) {
            // TODO Auto-generated catch block
				System.out.println(e1.getMessage());
            	e1.printStackTrace();
        	}
		}
		    
		
	}
}
