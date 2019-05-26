package com.terrygjt.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by terry on 2019-05-20.
 */

public class SqliteThread extends HandlerThread {
    private Handler mHandler,dataHandler;
    private User_Sqlite user_sqlite;
    public SqliteThread(Context context,Handler dataHandler) {
        super("SqliteThread");
        user_sqlite=new User_Sqlite(context);
        this.dataHandler=dataHandler;
    }
    public void deleteData(String phoneNum) {
        Message msg=new Message();
        msg.what=1;
        msg.obj=phoneNum;
        mHandler.sendMessage(msg);
    }
    public void updateData(String phoneNum,String updateColumnName,String updateColumnValue) {
        String[] a=new String[3];
        a[1]=phoneNum;a[2]=updateColumnName;a[3]=updateColumnValue;
        Message msg=new Message();
        msg.what=2;
        msg.obj=a;
        mHandler.sendMessage(msg);
    }

    public void insertData(String phoneNum,String username,String password,String ipString) {
        String[] a=new String[4];
        a[1]=phoneNum;a[2]=username;a[3]=password;a[4]=ipString;
        Message msg=new Message();
        msg.what=3;
        msg.obj=a;
        mHandler.sendMessage(msg);
    }
    /*
    *queryColumnNames表示要查询的列
    * 可以为：phoneNum, username, password, ipString
    * */
    public void queryData(String phoneNum, @Nullable String[] queryColumnNames) {
        if(queryColumnNames==null){
            queryColumnNames=new String[]{"phoneNum","username","password","ipString"};
        }
        String[] ob=new String[queryColumnNames.length+1];
        ob[0]=phoneNum;
        for (int i=0;i<queryColumnNames.length;i++){
            ob[i+1]=queryColumnNames[i];
        }
        Message msg=new Message();
        msg.what=4;
        msg.obj=ob;
        mHandler.sendMessage(msg);
    }
    public void queryAllDataAndLog() {

    }
    public void closeSQLiteDatabase(){

    }
    private void dataBack(int mgsWhat,Object o){
        Message message=new Message();
        message.what=mgsWhat;
        message.obj=o;
        dataHandler.sendMessage(message);
    }
    public void setLooper(Looper looper) {
        mHandler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        user_sqlite.deleteData((String)msg.obj);
                        break;
                    case 2:
                        String[] a=(String[])msg.obj;
                        updateData(a[0],a[1],a[2]);
                        break;
                    case 3:
                        String[] a2=(String[])msg.obj;
                        insertData(a2[0],a2[1],a2[2],a2[3]);
                        break;
                    case 4:
                        String[] a3=(String[])msg.obj;
                        String phoneNum=a3[0];
                        String[] queryColumnNames=new String[a3.length-1];
                        for (int i=0;i<queryColumnNames.length;i++){
                            queryColumnNames[i]=queryColumnNames[i+1];
                        }
                        String[] result=user_sqlite.queryData(phoneNum,queryColumnNames);
                        dataBack(4,result);
                        break;
                }
            }
        };
    }
}
