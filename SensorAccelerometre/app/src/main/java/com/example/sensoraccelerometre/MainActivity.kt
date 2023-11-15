package com.example.sensoraccelerometre

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),SensorEventListener {
    private lateinit var manager:SensorManager
    private lateinit var sensor:Sensor
    private lateinit var ls:List<Sensor>
    private lateinit var gx:TextView
    private lateinit var gy:TextView
    private lateinit var gz:TextView
    private lateinit var txtMove:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gx=findViewById<TextView>(R.id.Xacceleration)
        gy=findViewById<TextView>(R.id.Yacceleration)
        gz=findViewById<TextView>(R.id.Zacceleration)
        txtMove=findViewById<TextView>(R.id.txtMove)

        this.manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.ls=this.manager.getSensorList(Sensor.TYPE_ALL)
        for(element in this.ls){
            Log.i("id", element.toString())
        }
        this.sensor=this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        this.manager.registerListener(this,this.sensor,SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(p0: SensorEvent) {
        //gy.setText("X: "+p0.values[0].toString())
        gx.setText("X: "+p0.values[0].toString() )
        gy.setText("Y :"+p0.values[1].toString())
        gz.setText("Z :"+p0.values[2].toString())

        var vectorLength:Float
        vectorLength = Math.sqrt(
            Math.pow(p0.values.get(0).toDouble(), 2.0) + Math.pow(
                p0.values.get(1).toDouble(), 2.0
            )
                    + Math.pow(p0.values.get(2).toDouble(), 2.0)
        ).toFloat()

        txtMove.setX(txtMove.getX() + p0.values.get(0) * (-1 * vectorLength))
        txtMove.setY(txtMove.getY() + p0.values.get(1) * vectorLength)


    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}