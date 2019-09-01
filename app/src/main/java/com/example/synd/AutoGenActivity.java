package com.example.synd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AutoGenActivity extends AppCompatActivity {

    Button btnpdf;
    EditText createpdf;
    private static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_gen);

        btnpdf = findViewById(R.id.btnpdf);
        createpdf = findViewById(R.id.pdftext);

        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions ={ Manifest.permission.WRITE_EXTERNAL_STORAGE };
                        requestPermissions(permissions, STORAGE_CODE);
                    }
                    else {

                        savePDF();

                    }
                }
                else{
                    savePDF();

                }
            }
        });
    }

    private void savePDF(){

        Document mDoc = new Document();
        //pdf name
        String filename = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis());
        //file path
        String filepath = Environment.getExternalStorageDirectory() + "/mypdf/" + filename + ".pdf";

        try{

            PdfWriter.getInstance(mDoc, new FileOutputStream(filepath));
            //open doc
            mDoc.open();
            //get text
            String mtext = createpdf.getText().toString();
            mDoc.addAuthor("CODE_BLOODED");

            mDoc.add(new Paragraph("Issue DD for " + mtext + " ASAP "));
            //close doc
            mDoc.close();

            Toast.makeText(this, filename + ".pdf\n is saved to\n" + filepath, Toast.LENGTH_SHORT).show();
            sendEmail();

        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    savePDF();
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @SuppressLint("IntentReset")
    void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {"athonhack33@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        // emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Adding attachment pending! Email message goes here");


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Toast.makeText(this, "There is email client installed.", Toast.LENGTH_SHORT).show();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
