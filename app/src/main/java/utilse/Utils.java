package utilse;

import android.content.Context;
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
        db.execSQL("insert into user(_id,username,account,password,province) values(?,?,?,?,?)", new Object[]{userMessage.getId(),userMessage.getUsername(),userMessage.getAccount(),userMessage.getPassword(),userMessage.getProvince()});
        db.close();
    }
}
 