package utilse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bean.UserMessage;
import database.UserMessageOpenHelper;

/**
 * Created by 小黑 on 2019/8/7.
 */

public class Utils {
    public static void insert(Context context, UserMessage userMessage){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        db.execSQL("insert into user(username,account,password,province) values(?,?,?,?)", new Object[]{userMessage.getUsername(),userMessage.getAccount(),userMessage.getPassword(),userMessage.getProvince()});
        db.close();
    }
    public static Cursor serach(Context context,String account){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null,null);
        return cursor;
    }
    public static void delet(Context context,UserMessage userMessage){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        db.execSQL("delete from user where account = ? and password = ?",new Object[]{userMessage.getAccount(),userMessage.getPassword()});
        db.close();
    }
}
