package ac.example.mgijima.bustransportingsystem.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ac.example.mgijima.bustransportingsystem.repositories.IEmployeeRepository;
import ac.example.mgijima.bustransportingsystem.config.DatabaseConfig;
import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.domain.Employee;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 4/13/2016.
 */
public class EmployeeRepoImpl extends SQLiteOpenHelper implements IEmployeeRepository {

    public static final String TABLE_NAME="employees";
    private SQLiteDatabase db;

    public static final String COLUMN_NUMBER="employeeNumber";
    public static final String COLUMN_ID="emp_ID";
    public static final String COLUMN_NAME="empName";
    public static final String COLUMN_LAST_NAME="empLastName";
    public static final String COLUMN_STREET="street";
    public static final String COLUMN_CITY="city";
    public static final String COLUMN_CODE="code";

    private static final String DATABASE_CREATE=" CREATE TABLE "
    + TABLE_NAME + "("
            + COLUMN_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ID + " TEXT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_LAST_NAME + " TEXT NOT NULL,"
            + COLUMN_STREET + " TEXT NOT NULL, "
            + COLUMN_CITY +" TEXT NOT NULL, "
            + COLUMN_CODE +" TEXT NOT NULL );";

public  EmployeeRepoImpl(Context context){
    super(context, DatabaseConfig.DATABASE_NAME,null,DatabaseConfig.DATABASE_VERSION);
}
    public void open()throws SQLException{
        db= this.getWritableDatabase();
    }
    public void close(){
        this.close();
    }

    @Override
    public Employee findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.query(
                TABLE_NAME, new String[]{
                        COLUMN_NUMBER,
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_LAST_NAME,
                        COLUMN_STREET,
                        COLUMN_CITY,
                        COLUMN_CODE},

                COLUMN_NUMBER + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            final EmpAddressVO address= new EmpAddressVO.AddressBuilder()
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .build();
            final Employee objEmployee= new Employee.EmployeeBuilder()
                    .employeeNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_NUMBER)))
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .Name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)))
                    .address(address)
                    .build();
            return objEmployee;
        }
        else {
            return null;
        }
    }

    @Override
    public Employee add(Employee entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER,entity.getEmployeeNumber());
        values.put(COLUMN_ID,entity.getEmpId());
        values.put(COLUMN_NAME,entity.getEmpName());
        values.put(COLUMN_LAST_NAME,entity.getEmpLastName());
        values.put(COLUMN_STREET,entity.getObjAddress().getStreet());
        values.put(COLUMN_CITY, entity.getObjAddress().getCity());
        values.put(COLUMN_CODE,entity.getObjAddress().getCode());
        Long empNum=db.insertOrThrow(TABLE_NAME,null,values);
        Employee addedEmployee=new Employee.EmployeeBuilder()
                .copy(entity)
                .employeeNumber(empNum)
                .build();

        return addedEmployee;
    }

    @Override
    public Employee update(Employee entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values= new ContentValues();
        values.put(COLUMN_NUMBER,entity.getEmployeeNumber());
        values.put(COLUMN_ID,entity.getEmpId());
        values.put(COLUMN_NAME,entity.getEmpName());
        values.put(COLUMN_LAST_NAME,entity.getEmpLastName());
        values.put(COLUMN_STREET,entity.getObjAddress().getStreet());
        values.put(COLUMN_CITY,entity.getObjAddress().getCity());
        values.put(COLUMN_CODE, entity.getObjAddress().getCode());
        db.update(TABLE_NAME,values,COLUMN_NUMBER + " =? " , new String[]
                        {String.valueOf(entity.getEmployeeNumber())});


        return entity;
    }

    @Override
    public Employee remove(Employee entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(TABLE_NAME,
                COLUMN_NUMBER + " =? ",
                new String[]{String.valueOf(entity.getEmployeeNumber())});
        return entity;
    }

    @Override
    public Set<Employee> findAll() {
        SQLiteDatabase db =this.getReadableDatabase();
        Set<Employee>objEmployees= new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final EmpAddressVO address = new EmpAddressVO.AddressBuilder()
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .build();
                final Employee employee = new Employee.EmployeeBuilder()
                        .employeeNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_NUMBER)))
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .Name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)))
                        .address(address)
                        .build();
                objEmployees.add(employee);
            }while(cursor.moveToNext());
            }


        return objEmployees;
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
