/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch8threads;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author home
 */
public class MovingBalls extends JPanel implements Runnable{
   public int Score=0;
   
   public ArrayList<Ball>Balls=new ArrayList<Ball>(5);
   public Tank BlueTank=new Tank("C:\\Users\\home\\Desktop\\images\\TankBlueS.jpg");
   public Tank BlackTank=new Tank("C:\\Users\\home\\Desktop\\images\\TankBlueS1.jpg");
    public MovingBalls()
    {
        setSize(600,600);
        setBackground(Color.red);
        BlackTank.pos.x=220;
        BlackTank.pos.y=0;
        
        BlueTank.pos.x=220;
        BlueTank.pos.y=340;
        this.addKeyListener(new keylist());
        
    }
    private class keylist implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("Test");
            if (e.getKeyCode()==KeyEvent.VK_D)
            {
                BlackTank.mover();
                
                
            }
            if (e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                
                BlueTank.mover();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_A)
            {
                BlackTank.movel();
               
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                
                BlueTank.movel();
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_S)
            {
                BlackTank.fireBullet1();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                BlueTank.fireBullet();
                
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
    }
    public void  paintComponent(Graphics g)
    {
        g.clearRect(0, 0, 600, 600);
        
        try
        {
        BufferedImage imgtank1 = ImageIO.read(new File(BlackTank.ImagePath));
        BufferedImage imgrocket1 = ImageIO.read(new File(BlackTank.Rocket1.imgPath));
        g.drawImage(imgtank1, BlackTank.pos.x, BlackTank.pos.y,null);
        g.drawImage(imgrocket1, BlackTank.Rocket1.pos.x, -BlackTank.Rocket1.pos.y,null);
        BufferedImage imgtank = ImageIO.read(new File(BlueTank.ImagePath));
        BufferedImage imgrocket = ImageIO.read(new File(BlueTank.Rocket.imgPath));
        g.drawImage(imgtank, BlueTank.pos.x, BlueTank.pos.y,null);
        g.drawImage(imgrocket, BlueTank.Rocket.pos.x, BlueTank.Rocket.pos.y,null);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        
        
        for (Ball OneBall: Balls)
        
        {
            g.setColor(OneBall.CurrentColor);
            g.fillOval(OneBall.x,OneBall.y, 50, 50);
            if (BlueTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                System.out.println("Hit Occurs" + OneBall.CurrentColor.toString());
                Balls.remove(OneBall);
                break;
                
            }
            if (BlackTank.Rocket1.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                System.out.println("Hit Occurs" + OneBall.CurrentColor.toString());
                Balls.remove(OneBall);
                break;
                
            }
            if(BlueTank.Rocket.pos.distance(BlackTank.pos.x,BlackTank.pos.y)>=20){
                Score++;
                System.out.println("Score:"+Score);
                
            
        }
            if(BlackTank.Rocket1.pos.distance(BlueTank.pos.x,BlueTank.pos.y)>=20){
        Score++;
        
        System.out.println("Score2:"+Score);
    }
        }
    }
    @Override
    public void run() {
    try
    {
    while(true)
    {
        for (Ball OneBall: Balls)
        {
            OneBall.move(this.getWidth());
        }    
        
        //y+=10;
        Thread.sleep(50);
        repaint();
    }
    }
    catch (InterruptedException e)
    {}
    }
    
}

