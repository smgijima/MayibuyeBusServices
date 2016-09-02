package ac.example.mgijima.bustransportingsystem.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ac.example.mgijima.bustransportingsystem.repositories.IAccountRepo;
import ac.example.mgijima.bustransportingsystem.config.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.Account;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 5/12/2016.
 */
public class AccountRepoImpl extends SQLiteOpenHelper implements IAccountRepo {
    public static final String TABLE_NAME="accounts";
    private SQLiteDatabase db;

    private static final String COLUMN_ID="id";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";


    private static String DATABASE_CREATE=" CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL, "

            + COLUMN_PASSWORD + " TEXT NOT NULL );";


    public AccountRepoImpl(Context context) {

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
    public Account findById(Long accNum) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(
                TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD},

                COLUMN_ID + " =? ",
                new String[]{String.valueOf(accNum)},
                null,
                null,
                null,
                null );

        if(cursor.moveToFirst())
        {
            Account account = new Account.AccountBuilder()
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))

                    .build();
            return account;
        }
        else {
            return null;
        }

    }

    @Override
    public Account add(Account entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        Long id=db.insertOrThrow(TABLE_NAME,null,values);
        Account addedAccount=new Account.AccountBuilder()
                .copy(entity)
                .id(id)
                .build();
        return addedAccount;
    }

    @Override
    public Account update(Account entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        db.update(TABLE_NAME,values,COLUMN_ID + " =? ", new String[]
                {String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Account remove(Account entity) {
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Account> findAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Set objAccountSet= new HashSet();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do{
                final Account account=new Account.AccountBuilder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();
                objAccountSet.add(account);
            }while (cursor.moveToNext());
        }
        return  objAccountSet;
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
