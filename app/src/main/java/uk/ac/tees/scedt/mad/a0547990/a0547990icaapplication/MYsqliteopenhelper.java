package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.comment.Comment;
import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.javabean.User;

public class MYsqliteopenhelper extends SQLiteOpenHelper {
    private static final String DB_Account="MYsqlite.db";

    private static final String create_users="create table users(account varchar(32),password varchar(32))";


    public MYsqliteopenhelper(@Nullable Context context) {
        super(context, DB_Account, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_users);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long register(User u){
        SQLiteDatabase db= getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("account",u.getAccount());
        cv.put("password",u.getPassword());
        long users=db.insert("users",null,cv);
        return users;
    }
    public boolean login(String EditText_Account,String EditText_Password){
        SQLiteDatabase db1=getWritableDatabase();
        boolean result=false;
        Cursor users=db1.query("users",null,"account like?",new String[]{EditText_Account},null,null,null);
        if(users !=null){
            while (users.moveToNext()){
              String EditText_Password1=  users.getString(1);
              result=EditText_Password1.equals(EditText_Password);
              return result;
            }
        }
        return false;
    }

}
