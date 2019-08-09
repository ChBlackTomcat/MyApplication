package activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.first.myapplication.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bean.UserMessage;
import okhttp3.Call;

/**
 * Created by 小黑 on 2019/8/2.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText ed_account;
    private EditText ed_password;
    private EditText ed_rePassword;
    private EditText ed_username;
    private Button confiem;
    private RadioGroup rd_prvince;
    private String province;
    private UserMessage userMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_account = (EditText) findViewById(R.id.register_account);
        ed_password = (EditText) findViewById(R.id.register_ed_password);
        ed_rePassword = (EditText) findViewById(R.id.register_ed_rePassword);
        ed_username = (EditText) findViewById(R.id.register_ed_username);
        confiem = (Button) findViewById(R.id.register_bt_confirm);
        rd_prvince = (RadioGroup) findViewById(R.id.register_rd_province);
        confiem.setOnClickListener(this);
    }
    //给注册按钮添加监听
    @Override
    public void onClick(View view) {
        String account = ed_account.getText().toString();
        String password = ed_password.getText().toString();
        String rePassword = ed_rePassword.getText().toString();
        String username = ed_username.getText().toString();
        province = radiButtonOf(rd_prvince.getCheckedRadioButtonId());
        if (!"".equals(account) && !"".equals(password) && !"".equals(username)) {
            if (password.equals(rePassword) && password.length() >= 8) {
                userMessage = new UserMessage(0,account,password,province,username);//当注册要求满足时对数据进行封装
                  String json = new Gson().toJson(userMessage);//联网请求，将数据发送到服务器上
                  initHttp(json);
                    Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                    ed_account.setText("");
                    ed_password.setText("");
                    ed_rePassword.setText("");
                    ed_username.setText("");
            } else if(password.length()<8){
                messageOfDialog("密码格式错误");//根据不同情况显示对话框
            } else if(!password.equals(rePassword)){
                messageOfDialog("两次密码输入不一致");
            }
        }
    }

    private void initHttp(String json) {
        String url = getResources().getString(R.string.httpUrl);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("json", json)
                .addParams("choose","register")
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }

                });
    }

    //显示对话框的逻辑方法
    private void messageOfDialog(String messsage){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("提示");
        builder.setMessage(messsage);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
//获取radiogroud中的buttonid
    private String radiButtonOf(int id){
        String province = null;
        switch (id){
            case R.id.rd_guangdon:
                province="广东";
                break;
            case R.id.rd_guangXi:
                province="广西";
                break;
            case R.id.rd_heNan:
                province="河南";
                break;
            case R.id.rd_huBei:
                province="湖北";
                break;
        }
        return province;

    }
    public void back(View view){
        finish();
    }


}
