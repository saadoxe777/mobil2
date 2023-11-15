package com.example.labo1_mobile2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import java.util.List;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SolarSystemBD extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

    public SolarSystemBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querysolar = "CREATE TABLE IF NOT EXISTS solar(id_sol integer primary key Autoincrement,nom_astre text,taille integer,couleur integer" +
                ",status integer);";
        db.execSQL(querysolar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String querysol = "DROP TABLE IF EXISTS solar;";
        db.execSQL(querysol);

    }

    public void Open() {
        this.db = this.getReadableDatabase();
    }

    public void Close() {
        this.db.close();
    }

    public void SeedSolar() {
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Jupiter',50,-16711681,1);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Mars',80,-65536,1);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Terrestre',30,-16711681,0);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Alien',60,-12303292,0);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Factor',75,-16711681,1);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Adquater',55,-65536,0);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Frontenac',65,-16711681,1);");
        this.db.execSQL("INSERT INTO solar(nom_astre,taille,couleur,status) values('Joliette',70,-16711681,0);");

    }

    public List<AstreCeleste> getAllAstres() {
        List<AstreCeleste> AstreList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  solar", null);
        int nom_astre=cursor.getColumnIndex("nom_astre");
        int taille = cursor.getColumnIndex("taille");
        int couleur = cursor.getColumnIndex("couleur");
        int status=cursor.getColumnIndex("status") ;


        if((cursor != null) && cursor.moveToFirst())
        {
            do{
                AstreList.add(new AstreCeleste(cursor.getString(nom_astre),cursor.getInt(taille),cursor.getInt(couleur),cursor.getInt(status)));
            }while(cursor.moveToNext());
        }

        return AstreList;
    }
}
