package com.teccart.mb1.sensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor sensor;
    private List<Sensor> ls;

    private TextView gX;
    private TextView gY;
    private TextView gZ;
    private TextView txtMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gX = (TextView)findViewById(R.id.Xacceleration);
        gY = (TextView)findViewById(R.id.Yacceleration);
        gZ = (TextView)findViewById(R.id.Zacceleration);
        txtMove = (TextView)findViewById(R.id.txtMove);

        this.manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.ls = this.manager.getSensorList(Sensor.TYPE_ALL);

       for(Sensor element: this.ls)
        {
            Log.i("id",element.toString());
        }

        this.sensor = this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.manager.registerListener(this,this.sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        gX.setText("X: " + String.valueOf(event.values[0]));
        gY.setText("Y: " + String.valueOf(event.values[1]));
        gZ.setText("Z: " + String.valueOf(event.values[2]));

        float vectorLength;
        vectorLength = (float)Math.sqrt(Math.pow((double)(event.values[0]),2)+Math.pow((double)(event.values[1]),2)
                + Math.pow((double)(event.values[2]),2));

        txtMove.setX(txtMove.getX() + event.values[0] * (-1*vectorLength));
        txtMove.setY(txtMove.getY() + event.values[1] * vectorLength);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}