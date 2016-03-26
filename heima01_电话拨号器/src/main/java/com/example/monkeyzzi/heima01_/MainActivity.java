package com.example.monkeyzzi.heima01_;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //给按钮设置点击侦听
        //1.拿到按钮对象
        Button bt = (Button)findViewById(R.id.bt);
        //2.设置侦听
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3.获取输入的号码
                et = (EditText)findViewById(R.id.et);
                String phone = et.getText().toString();
                //我们需要告诉系统，我们的动作:打电话
                //创建意图对象
                Intent intent = new Intent();
                //把动作封装至意图对象当中
                intent.setAction(Intent.ACTION_CALL);
                //设置打给谁
                intent.setData(Uri.parse("tel:"+phone));

                //把动作告诉系统
                startActivity(intent);

            }
        });


    }

}
