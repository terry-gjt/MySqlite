package com.terrygjt.mysqlite;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private User_Sqlite user_sqlite;
    private SqliteThread sqliteThread;
    private Handler handler_for_sqliteThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        user_sqlite=new User_Sqlite(this);
//        fun();
        initHandler();
        sqliteThread=new SqliteThread(this,handler_for_sqliteThread);
        sqliteThread.start();
        sqliteThread.setLooper(sqliteThread.getLooper());
        fun2();
    }
    @Override
    public void onDestroy(){
        user_sqlite.closeSQLiteDatabase();
        super.onDestroy();
    }
    private void fun(){
        user_sqlite.insertData("1301","gjt","1301","1301");
        Log.i("线程fun","insertData(1301,1301,1301,1301)");
        user_sqlite.insertData("1302","黄存栏","1302p","1303i");
        Log.i("线程fun","insertData(1302,1302u,1302p,1303i);");
        user_sqlite.insertData("1303","金思思","1302","1303");
        Log.i("线程fun","insertData(1303,1302,1302,1303)");
        user_sqlite.insertData("1304","李林","1302","1303");
        Log.i("线程fun","insertData(1304,1302,1302,1303)");
        user_sqlite.queryAllDataAndLog();
        user_sqlite.deleteData("1304");
        Log.i("线程fun","deleteData(1304,1302,1302,1303)");
        user_sqlite.queryAllDataAndLog();
        String[] result3=user_sqlite.queryData("1302",new String[]{"username","ipString"});
        Log.i("线程fun","result3:"+result3[0]);
    }
    private void fun2(){
        sqliteThread.insertData("1301","gjt","1301","1301");
        Log.i("线程fun","insertData(1301,1301,1301,1301)");
        sqliteThread.insertData("1302","黄存栏","1302p","1303i");
        Log.i("线程fun","insertData(1302,1302u,1302p,1303i);");
        sqliteThread.insertData("1303","金思思","1302","1303");
        Log.i("线程fun","insertData(1303,1302,1302,1303)");
        sqliteThread.insertData("1304","李林","1302","1303");
        Log.i("线程fun","insertData(1304,1302,1302,1303)");
        sqliteThread.queryAllDataAndLog();
        sqliteThread.deleteData("1304");
        Log.i("线程fun","deleteData(1304,1302,1302,1303)");
        sqliteThread.queryAllDataAndLog();
        sqliteThread.queryData("1302",new String[]{"username","ipString"});
//        Log.i("线程fun","result3:"+result3[0]);
    }
    private void initHandler(){
        handler_for_sqliteThread = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==4) {//处理queryData的返回结果
                    String[] ss=(String[])msg.obj;
                    Log.i("线程msg.what==4","queryData：");
                }
                else if (msg.what == 5 ) {
                }
            }
        };
    }
}
