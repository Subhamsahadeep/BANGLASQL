package com.example.subham.banglasql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mysqlite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="mydatabase.db";
    private static final String TABLE_NAME="mytable";
    private static final String COLUMN1="ID";
    private static final String COLUMN2="FIRSTNAME";
    private static final String COLUMN3="LASTNAME";
    private static final String COLUMN4="EMAIL";



    public mysqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query= "CREATE TABLE "+TABLE_NAME+"(" +COLUMN1+ " INTEGER PRIMARY KEY, "+COLUMN2+ " TEXT, "+COLUMN3+ " TEXT, "+COLUMN4+ " TEXT "+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean addtotable(String Id,String Fname, String Lname,String Email)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN1,Id);
        contentValues.put(COLUMN2,Fname);
        contentValues.put(COLUMN3,Lname);
        contentValues.put(COLUMN4,Email);
       long checker= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(checker == -1)
        {
            return false;
        }
        else
            return true;



    }
public Cursor display(){
    SQLiteDatabase sqLiteDatabase=getReadableDatabase();
    Cursor res;
    res=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    return res;

}

public boolean update(String Id,String Fname, String Lname,String Email)
{
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put(COLUMN1,Id);
    contentValues.put(COLUMN2,Fname);
    contentValues.put(COLUMN3,Lname);
    contentValues.put(COLUMN4,Email);
    sqLiteDatabase.update(TABLE_NAME,contentValues,"ID= ?",new String[]{Id});
    return true;
}

public int delete(String id){
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    return sqLiteDatabase.delete(TABLE_NAME,"ID= ?",new String[]{id});

}

}
