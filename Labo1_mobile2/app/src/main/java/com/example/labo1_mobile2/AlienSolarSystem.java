package com.example.labo1_mobile2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSystem extends View {
    private Random alea;
    private Paint ballPaint;
    private Paint aspaint;
    private int ballX;
    private int bally;
    private float ballRadius;
    private List<AstreCeleste> Astrelist ;
    private SolarSystemBD mydb;
    private int cnt;
    private Context mcontext;
    private AstreCeleste Astre;
    public AlienSolarSystem(Context context) {
        super(context);
        this.mydb= new SolarSystemBD(context,"planete",null,1);
        this.mydb.Open();
        this.Astrelist=this.mydb.getAllAstres();
        if(this.Astrelist.size()<1) {

            this.mydb.SeedSolar();
            this.Astrelist = this.mydb.getAllAstres();
        }
        this.mydb.Close();
        mcontext = context;
        cnt =0;
        alea = new Random();
        ballX = alea.nextInt(500);
        bally = alea.nextInt(500);
        ballPaint = new Paint();
        ballPaint.setAntiAlias(true);
        ballPaint.setColor(Color.GRAY);

        for (AstreCeleste astre:Astrelist
             ) {

            Astre = new AstreCeleste(astre.getNomAstre(),astre.getTaille(),astre.getStatus(),astre.getColor());
        }

        ballRadius = 30;
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
         super.onDraw(canvas);
     canvas.drawCircle(ballX, bally, ballRadius, ballPaint);
     aspaint=new Paint();
        for (AstreCeleste astre:Astrelist) {
            aspaint.setColor(astre.getColor());
          //  float distance = calculateDistance(astre.getPosX(), astre.getPosY(), astre.getPosX(), );

          canvas.drawCircle(astre.getPosX(),astre.getPosY(),astre.getTaille(),aspaint);
        }

        // Calcul de la distance entre le cercle en cours et le cercle précédent
//        float distance = calculateDistance(astre.getPosX(), astre.getPosY(), prevPosX, prevPosY);
//
//        // Vérification de la distance pour éviter le chevauchement
//        if (distance >= (astre.getTaille() + prevAstreTaille)) {
//            canvas.drawCircle(astre.getPosX(), astre.getPosY(), astre.getTaille(), aspaint);
//            prevPosX = astre.getPosX();
//            prevPosY = astre.getPosY();
//            prevAstreTaille = astre.getTaille();
//        }





    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();

        boolean limitL,limitR,LimitU,LimitD = false;

        switch (action)
        {

            case MotionEvent.ACTION_MOVE:
                ballX = touchX;
                bally = touchY;

               for(AstreCeleste astre:Astrelist)
                {
                    limitL = ballX > (astre.getPosX()-30);
                    limitR =  ballX < (astre.getPosX()+30);
                    LimitU =  bally > (astre.getPosY()-30);
                    LimitD =  bally < (astre.getPosY()+30);

                    if(limitL && limitR && LimitD && LimitU )
                    {
                        if(astre.getStatus()==1)
                        {
                            astre.setColor(Color.GREEN);
                            Toast.makeText(mcontext,String.valueOf("Nom:"+astre.getNomAstre()+" Taille :"+astre.getTaille()+" Position en X: "+astre.getPosX()
                            +" Position en Y: "+astre.getPosY()),Toast.LENGTH_SHORT).show();
                            cnt++;
                        }

                    }
                }

               break;
       }
       invalidate();
        return true;
    }
}
