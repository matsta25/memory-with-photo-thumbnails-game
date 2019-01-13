package commatsta25.httpsgithub.memory_with_photo_thumbnails_game;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import commatsta25.httpsgithub.memory_with_photo_thumbnails_game.adapter.MyAdapter;

public class PlayActivity extends AppCompatActivity {

    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

    ArrayList itemPaths = new ArrayList<String>();

    ListView listview;
    MyAdapter myAdapter;


    String[] clickedTable = new String[2];
    int[] clickedTablePositions = new int[2];

    View[] clickedView = new View[2];

    int WIN_COUNT = 0;
    final int WIN_COUNT_SUCCESS = 3;

    final int SET_OUT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        dbRead();
    }

    private void dbRead() {
        CharSequence text = " dbRead()";
        toastMessage(text);
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

        while(cursor.moveToNext()) {
            String path = cursor.getString(
            cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_PATH));
            Log.d("ADebugTag", "PATH: " + path);
            itemPaths.add(path);
            itemPaths.add(path);
        }
        cursor.close();

        for(int i = 0 ; i < itemPaths.size(); i++) {
            Log.d("ADebugTag", "item from itemPath: " + itemPaths.get(i));
        }
        listview = findViewById(R.id.listview);
        Collections.shuffle(itemPaths);

        myAdapter = new MyAdapter(itemPaths,this);
        listview.setAdapter(myAdapter);
    }

    public void clicked(String param, View v, int position){
        MyAdapter.showImage(v, position);

        if(clickedTable[1] == null && clickedTable[0] != null){
            clickedTable[1] = param;
            clickedTablePositions[1] = position;
            clickedView[1] = v;
        }else if (clickedTable[0] == null && clickedTable[1] == null){
            clickedTable[0] = param;
            clickedTablePositions[0] = position;
            clickedView[0] = v;
        }

        if(clickedTable[0] != null && clickedTable[1] != null ){
            if(clickedTablePositions[0] == clickedTablePositions[1]){
                Toast.makeText(this, "THE SAME PICTURE", Toast.LENGTH_SHORT).show();
                clickedTable[1] = null;
            }else if(clickedTable[0] == clickedTable[1]){

                Toast.makeText(this, "WIN", Toast.LENGTH_SHORT).show();
                setTimeOut(true);
                WIN_COUNT++;

                if(WIN_COUNT == WIN_COUNT_SUCCESS){
                    Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }
                clickedTable[0] = null;
                clickedTable[1] = null;
            }else{
                Toast.makeText(this, "MISS", Toast.LENGTH_SHORT).show();
                setTimeOut(false);

                clickedTable[0] = null;
                clickedTable[1] = null;
            }
        }

        Log.d("ADebugTag", "clicked: clickedTable[0] " + clickedTable[0]);
        Log.d("ADebugTag", "clicked: clickedTable[1] " + clickedTable[1]);

    }

    public void setTimeOut( final boolean result){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(result){
                            MyAdapter.hideImageWin(clickedView);
                        }else{
                            MyAdapter.hideImageMiss(clickedView);
                        }
                    }
                },
                SET_OUT_TIME);
    }

    private void toastMessage(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
