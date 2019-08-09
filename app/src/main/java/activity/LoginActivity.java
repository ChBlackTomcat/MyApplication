package activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.first.myapplication.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bean.UserMessage;
import okhttp3.Call;
import utilse.Utils;

import static android.R.attr.data;


/**
 * Created by 小黑 on 2019/8/2.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private Button bt_register;
    private ImageButton login_manager;
    private ImageButton login_user;
    private EditText ed_account;
    private EditText ed_password;
    private UserMessage userMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        bt_register = (Button) findViewById(R.id.register);
        login_manager = (ImageButton) findViewById(R.id.Login_manager);
        login_user = (ImageButton) findViewById(R.id.Login_user);
        ed_account = (EditText)findViewById(R.id.account);
        ed_password = (EditText) findViewById(R.id.password);
        bt_register.setOnClickListener(this);
        login_user.setOnClickListener(this);
        login_manager.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.Login_manager:
                break;
            case R.id.Login_user:

                String account = ed_account.getText().toString();
                String password = ed_password.getText().toString();
                if(verift(account,password)) {
                    String result = initHttp(account, password);
                }else{
                    messageOfDialog();
                }
                break;
        }
    }

    private void messageOfDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("提示");
        builder.setMessage("账号密码不能为空");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private String initHttp(String account,String password) {
        String url = getResources().getString(R.string.httpUrl);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("account", account)
                .addParams("password", password)
                .addParams("choose","login")
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                                if(!"".equals(response)) {
                                    Log.e("TAG", response);
                                    provessData(response);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("userMessage", userMessage);
                                    startActivity(intent);
                                    finish();
                                }
                        }

                });
        return null;
    }

    private void provessData(String json) {
        if(!TextUtils.isEmpty(json)){
             userMessage = new Gson().fromJson(json,UserMessage.class);
        }
    }

    public boolean verift(String account,String password){
        if("".equals(account)||"".equals(password)){
            return false;
        }
        else
            return true;
    }


}
