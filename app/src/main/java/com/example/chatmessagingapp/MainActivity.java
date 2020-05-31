package com.example.chatmessagingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 =(EditText)findViewById(R.id.editText2);
    }

    public void btn_send(View view){

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){

            Mymessage();
        }

    }

    private void Mymessage() {

        String PhoneNumber = editText.getText().toString().trim();
        String Message = editText2.getText().toString().trim();


        if(!editText.getText().toString().equals("")|| !editText2.getText().toString().equals("")){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(PhoneNumber , null, Message, null ,null );

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

        else{
            Toast.makeText(this, "Please enter Number or Message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch ((requestCode)){

            case 0:

                if(grantResults.length >= 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    Mymessage();

                }

                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
