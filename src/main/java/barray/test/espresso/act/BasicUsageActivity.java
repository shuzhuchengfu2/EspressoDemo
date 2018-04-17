package barray.test.espresso.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import barray.test.espresso.R;

public class BasicUsageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_usage);
    }

    public void clickMe(View view) {
        TextView tvResult = findViewById(R.id.tv_status);
        tvResult.setText(getResources().getString(R.string.app_name));
    }

    public void showName(View view) {
        EditText etName = findViewById(R.id.et_name);
        String name = etName.getText().toString().trim();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }


}
