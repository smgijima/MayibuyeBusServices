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

import ac.cj.cornelious.busbooking.configerations.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.ActivateTrip;
import ac.example.mgijima.bustransportingsystem.repositories.IActivateTripRepo;

/**
 * Created by Cornelious on 5/12/2016.
 */
public class ActivateTripRepo extends SQLiteOpenHelper implements IActivateTripRepo {
    public static final String TABLE_NAME="trip";
    private SQLiteDatabase db;

    private static final String COLUMN_TRIP_ID="tripId";
    private static final String COLUMN_TRIP_DEPARTURE="departure";
    private static final String COLUMN_TRIP_TIME="time";
    private static final String COLUMN_DESTINATION="destination";

    private static String DATABASE_CREATE=" CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_TRIP_DEPARTURE + " TEXT NOT NULL, "
            + COLUMN_DESTINATION + " TEXT NOT NULL, "
            + COLUMN_TRIP_TIME + " TEXT NOT NULL );";

    public ActivateTripRepo(Context context) {

        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db=this.getWritableDatabase();
    }

    @Override
    public ActivateTrip findById(Long id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(
                TABLE_NAME, new String[]{
                        COLUMN_TRIP_ID,
                        COLUMN_TRIP_DEPARTURE,
                        COLUMN_TRIP_TIME,
                        COLUMN_DESTINATION},
                COLUMN_TRIP_ID +" =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if(cursor.moveToFirst())
        {
            ActivateTrip objtrip = new ActivateTrip.ActivateTripBuilder()
                    .departure(cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_DEPARTURE)))
                    .time(cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_TIME)))
                    .destination(cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION)))
                    .build();
            return objtrip;
        }
        else {
            return null;
        }
    }

    @Override
    public ActivateTrip add(ActivateTrip entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRIP_DEPARTURE,entity.getDeparture());
        values.put(COLUMN_TRIP_TIME, entity.getTime());
        values.put(COLUMN_DESTINATION, entity.getDestination());
        Long tripNum=db.insertOrThrow(TABLE_NAME,null,values);
        ActivateTrip objAddedTrip=new ActivateTrip.ActivateTripBuilder()
                .copy(entity)
                .id(tripNum)
                .build();
        return objAddedTrip;
    }

    @Override
    public ActivateTrip update(ActivateTrip entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRIP_ID,entity.getId());
        values.put(COLUMN_TRIP_DEPARTURE, entity.getDeparture());
        values.put(COLUMN_TRIP_TIME, entity.getTime());
        values.put(COLUMN_DESTINATION, entity.getDestination());
        db.update(TABLE_NAME,values,COLUMN_TRIP_ID + " =? ", new String[]
                {String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ActivateTrip remove(ActivateTrip entity) {
        db.delete(TABLE_NAME,COLUMN_TRIP_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<ActivateTrip> findAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Set objTripSet= new HashSet();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do{
                final ActivateTrip objTrip=new ActivateTrip.ActivateTripBuilder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_TRIP_ID)))
                        .departure(cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_DEPARTURE)))
                        .time(cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_TIME)))
                        .destination(cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION)))
                        .build();
                objTripSet.add(objTrip);
            }while (cursor.moveToNext());
        }
        return  objTripSet;
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
