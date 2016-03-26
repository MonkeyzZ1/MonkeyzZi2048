package com.example.monkeyzzi.heima04_;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View view){
        Toast.makeText(MainActivity.this, "这是中间", Toast.LENGTH_SHORT).show();
    }
    public void click2(View view){
        Toast.makeText(MainActivity.this, "这是↑", Toast.LENGTH_SHORT).show();
    }
    public void click3(View view){
        Toast.makeText(MainActivity.this, "这是↓", Toast.LENGTH_SHORT).show();
    }
    public void click4(View view){
        Toast.makeText(MainActivity.this, "这是←", Toast.LENGTH_SHORT).show();
    }
    public void click5(View view){
        Toast.makeText(MainActivity.this, "这是→", Toast.LENGTH_SHORT).show();
    }
    public void click6(View view){
        Toast.makeText(MainActivity.this, "这是↖", Toast.LENGTH_SHORT).show();
    }
    public void click7(View view){
        Toast.makeText(MainActivity.this, "这是↙", Toast.LENGTH_SHORT).show();
    }
    public void click8(View view){
        Toast.makeText(MainActivity.this, "这是↗", Toast.LENGTH_SHORT).show();
    }
    public void click9(View view){
        Toast.makeText(MainActivity.this, "这是↘", Toast.LENGTH_SHORT).show();
    }
}
