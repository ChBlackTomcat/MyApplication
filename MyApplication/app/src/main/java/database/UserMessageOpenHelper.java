package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.first.myapplication.R;

/**
 * Created by 小黑 on 2019/8/7.
 */

public class UserMessageOpenHelper extends SQLiteOpenHelper {
    private Context context;
    public UserMessageOpenHelper(Context context) {
        super(context, "userMessage", null, 1);
        this.context = context;
    }

    /**
     * 当数据库第一次创建的时候调用，适合做表结构的初始化
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(context.getResources().getString(R.string.sql));
    }
    /**
     * 这个方法适合做表结构的更新
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
