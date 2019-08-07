package activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.first.myapplication.R;

import bean.UserMessage;
import utilse.Utils;

/**
 * Created by 小黑 on 2019/8/5.
 */

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserMessage userMessage = (UserMessage) getIntent().getSerializableExtra("userMessage");
        Utils.insert(this,userMessage);
    }
}
