package com.example.synd;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SOSActivity extends AppCompatActivity implements View.OnClickListener {

    public Button send;
    EditText phone_Number, message;
    String phone_Num, send_msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        send = (Button) findViewById(R.id.btnSend);
        phone_Number = findViewById(R.id.mblTxt);
        message = findViewById(R.id.msgTxt);

        /*send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {

                phone_Num = phone_Number.getText().toString();
                send_msg = message.getText().toString();

                try {
                    SmsManager sms = SmsManager.getDefault(); // using android SmsManager
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
//                    sms.sendTextMessage(phone_Num, null, send_msg, null, null);
                    sms.sendTextMessageWithoutPersisting(phone_Num, null, send_msg, null, null); // adding number and text
                } catch (Exception e) {
                    Toast.makeText(SOSActivity.this, "SMS not sent", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
        */
    }

    public void sms(View v)
    {
        Log.i("Sending SMS","");
        Intent I =new Intent(Intent.ACTION_VIEW);

        I.setData(Uri.parse("smsto:"));
        I.setType("vnd.android-dir/mms-sms");
        I.putExtra("address", phone_Num);
        I.putExtra("sms_body","Enter your Sms here..");

        try
        {
            startActivity(I);
            finish();
            Log.i("Sms Send","");
        }
        catch(Exception e)
        {
            Toast.makeText(SOSActivity.this,"Sms not send",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
