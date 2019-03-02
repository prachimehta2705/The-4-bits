package com.example.dinalaptop.ocrdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ImageView ivImage;
    TextView barcodeResult;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        barcodeResult = (TextView) findViewById(R.id.barcodeOutput);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
    }

    private void SelectImage(){

        final CharSequence[] items={"Camera","Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
            }

        }
    }

    /* public  void onActivityResult(int requestCode, int resultCode, Intent data){
         super.onActivityResult(requestCode, resultCode,data);
         if(resultCode== Activity.RESULT_OK){

             if(requestCode==REQUEST_CAMERA) private void SelectImage(){

         final CharSequence[] items={"Camera","Gallery","QRcode/Barcode", "Cancel"};

         AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
         builder.setTitle("Add Image");

         builder.setItems(items, new DialogInterface.OnClickListener() {

             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 if (items[i].equals("Camera")) {

                     Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     startActivityForResult(intent, REQUEST_CAMERA);

                 } else if (items[i].equals("Gallery")) {

                     Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                     intent.setType("image*//*");
                    startActivityForResult(intent, SELECT_FILE);

                } else if(items[i].equals("QRcode/Barcode")){
                    Intent intent = new Intent(getApplicationContext(),ScanBarcodeActivity.class);
                    startActivityForResult(intent,0);
                }
                else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }
            {

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);

            }
            else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
            }

//ivImage.getDrawable();
        }
        else if (resultCode == 0){
            if (resultCode == CommonStatusCodes.SUCCESS){
                if (data!= null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText(barcode.displayValue);
                }else {
                    Toast.makeText(getApplicationContext(),"No barcode found",Toast.LENGTH_SHORT);
                }
            }
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
