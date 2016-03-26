package com.example.monkeyzzi.heima02_;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private EditText et_number;
    private EditText et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void send(View view){
        et_number = (EditText) findViewById(R.id.et_number);
        et_message = (EditText) findViewById(R.id.et_message);

        String phone = et_number.getText().toString();
        String msg = et_message.getText().toString();

        //获取短信管理器
        SmsManager sm = SmsManager.getDefault();
        //切割短信
        ArrayList<String> smss = sm.divideMessage(msg);
        
        //for循环把集合中的所有短信发送出去
        for (String string:smss
             ) {
            sm.sendTextMessage(phone,null,string,null,null);
        }

    }
}
