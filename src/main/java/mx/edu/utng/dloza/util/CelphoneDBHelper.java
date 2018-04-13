package mx.edu.utng.dloza.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import mx.edu.utng.dloza.model.Celphone;

/**
 * Created by Vianey on 11/04/2018.
 */

public class CelphoneDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "celphone.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "Celphone";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CELPHONE_MARK = "mark";
    public static final String COLUMN_CELPHONE_MODEL = "model";
    public static final String COLUMN_CELPHONE_COLOR = "color";
    public static final String COLUMN_CELPHONE_MEMORY = "memory";
    public static final String COLUMN_CELPHONE_COST = "cost";
    public static final String COLUMN_CELPHONE_COMPANY = "company";


    public CelphoneDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CELPHONE_MARK + " TEXT NOT NULL, " +
                COLUMN_CELPHONE_MODEL + " TEXT NOT NULL, " +
                COLUMN_CELPHONE_COLOR + " TEXT NOT NULL, " +
                COLUMN_CELPHONE_MEMORY + " TEXT NOT NULL, " +
                COLUMN_CELPHONE_COST + " NUMBER NOT NULL," +
                COLUMN_CELPHONE_COMPANY + " TEXT NOT NULL);"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void saveNewCelphone(Celphone celphone) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CELPHONE_MARK, celphone.getMark());
        values.put(COLUMN_CELPHONE_MODEL, celphone.getModel());
        values.put(COLUMN_CELPHONE_COLOR, celphone.getColor());
        values.put(COLUMN_CELPHONE_MEMORY, celphone.getMemory());
        values.put(COLUMN_CELPHONE_COST, celphone.getCost());
        values.put(COLUMN_CELPHONE_COMPANY, celphone.getCompany());


        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Celphone> celphoneList(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + filter;
        }

        List<Celphone> carLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Celphone celphone;

        if (cursor.moveToFirst()) {
            do {
                celphone = new Celphone();

                celphone.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                celphone.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MARK)));
                celphone.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MODEL)));
                celphone.setColor(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COLOR)));
                celphone.setMemory(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MEMORY)));
                celphone.setCost(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COST)));
                celphone.setCompany(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COMPANY)));
                carLinkedList.add(celphone);
            } while (cursor.moveToNext());
        }


        return carLinkedList;
    }

    /**
     * Query only 1 record
     **/
    public Celphone getCelphone(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Celphone receivedCelphone = new Celphone();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedCelphone.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MARK)));
            receivedCelphone.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MODEL)));
            receivedCelphone.setColor(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COLOR)));
            receivedCelphone.setMemory(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_MEMORY)));
            receivedCelphone.setCost(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COST)));
            receivedCelphone.setCompany(cursor.getString(cursor.getColumnIndex(COLUMN_CELPHONE_COMPANY)));
        }

        return receivedCelphone;

    }


    /**
     * delete record
     **/
    public void deleteCelphoneRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**
     * update record
     **/
    public void setUpdatedcelphoneRecord(long celphoneId, Context context, Celphone updatedcelphone) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME + " SET mark ='" + updatedcelphone.getMark() + "', model ='" + updatedcelphone.getModel()
                + "', color ='" + updatedcelphone.getColor() + "', memory ='" + updatedcelphone.getMemory() +
                "', cost ='" + updatedcelphone.getCost() + "', company ='" + updatedcelphone.getCompany() +
                "'  WHERE _id='" + celphoneId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }
}