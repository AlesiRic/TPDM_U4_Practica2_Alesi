package com.example.clowntoy.tpdm_u4_practica2_alesi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

    int estado,puntaje,tiempo;
    Mosca[] mosca;
    CountDownTimer timer1,timer2;
    MoscaGrande moscaGrande;

    public Lienzo(Context context){
        super(context);
        estado=0;
        mosca=new Mosca[7];
        tiempo=0;
        for(int i=0;i<7;i++){
            mosca[i]=new Mosca();
        }
        moscaGrande=new MoscaGrande();
        puntaje=0;
        timer1=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempo++;
            }

            @Override
            public void onFinish() {
                estado=3;
                invalidate();
            }
        };
        timer2=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempo++;
            }

            @Override
            public void onFinish() {
                estado=3;
                invalidate();
            }
        };

    }


    public void onDraw(Canvas c){
        Paint p=new Paint();
        switch(estado){
            case 0:
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.BLUE);
                p.setTextSize(100);
                c.drawText("Swat that fly!!",100,250,p);
                p.setTextSize(50);
                p.setColor(Color.BLACK);
                c.drawText("Tienes que matar 30 moscas tocandolas antes de 30 segundos" +
                        "\nSi lo logras tendras que derrotar al jefe tocandolo 5 veces" +
                        "\nSi lo logras, ganas el juego",100,450,p);
                p.setColor(Color.GRAY);
                c.drawText("Pulsa para iniciar",100,850,p);


                break;

            case 1:

                for(int i=0;i<7;i++){
                    mosca[i].dibujarMosca(c,p,getWidth(),getHeight());
                }
                p.setColor(Color.BLACK);
                p.setStyle(Paint.Style.FILL_AND_STROKE);
                p.setTextSize(50);
                c.drawText("Puntaje: "+puntaje+"   Tiempo: "+tiempo,100,100,p);
                invalidate();
            break;
            case 2:
                moscaGrande.dibujarMosca(c,p,getWidth(),getHeight());
                p.setColor(Color.BLACK);
                p.setStyle(Paint.Style.FILL_AND_STROKE);
                p.setTextSize(50);
                c.drawText("Puntaje: "+puntaje+"   Tiempo: "+tiempo,100,100,p);
                invalidate();
                break;
            case 3:
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.RED);
                p.setTextSize(100);
                c.drawText("Perdiste",100,480,p);
                p.setTextSize(50);
                p.setColor(Color.GRAY);
                c.drawText("Pulsa para jugar otra vez",100,850,p);
                break;
            case 4:
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.GREEN);
                p.setTextSize(100);
                c.drawText("VICTORIA!!",100,180,p);
                p.setTextSize(50);
                p.setColor(Color.GRAY);
                c.drawText("Pulsa para jugar otra vez",100,850,p);
                break;
            default:break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean tocar=false;
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            switch(estado){
                case 0:
                    estado=1;
                    tocar=true;
                    invalidate();
                    timer1.start();
                    break;
                case 1:
                    for(int i=0;i<7;i++){
                        if(mosca[i].isTouched((int) event.getX(),(int)event.getY())){
                            puntaje=puntaje+1;
                            mosca[i]=new Mosca();
                            tocar=true;
                        }
                        if(puntaje==30){
                            timer1.cancel();
                            puntaje=0;
                            estado=2;
                            tiempo=0;
                            timer2.start();
                        }
                        invalidate();
                    }

                    break;

                case 2:
                    if(moscaGrande.isTouched((int)event.getX(),(int)event.getY())){
                        puntaje=puntaje+1;
                        tocar=true;
                    }
                    if(puntaje==5){
                        estado=4;
                        invalidate();
                    }
                    break;
                case 3:
                    estado=1;
                    tiempo=0;
                    puntaje=0;
                    timer1.start();
                    invalidate();
                    break;
                case 4:
                    estado=1;
                    tiempo=0;
                    puntaje=0;
                    timer1.start();
                    invalidate();
                    break;
                default:break;


            }
        }
        return tocar;
    }
}
