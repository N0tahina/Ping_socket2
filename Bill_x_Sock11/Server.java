import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import Graph.Wind;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Server implements KeyListener
{
    @Override
	public void keyPressed(KeyEvent e)
	{} 
	@Override
	public void keyReleased(KeyEvent e)
	{}
	@Override
	public void keyTyped(KeyEvent e)
	{}
    public static void main(String[] args) throws Exception
    {
        try{

            ServerSocket listener = new ServerSocket(4000);
            // listener.setSoTimeout(30000);
            Socket socket = listener.accept();
            String line;
            int a =1;
            BufferedReader readerChannel = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writerChannel = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Socket socket2 = listener.accept();
            String line2;
            int a2 =1;
            BufferedReader readerChannel2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            BufferedWriter writerChannel2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));

            while(true){

                    line = readerChannel.readLine();
                    line2 = readerChannel2.readLine();

                    if(line != null){
                        String[] col = line.split("//");
                        try{
                            System.out.println(line);
                            writerChannel.write(line2+"\n");
                            writerChannel.flush();
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                        if(col[4].equals("close")){
                            writerChannel.write(line2+"\n");
                            writerChannel.flush();
                            socket.close();
                        }
                    }

                    if(line2 != null){
                        String[] col = line2.split("//");
                        try{
                            System.out.println(line2);
                            writerChannel2.write(line+"\n");
                            writerChannel2.flush();
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                        if(col[4].equals("close")){
                            writerChannel2.write(line+"\n");
                            writerChannel2.flush();
                            socket2.close();
                        }
                    }

                    //     try {
                    //         Thread.sleep(5);
                    //     } catch (InterruptedException e) {
                    //         e.printStackTrace();
                    //     }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
