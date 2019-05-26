package com.terrygjt.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by terry on 2019-05-18.
 */

public class User_Sqlite extends SQLiteOpenHelper {
    public String tableName="Users";
    SQLiteDatabase mSQLiteDatabase;
    public User_Sqlite(Context context) {
        super(context, "data.db", null, 1);
        mSQLiteDatabase= this.getReadableDatabase();
    }
    public void deleteData(String phoneNum) {
        mSQLiteDatabase.delete(tableName,"phoneNum=?",new String[]{phoneNum});
    }
    public void updateData(String phoneNum,String updateColumnName,String updateColumnValue) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(updateColumnName,updateColumnValue);
        mSQLiteDatabase.update(tableName,contentValues,"phoneNum=?",new String[]{phoneNum});
    }
    
    public void insertData(String phoneNum,String username,String password,String ipString) {
        ContentValues values=new ContentValues();
        values.put("phoneNum",phoneNum);
        values.put("username",username);
        values.put("password",password);
        values.put("ipString",ipString);
        mSQLiteDatabase.insert(tableName,null,values);
    }
    /*
    *queryColumnNames表示要查询的列
    * 可以为：phoneNum, username, password, ipString
    * */
    public String[] queryData(String phoneNum, String[] queryColumnNames) {
        String[] result=new String[queryColumnNames.length];
        try {
//            各个参数的意思：
//            table:表名，不能为null
//            columns:要查询的列名，可以是多个，可以为null，表示查询所有列
//            selection:查询条件，可以为null,比如id=? and name=?
//            selectionArgs:对查询条件赋值，一个问号对应一个值，按顺序 可以为null
//            having:语法have，可以为null
//            orderBy：语法，按xx排序，可以为null
            Cursor cs = mSQLiteDatabase.query(tableName, queryColumnNames, "phoneNum=?", new String[]{phoneNum},
                    null, null, null);
            if(cs.moveToFirst()) {
                for (int i=0;i<queryColumnNames.length;i++){
                    result [i]=cs.getString(cs.getColumnIndex(queryColumnNames[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void queryAllDataAndLog() {
        String[] result=new String[4];
        try {
//            各个参数的意思：
//            table:表名，不能为null
//            columns:要查询的列名，可以是多个，可以为null，表示查询所有列
//            selection:查询条件，可以为null,比如id=? and name=?
//            selectionArgs:对查询条件赋值，一个问号对应一个值，按顺序 可以为null
//            having:语法have，可以为null
//            orderBy：语法，按xx排序，可以为null
            Cursor cs = mSQLiteDatabase.query(tableName, null, null, null,
                    null, null, null);
            if(cs.moveToFirst()) {
                do {
                    result [0]=cs.getString(cs.getColumnIndex("phoneNum"));
                    result [1]=cs.getString(cs.getColumnIndex("username"));
                    result [2]=cs.getString(cs.getColumnIndex("password"));
                    result [3]=cs.getString(cs.getColumnIndex("ipString"));
                    Log.i("线程queryAllDataAndLog",result [0]+"-"+result [1]+"-"+result [2]+"-"+result [3]);
                }
                while (cs.moveToNext()) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeSQLiteDatabase(){
        if (mSQLiteDatabase!=null){
            mSQLiteDatabase.close();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users (phoneNum text PRIMARY KEY,username text,password text,ipString text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}