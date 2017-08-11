package yoann.m2i.repertoire_telephone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrateur on 20/06/2017.
 */

public class DbInit extends SQLiteOpenHelper {

    public DbInit(Context ctxt, String dbName, int version) {
        super(ctxt, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contacts (" +
                " id INTEGER PRIMARY KEY NOT NULL" +
                ", name TEXT NOT NULL" +
                ", tel TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
