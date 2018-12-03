package commatsta25.httpsgithub.memory_with_photo_thumbnails_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

    ArrayList itemPaths = new ArrayList<String>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        dbRead();

    }

    private void dbRead() {

        CharSequence text = " dbRead()";
        toastMessage(text);
//        final ImageView imageViewTest = findViewById(R.id.imageViewTest);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        lv = findViewById(R.id.lv);

        while(cursor.moveToNext()) {
            String path = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_PATH));
            Bitmap bmImg = BitmapFactory.decodeFile(path);
//            imageViewTest.setImageBitmap(bmImg);
            Log.d("ADebugTag", "PATH: " + path);
            itemPaths.add(path);
        }
        cursor.close();

        for(int i = 0 ; i < itemPaths.size(); i++) {
            Log.d("ADebugTag", "item from itemPath: " + itemPaths.get(i));
        }

        ArrayAdapter adapter = new ArrayAdapter(PlayActivity.this, android.R.layout.simple_list_item_1, itemPaths);

        lv.setAdapter(adapter);


    }

    private void toastMessage(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



}
