package com.example.clowntoy.tpdm_u4_practica2_alesi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Mosca {
    int x,y;
    boolean flap;
    double angulo;
    int desX,desY;
    public Mosca(){
        x=y=500;
        int anguloP=(int)(Math.random()*16);
        switch(anguloP){
            case 0:angulo=0;break;
            case 1:angulo=22.5;break;
            case 2:angulo=45;break;
            case 3:angulo=67.5;break;
            case 4:angulo=90;break;
            case 5:angulo=112.5;break;
            case 6:angulo=135;break;
            case 7:angulo=157.5;break;
            case 8:angulo=180;break;
            case 9:angulo=202.5;break;
            case 10:angulo=225;break;
            case 11:angulo=247.5;break;
            case 12:angulo=270;break;
            case 13:angulo=292.5;break;
            case 14:angulo=315;break;
            case 15:angulo=337.5;break;
            default:angulo=360;break;
        }
        desX=(int)(Math.cos(Math.toRadians(angulo))*5);
        desY=(int)(Math.sin(Math.toRadians(angulo))*5);
        flap=true;
    }

    public void dibujarMosca(Canvas c, Paint p,int a,int b){
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        if(flap) {
            p.setColor(Color.CYAN);
            c.drawOval(x - 10, y - 10, x + 30, y + 30, p);
            c.drawOval(x + 40, y - 10, x + 80, y + 30, p);
            p.setColor(Color.BLACK);
            c.drawOval(x, y, x + 70, y + 70, p);
            flap=false;
        }else{
            p.setColor(Color.CYAN);
            c.drawOval(x - 20, y + 10, x + 20, y + 50, p);
            c.drawOval(x + 50, y + 10, x + 90, y + 50, p);
            p.setColor(Color.BLACK);
            c.drawOval(x, y, x + 70, y + 70, p);
            flap=true;
        }
        trasladar(a,b);
    }

    public void trasladar(int a,int b){
        if(x<=0 || x>=a){
            desX=desX*-1;
        }
        if(y<=0 || y>=a){
            desY=desY*-1;
        }
        x=x+desX;
        y=y+desY;
    }

    public boolean isTouched(int x,int y){
        if(x>=this.x && x<=(this.x+70)){
            if(y>=this.y && y<=(this.y+70)){
                return true;
            }
        }
        return false;
    }

}
