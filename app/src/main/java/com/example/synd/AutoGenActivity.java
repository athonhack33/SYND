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
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AutoGenActivity extends AppCompatActivity {

    ImageView btnpdf;
    EditText createpdf, amt;
    private static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_gen);

        btnpdf = findViewById(R.id.btnpdf);
        createpdf = findViewById(R.id.pdftext);
        amt = findViewById(R.id.editText);

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

    public void savePDF(){

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
            String mamt = amt.getText().toString();

            mDoc.addAuthor("CODE_BLOODED");
            mDoc.add(new Paragraph("Respected Sir, \n\t\t\t\t Subject: Application for Demand Draft."));

            mDoc.add(new Paragraph("\nKindly issue  a Demand draft of Rs." + mamt + " in favour of " + mtext + " college payable at towards payment of my semester fees."));
            mDoc.add(new Paragraph("\nThanking you, \n<Customer Name>."));



            //close doc
            mDoc.close();

            Toast.makeText(this, filename + ".pdf\n is saved to\n" + filepath, Toast.LENGTH_LONG).show();

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


    void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {"athonhack33@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("vnd.android.cursor.dir/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        // emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
               Toast.makeText(this, "There is email client installed.", Toast.LENGTH_SHORT).show();

        } catch (android.content.ActivityNotFoundException ex) {
               Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


}
