package commatsta25.httpsgithub.memory_with_photo_thumbnails_game;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button takePictureButton = findViewById(R.id.takePictureButton);
        final Button playButton = findViewById(R.id.playButton);


        takePictureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                for(int i = 0 ; i < 2 ; i++){
                    dispatchTakePictureIntent();

                final SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                String title = "titleTEST";
                String subtitle = "subtitleTEST";
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                Log.d("ADebugTag", "Value: " + Long.toString(newRowId));
//                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                CharSequence text = "PLAY BUTTON CLICKED.";
                toastMessage(text);
            }
        });


    }



    private void play() {

        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ImageView mImageView = findViewById(R.id.mImageView);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);

        }
    }

    private void toastMessage(CharSequence text) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
    }

}

