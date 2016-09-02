package ac.example.mgijima.bustransportingsystem.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ac.example.mgijima.bustransportingsystem.repositories.IPassengerRepository;
import ac.example.mgijima.bustransportingsystem.config.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.Passenger;
import ac.example.mgijima.bustransportingsystem.domain.PassengerAddress;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 4/17/2016.
 */
public class PassengerRepositoryImpl extends SQLiteOpenHelper implements IPassengerRepository {
public static final String TABLE_NAME="passengers";
    private SQLiteDatabase db;

    public static final String COLUMN_PASSNUMBER="id";
    public static final String COLUMN_IDNUM="idNumber";
    public static final String COLUMN_NAME="name";
    public static final String COLUMNLAST_NAME="lastName";
    public  static final String COLUMN_STREET="street";
    public static final String COLUMN_CITY="city";
    public static final String COLUMN_CODE="code";

private  static final String DATABASE_CREATE=" CREATE TABLE "
        + TABLE_NAME + "("
        + COLUMN_PASSNUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_IDNUM + " TEXT NOT NULL, "
        + COLUMN_NAME + " TEXT NOT NULL, "
        + COLUMNLAST_NAME + " TEXT NOT NULL, "
        + COLUMN_STREET + " TEXT NOT NULL, "
        + COLUMN_CITY + " TEXT NOT NULL, "
        + COLUMN_CODE + " TEXT NOT NULL );";

    public PassengerRepositoryImpl(Context context){
        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db= this.getWritableDatabase();
    }
    public void close(){
        this.close();
    }

    @Override
    public Passenger findById(Long passNum) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{
                        COLUMN_PASSNUMBER,
                        COLUMN_IDNUM,
                        COLUMN_NAME,
                        COLUMNLAST_NAME,
                        COLUMN_STREET,
                        COLUMN_CITY,
                        COLUMN_CODE},

                COLUMN_PASSNUMBER + " =? ",
                new String[]{String.valueOf(passNum)},
                null,
                null,
                null,
                null);


            if (cursor.moveToFirst()) {
                final PassengerAddress objAddress= new PassengerAddress.AddressBuilder()
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .build();
                final Passenger objPassenger = new Passenger.PassengerBuilder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_PASSNUMBER)))
                        .passNumber(cursor.getString(cursor.getColumnIndex(COLUMN_IDNUM)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMNLAST_NAME)))
                        .address(objAddress)
                        .build();

                return objPassenger;
            } else {

                return null;
            }


    }

    @Override
    public Passenger add(Passenger entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSNUMBER,entity.id());
        values.put(COLUMN_IDNUM,entity.getPassNumber());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMNLAST_NAME,entity.getLastName());
        values.put(COLUMN_STREET,entity.getObjAdress().getStreet());
        values.put(COLUMN_CITY,entity.getObjAdress().getCity());
        values.put(COLUMN_CODE,entity.getObjAdress().getCode());

        Long passNumber=db.insertOrThrow(TABLE_NAME, null, values);

        Passenger addedPassenger= new Passenger.PassengerBuilder()
                .copy(entity)
                .id(passNumber)
                .build();
         return addedPassenger ;
    }

    @Override
    public Passenger update(Passenger entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSNUMBER,entity.id());
        values.put(COLUMN_IDNUM,entity.getPassNumber());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMNLAST_NAME,entity.getLastName());
        values.put(COLUMN_STREET,entity.getObjAdress().getStreet());
        values.put(COLUMN_CITY,entity.getObjAdress().getCity());
        values.put(COLUMN_CODE,entity.getObjAdress().getCode());

        db.update(TABLE_NAME,values,COLUMN_PASSNUMBER + " =? ", new String[]{String.valueOf(entity.id())});

        return entity;
    }

    @Override
    public Passenger remove(Passenger entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(TABLE_NAME,
                COLUMN_PASSNUMBER + " =? ", new String[]{String.valueOf(entity.id())});
        return entity;
    }
public Cursor getAll(){

    try {
        open();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String sql="Select * from passengers";
    return (db.rawQuery(sql,null));

}
    @Override
    public Set<Passenger> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Passenger> employeesSet=new HashSet<>();

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor =db.query(TABLE_NAME,null,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
        do {
            final PassengerAddress objAddress= new PassengerAddress.AddressBuilder()
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .build();

            final Passenger objPassenger = new Passenger.PassengerBuilder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_PASSNUMBER)))
                    .passNumber(cursor.getString(cursor.getColumnIndex(COLUMN_IDNUM)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMNLAST_NAME)))
                    .address(objAddress)
                    .build();
            employeesSet.add(objPassenger);
        }while(cursor.moveToNext());

        }

        return employeesSet;
    }

    @Override
    public int removeAll() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowsDleted=db.delete(TABLE_NAME,null,null);
        return 0;
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
