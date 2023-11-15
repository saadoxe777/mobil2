package com.example.labo1_mobile2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

import java.util.Random;

public class AstreCeleste {

    private int posX;
    private int posY;
    private Paint crayon;
    private Random alea;
    private int  status;
    private int color;
    private int taille;
    private String NomAstre;


    public int getColor() {
        return color;
    }

    public AstreCeleste(String nomAstre, int taille, int color, int status){
        alea = new Random();
        posX = alea.nextInt(500);
        posY = alea.nextInt(500);
        this.NomAstre=nomAstre;
        this.status = status;
        this.taille=taille;
        this.color=color;
        crayon = new Paint();
        crayon.setAntiAlias(true);
        crayon.setColor(color);


    }
    public int getStatus()
    {
        return this.status;
    }

    public void setStatus(int state)
    {
        this.status = state;

    }

    public String getNomAstre() {
        return NomAstre;
    }

    public int getTaille() {
        return taille;
    }

    public int getPosX()
    {

        return this.posX;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPosY()
    {
        return
                this.posY;
    }

    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(posX, posY, taille, crayon);
    }
}

