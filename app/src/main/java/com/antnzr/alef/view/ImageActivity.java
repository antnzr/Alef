package com.antnzr.alef.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.antnzr.alef.R;

import static com.antnzr.alef.utils.Constants.DISPLAY_FULL_IMAGE;
import static com.antnzr.alef.utils.ImageDownloader.setImage;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.image);

        if (getIntent().hasExtra(DISPLAY_FULL_IMAGE)) {
            setImage(imageView, getIntent().getStringExtra(DISPLAY_FULL_IMAGE));
        }
    }
}
