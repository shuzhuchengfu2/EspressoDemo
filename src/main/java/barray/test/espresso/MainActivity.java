package barray.test.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import barray.test.espresso.act.BasicUsageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 基本用法
     * @param view
     */
    public void basicUsage(View view){
        Intent intent = new Intent(this, BasicUsageActivity.class);
        startActivity(intent);
    }
}
