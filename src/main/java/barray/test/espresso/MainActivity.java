package barray.test.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import barray.test.espresso.act.BasicUsageActivity;
import barray.test.espresso.act.IntentActivity;
import barray.test.espresso.act.ListViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 基本用法
     *
     * @param view
     */
    public void basicUsage(View view) {
        Intent intent = new Intent(this, BasicUsageActivity.class);
        startActivity(intent);
    }

    /**
     * 适配器测试
     *
     * @param view
     */
    public void adapterTest(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    /**
     * startActivityForResult
     * @param view
     */
    public void forResult(View view){
        Intent intent = new Intent(this, IntentActivity.class);
        startActivity(intent);
    }





}
