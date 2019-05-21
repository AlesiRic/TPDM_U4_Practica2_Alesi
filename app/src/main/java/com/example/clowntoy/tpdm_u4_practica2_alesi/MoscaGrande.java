package com.example.clowntoy.tpdm_u4_practica2_alesi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MoscaGrande {

    int x,y;
    boolean flap;
    double angulo;
    int desX,desY;
    public MoscaGrande(){
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
        desX=(int)(Math.cos(Math.toRadians(angulo))*10);
        desY=(int)(Math.sin(Math.toRadians(angulo))*10);
        flap=true;
    }

    public void dibujarMosca(Canvas c, Paint p, int a, int b){
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        if(flap) {
            p.setColor(Color.CYAN);
            c.drawOval(x - 20, y - 20, x +60, y + 60, p);
            c.drawOval(x + 80, y - 20, x +160, y + 60, p);
            p.setColor(Color.BLACK);
            c.drawOval(x, y, x + 140, y + 140, p);
            flap=false;
        }else{
            p.setColor(Color.CYAN);
            c.drawOval(x - 40, y + 20, x + 40, y + 100, p);
            c.drawOval(x + 100, y + 20, x + 180, y + 100, p);
            p.setColor(Color.BLACK);
            c.drawOval(x, y, x + 140, y + 140, p);
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
        if(x>=this.x && x<=(this.x+140)){
            if(y>=this.y && y<=(this.y+140)){
                return true;
            }
        }
        return false;
    }

}
