package barray.test.espresso.act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import barray.test.espresso.R;

/**
 * 测试startActivityForResult 权限
 */
public class CallPhoneActivity extends AppCompatActivity {
    private EditText mCallerNumber;
    private static final int REQUEST_CODE_PICK = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        mCallerNumber = findViewById(R.id.edit_text_caller_number);
    }

    public void onCall(View view) {
        boolean hasCallPhonePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;

        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber());
        else
            Toast.makeText(this, "需要拨打电话的权限", Toast.LENGTH_SHORT).show();
    }

    private Intent createCallIntentFromNumber() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        String number = mCallerNumber.getText().toString();
        intentToCall.setData(Uri.parse("tel:" + number));
        return intentToCall;
    }

    public void onPickContact(View view) {
        Intent pickContactIntent = new Intent(this, ContactsActivity.class);
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                mCallerNumber.setText(data.getExtras() == null ? "" :
                        data.getExtras().getString(ContactsActivity.KEY_PHONE_NUMBER));
                Log.d("aaa",mCallerNumber.getText().toString().trim());
            }
        }
    }
}
