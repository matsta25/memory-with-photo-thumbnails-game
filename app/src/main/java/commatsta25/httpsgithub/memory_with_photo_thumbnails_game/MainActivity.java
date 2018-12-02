package commatsta25.httpsgithub.memory_with_photo_thumbnails_game;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button takePictureButton = findViewById(R.id.takePictureButton);
        final Button playButton = findViewById(R.id.playButton);

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "TAKE A PICTURE BUTTON CLICKED.";

                toastMessage(text);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "PLAY BUTTON CLICKED.";

                toastMessage(text);
            }
        });

    }

    private void toastMessage(CharSequence text) {

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
    }
}
