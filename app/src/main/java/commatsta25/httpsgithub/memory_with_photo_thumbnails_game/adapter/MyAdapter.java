package commatsta25.httpsgithub.memory_with_photo_thumbnails_game.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

import commatsta25.httpsgithub.memory_with_photo_thumbnails_game.PlayActivity;
import commatsta25.httpsgithub.memory_with_photo_thumbnails_game.R;

public class MyAdapter extends BaseAdapter {

    static ArrayList<String> itemPaths;
     static PlayActivity playActivity;

    public MyAdapter( ArrayList<String> itemPaths, PlayActivity playActivity) {
        this.itemPaths = itemPaths;
        this.playActivity = playActivity;
    }

    @Override
    public int getCount() {
        return itemPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return itemPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static void hideImageWin(View[] convertView) {
        for(View convertViewOne:convertView){
            if (convertViewOne == null) {
                convertViewOne = View.inflate(playActivity, R.layout.items_list, null);
            }
            ImageView image = convertViewOne.findViewById(R.id.imageView);
            image.setColorFilter(convertViewOne.getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(playActivity, R.layout.items_list, null);
        }

        Log.d("ADebugTag", "test getView(): " + itemPaths.get(position));
        ImageView images = convertView.findViewById(R.id.imageView);
        Bitmap bmImg = BitmapFactory.decodeFile(itemPaths.get(position));
        images.setImageBitmap(bmImg);

        hideImage(convertView);

        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.d("ADebugTag", "item from itemPath2: " + itemPaths.get(position));
                String path = itemPaths.get(position);
                playActivity.clicked(path,v,position);

            }

        });

        return convertView;
    }


    public static void hideImageMiss(View convertView[]) {
        for(View convertViewOne:convertView){
            Log.d("ADebugTag", "showImage() :  RUN " + convertViewOne);
            hideImage(convertViewOne);
        }
    }

    public static void showImage(View convertView, int position) {
            ImageView image = convertView.findViewById(R.id.imageView);
            Bitmap bmImg = BitmapFactory.decodeFile(itemPaths.get(position));
            image.setImageBitmap(bmImg);
            Log.d("ADebugTag", "showImage() :  RUN " + position + " bmImg " + bmImg );
            image.setColorFilter(0);
        }

    public static void hideImage(View convertViewOne) {
            ImageView image = convertViewOne.findViewById(R.id.imageView);
            image.setColorFilter(convertViewOne.getResources().getColor(R.color.colorPrimary));
    }

}


