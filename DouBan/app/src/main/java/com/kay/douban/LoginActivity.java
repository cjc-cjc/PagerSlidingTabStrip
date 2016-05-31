package com.kay.douban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Button lo;
    private EditText user;
    private EditText pass;

    private static final String action = "com.kay.douban.DOUBAN_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lo = (Button)findViewById(R.id.login);
        user=(EditText)findViewById(R.id.users);
        pass=(EditText)findViewById(R.id.password);

        user.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setHint("");
            }
        });

        pass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setHint("");
            }
        });

        lo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals("cjc")
                        && pass.getText().toString().equals("123")) {
                    //创建Intent对象
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT)
                    .show();
                    Intent intent = new Intent();
                  intent.setAction(LoginActivity.action);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"用户名或者密码错误！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
