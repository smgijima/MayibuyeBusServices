package ac.cj.cornelious.busbooking.configerations.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import ac.cj.cornelious.busbooking.configerations.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.Maintainance;
import ac.example.mgijima.bustransportingsystem.repositories.IMaintainanceRepo;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 4/23/2016.
 */
public class MaintainanceRepoImpl extends SQLiteOpenHelper implements IMaintainanceRepo {
    public static final String TABLE_NAME="maintainance";
    private SQLiteDatabase db;

    private static final String COLUMN_ID="maintainanceID";
    private static final String COLUMN_CODE= "maintainanceCode";
    private static final String COLUMN_DESCRIPTION="description";
    private static final String COLUMN_COST="cost";

    private  static String DATABASE_CREATE=" CREATE TABLE "
            + TABLE_NAME +"("
            + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_CODE + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_COST + " TEXT NOT NULL );";
    public MaintainanceRepoImpl(Context context){
        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }

    public void open()throws SQLException{
        db=this.getWritableDatabase();
    }
    public void close(){
        this.close();
    }

    @Override
    public Maintainance findById(Long id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_COST},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
        null,
        null,
        null,
        null);

        if (cursor.moveToNext()){
            Maintainance objMaintainance= new Maintainance.BuildMaintainance()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .Description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .cost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)))
                    .build();
                    return objMaintainance;
        }
        else {

            return null;
        }
    }

    @Override
    public Maintainance add(Maintainance entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE,entity.getMaintainanceCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_COST, entity.getCost());
        Long id=db.insertOrThrow(TABLE_NAME,null,values);
        Maintainance addedMaintainance= new Maintainance.BuildMaintainance()
                .copy(entity)
                .id(id)
                .build();
        return addedMaintainance;
    }

    @Override
    public Maintainance update(Maintainance entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE,entity.getMaintainanceCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_COST, entity.getCost());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]
                {String.valueOf(entity.getMaintainanceID())});
        return entity;
    }

    @Override
    public Maintainance remove(Maintainance entity) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",
        new String[]{String.valueOf(entity.getMaintainanceID())});
        return entity;
    }

    @Override
    public Set<Maintainance> findAll() {
        Set maintainanceSet = new HashSet();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {

                Maintainance objMaintainance = new Maintainance.BuildMaintainance()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .Description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .cost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)))
                        .build();
                maintainanceSet.add(objMaintainance);
            } while (cursor.moveToNext());
            return maintainanceSet;
        }
        else {
        return null;

        }
    }

    @Override
    public int removeAll() {
       int rowsDeleted= db.delete(TABLE_NAME,null,null);
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
