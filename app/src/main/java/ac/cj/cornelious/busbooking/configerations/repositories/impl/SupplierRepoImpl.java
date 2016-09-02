package ac.cj.cornelious.busbooking.configerations.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import ac.example.mgijima.bustransportingsystem.config.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.Supplier;
import ac.example.mgijima.bustransportingsystem.repositories.ISupplierRepo;


public class SupplierRepoImpl extends SQLiteOpenHelper implements ISupplierRepo {
    public static final String TABLE_NAME="supplier";
    private SQLiteDatabase db;

    private static final String COLUMN_ID="ID";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_CONTACT="contact";

    private static String DATABASE_CREATE=" CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_CONTACT + " TEXT NOT NULL );";


    public SupplierRepoImpl(Context context) {

        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }
    public void open()throws SQLException {
        db=this.getWritableDatabase();
    }
    public void close()
    {
        this.close();
    }

    @Override
    public Supplier findById(Long id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(
                TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_CONTACT},

                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null );

        if(cursor.moveToFirst())
        {
            Supplier supplier = new Supplier.SupplierBuilder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .contactNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                    .build();
            return supplier;
        }
        else {
            return null;
        }
    }

    @Override
    public Supplier add(Supplier entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_CONTACT, entity.getContactNumber());
        Long supplierNum=db.insertOrThrow(TABLE_NAME,null,values);
        Supplier objAddedSupplier=new Supplier.SupplierBuilder()
                .copy(entity)
                .id(supplierNum)
                .build();
        return objAddedSupplier;
    }

    @Override
    public Supplier update(Supplier entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_CONTACT, entity.getContactNumber());
        db.update(TABLE_NAME,values,COLUMN_ID + " =? ", new String[]
                {String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Supplier remove(Supplier entity) {
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Supplier> findAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Set objBusSup= new HashSet();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do{
                final Supplier objSupplier=new Supplier.SupplierBuilder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .contactNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                        .build();
                objBusSup.add(objSupplier);
            }while (cursor.moveToNext());
        }
        return  objBusSup;
    }

    @Override
    public int removeAll() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowsDeleted=db.delete(TABLE_NAME,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
