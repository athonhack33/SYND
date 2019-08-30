package com.example.synd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AutoGenActivity extends AppCompatActivity {

    Button btnCreate;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_gen);

        btnCreate = findViewById(R.id.create);
        editText = findViewById(R.id.edittext);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createPdf(editText.getText().toString());
                savePDF();
            }
        });

    }

    private void savePDF() {

        Document mDoc = new Document();
       // String mfile = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis());
        String path = Environment.getExternalStorageDirectory() + "/myfile.pdf";

        try{
            PdfWriter.getInstance(mDoc, new FileOutputStream(path));
            mDoc.open();
            String mText = editText.getText().toString();
            mDoc.add(new Paragraph(mText));
            mDoc.close();
            Toast.makeText(this, "dkjahskjha", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(this, "noppppppeee", Toast.LENGTH_SHORT).show();

        }
    }



   /* private void createPdf(String someText){

        // create a new document
        PdfDocument document = new PdfDocument();
        // create a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(50, 50, 30, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(someText, 80, 50, paint);
        //canvas.drawt
        // finish the page
        document.finishPage(page);
// draw text on the graphics object of the page


        // Create Page 2
        pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 100, paint);
        document.finishPage(page);

        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"test-2.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
*/

}
