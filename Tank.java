/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch8threads;
import java.awt.Point;
/**
 *
 * @author home
 */
public class Tank {
    public Point pos=new Point();
    public String ImagePath;
    public Bullet Rocket1=new Bullet();
    public Bullet Rocket=new Bullet();
    public int speed;
    public Tank (String ImagePath)
    {
        speed=5;
        this.ImagePath=ImagePath;
        this.ImagePath=ImagePath;
        Rocket.imgPath="C:\\Users\\home\\Desktop\\images\\rocket.gif";
        Rocket1.imgPath="C:\\Users\\home\\Desktop\\images\\rocket.gif";
    }

    void mover() {
        this.pos.x+=speed;
    }
      void movel() {
        this.pos.x-=speed;
    }

    public void fireBullet() {
        
        Rocket.pos.x=this.pos.x+40;
        Rocket.pos.y=this.pos.y;
        Thread t1=new Thread (Rocket);    
        t1.start();
    }
    public void fireBullet1(){
        Rocket1.pos.x=this.pos.x+40;
        Rocket1.pos.y=this.pos.y;
        Thread t=new Thread(Rocket1);
        t.start();
    }
}


