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
import ac.example.mgijima.bustransportingsystem.domain.Ticket;
import ac.example.mgijima.bustransportingsystem.repositories.ITicketRepo;

/**
 * Created by Cornelious on 4/23/2016.
 */
public class TicketRepoImpl extends SQLiteOpenHelper implements ITicketRepo {
    public static final String TABLE_NAME="Tickets";
    private SQLiteDatabase db;

    private static final String  COLUMN_ID="ticketNum";
    private  static final String COLUMN_TYPE="ticketType";
    private static final String COLUMN_ROUTE="route";
    private static final String COLUMN_COST="cost";
    public TicketRepoImpl(Context context){
        super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
    }
    private static String DATABASE_CREATE=" CREATE TABLE "
    + TABLE_NAME +"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE + " TEXT NOT NULL, "
            + COLUMN_ROUTE + " TEXT NOT NULL, "
            + COLUMN_COST + " DOUBLE NOT NULL) ";

    public void open()throws SQLException{
        db=this.getWritableDatabase();

    }
    public void close(){
        this.close();
    }
    @Override
    public Ticket findById(Long id) {
        db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TYPE,
                        COLUMN_ROUTE,
                        COLUMN_COST},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            Ticket objTicket = new Ticket.TicketBuilder()
                    .ticketNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .ticketType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .route(cursor.getString(cursor.getColumnIndex(COLUMN_ROUTE)))
                    .cost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)))
                    .build();
            return objTicket;
        }
        else {

            return null;
        }
    }

    @Override
    public Ticket add(Ticket entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,entity.getTicketType());
        values.put(COLUMN_ROUTE,entity.getRoute());
        values.put(COLUMN_COST, entity.getCost());
        Long id=db.insertOrThrow(TABLE_NAME, null, values);
        Ticket addedTicket= new Ticket.TicketBuilder()
                .copy(entity)
                .ticketNumber(id)
                .build();
        return addedTicket;
    }

    @Override
    public Ticket update(Ticket entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,entity.getTicketType());
        values.put(COLUMN_ROUTE,entity.getRoute());
        values.put(COLUMN_COST,entity.getCost());
         db.update(TABLE_NAME,values,COLUMN_ID + " =? ",
                 new String[]{String.valueOf(entity.getTicketNum())});
        return entity;
    }

    @Override
    public Ticket remove(Ticket entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getTicketNum())});
        return entity;
    }

    @Override
    public Set<Ticket> findAll() {
        Set ticketSet = new HashSet();

        db=this.getReadableDatabase();
        Cursor cursor=db.query( TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Ticket objTicket = new Ticket.TicketBuilder()
                        .ticketNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .ticketType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .route(cursor.getString(cursor.getColumnIndex(COLUMN_ROUTE)))
                        .cost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)))
                        .build();
                ticketSet.add(objTicket);
            }while(cursor.moveToNext());
            return ticketSet;
        }
        else {

            return null;
        }
    }

    @Override
    public int removeAll() {
       int rowsdeleted= db.delete(TABLE_NAME,null,null);
        return rowsdeleted;
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
