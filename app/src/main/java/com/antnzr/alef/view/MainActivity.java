package com.antnzr.alef.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.antnzr.alef.R;
import com.antnzr.alef.images.ImagesAdapter;
import com.antnzr.alef.images.ImagesViewModel;
import com.antnzr.alef.images.ImagesViewModelFactory;

import static com.antnzr.alef.utils.Constants.DISPLAY_FULL_IMAGE;

public class MainActivity extends AppCompatActivity implements ImagesAdapter.OnImageClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerImages;
    private SwipeRefreshLayout refresh;

    private ImagesViewModel imagesViewModel;

    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        recyclerImages = findViewById(R.id.images_list);

        refresh = findViewById(R.id.swipe);
        refresh.setOnRefreshListener(this);

        ImagesViewModelFactory factory = new ImagesViewModelFactory();
        imagesViewModel = new ViewModelProvider(this, factory)
                .get(ImagesViewModel.class);

        imagesViewModel.init();

        displayImages();
    }

    private void displayImages() {
        imagesViewModel.getImages().observe(this, strings -> {
            setupRecyclerView();
            recyclerImages.setAdapter(new ImagesAdapter(strings, this));
        });
    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(true);
        displayImages();
        refresh.setRefreshing(false);
    }

    @Override
    public void onImageClick(View view, String imageUrl) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(DISPLAY_FULL_IMAGE, imageUrl);
        startActivity(intent);
    }

    private void setupRecyclerView() {
        recyclerImages.setHasFixedSize(true);
        recyclerImages.setItemAnimator(new DefaultItemAnimator());
        recyclerImages.setNestedScrollingEnabled(true);

        gridLayoutManager = new GridLayoutManager(this,
                getResources().getInteger(R.integer.gallery_columns));
        recyclerImages.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        gridLayoutManager.setSpanCount(getResources().getInteger(R.integer.gallery_columns));
    }
}
