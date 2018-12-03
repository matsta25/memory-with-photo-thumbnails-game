package commatsta25.httpsgithub.memory_with_photo_thumbnails_game.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import commatsta25.httpsgithub.memory_with_photo_thumbnails_game.R;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> itemPaths;

    public MyAdapter(Context context, ArrayList<String> itemPaths) {
        this.context = context;
        this.itemPaths = itemPaths;
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

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.items_list, null);
        }

        ImageView images = convertView.findViewById(R.id.imageView);
        Bitmap bmImg = BitmapFactory.decodeFile(itemPaths.get(position));
        images.setImageBitmap(bmImg);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADebugTag", "item from itemPath2: " + itemPaths.get(position));

                String path = itemPaths.get(position);

                Toast.makeText(parent.getContext(), "view clicked: " + path, Toast.LENGTH_SHORT).show();


            }

        });


        return convertView;
    }
}

