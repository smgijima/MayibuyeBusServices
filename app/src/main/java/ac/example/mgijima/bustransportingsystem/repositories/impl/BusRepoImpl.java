package ac.example.mgijima.bustransportingsystem.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ac.example.mgijima.bustransportingsystem.repositories.IBusRepositroy;
import ac.example.mgijima.bustransportingsystem.config.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.Bus;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 4/22/2016.
 */
public class BusRepoImpl extends SQLiteOpenHelper implements IBusRepositroy {

    public static final String TABLE_NAME="bus";
    private SQLiteDatabase db;


    private static final String COLUMN_BUS_NUMBER="bussID";
    private static final String COLUMN_NUMBER_PLATE="numberPlate";
    private static final String COLUMN_SEATS="numberOfSeats";

    private static String DATABASE_CREATE=" CREATE TABLE "
        + TABLE_NAME + "("
            + COLUMN_BUS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_NUMBER_PLATE + " TEXT NOT NULL, "
            + COLUMN_SEATS + " TEXT NOT NULL );";


    public BusRepoImpl(Context context) {

        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }

    public void open()throws SQLException{
        db=this.getWritableDatabase();
    }
    public void close()
    {
        this.close();
    }

    @Override
    public Bus findById(Long busNum) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(
                TABLE_NAME, new String[]{
                        COLUMN_BUS_NUMBER,
                        COLUMN_NUMBER_PLATE,
                        COLUMN_SEATS},

                       COLUMN_BUS_NUMBER + " =? ",
                        new String[]{String.valueOf(busNum)},
                null,
                null,
                null,
                null );

        if(cursor.moveToFirst())
        {
            Bus objBUs = new Bus.BusBuilder()
                    .busNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_BUS_NUMBER)))
                    .getnumberPlate(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_PLATE)))
                    .seats(cursor.getInt(cursor.getColumnIndex(COLUMN_SEATS)))
                    .build();
            return objBUs;
        }
        else {
            return null;
        }
    }

    @Override
    public Bus add(Bus entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER_PLATE,entity.getNumberPlate());
        values.put(COLUMN_SEATS, entity.getSeats());
            Long busNum=db.insertOrThrow(TABLE_NAME,null,values);
         Bus objAddedBus=new Bus.BusBuilder()
                 .copy(entity)
                 .busNumber(busNum)
                 .build();
        return objAddedBus;
    }

    @Override
    public Bus update(Bus entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER_PLATE,entity.getNumberPlate());
        values.put(COLUMN_SEATS, entity.getSeats());
        db.update(TABLE_NAME,values,COLUMN_BUS_NUMBER + " =? ", new String[]
                {String.valueOf(entity.getBusNumber())});
        return entity;
    }

    @Override
    public Bus remove(Bus entity) {
        db.delete(TABLE_NAME,COLUMN_BUS_NUMBER + " =? ",new String[]{String.valueOf(entity.getBusNumber())});
        return entity;
    }

    @Override
    public Set<Bus> findAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Set objBusSet= new HashSet();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do{
                final Bus objBuss=new Bus.BusBuilder()
                        .busNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_BUS_NUMBER)))
                        .getnumberPlate(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_PLATE)))
                        .seats(cursor.getInt(cursor.getColumnIndex(COLUMN_SEATS)))
                        .build();
                objBusSet.add(objBuss);
            }while (cursor.moveToNext());
        }
        return  objBusSet;
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
