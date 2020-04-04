package com.antnzr.alef.images;

import androidx.lifecycle.MutableLiveData;

import com.antnzr.alef.gateway.ImageServer;
import com.antnzr.alef.gateway.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesRepositoryRetrofit implements ImagesRepository {

    private static ImagesRepositoryRetrofit imagesRepositoryRetrofit;

    private ImageServer imageServer;

    public ImagesRepositoryRetrofit() {
        imageServer = ServiceGenerator.createService(ImageServer.class);
    }

    public static ImagesRepositoryRetrofit getInstance() {
        if (imagesRepositoryRetrofit == null) {
            imagesRepositoryRetrofit = new ImagesRepositoryRetrofit();
        }

        return imagesRepositoryRetrofit;
    }

    @Override
    public MutableLiveData<List<String>> getImages() {
        MutableLiveData<List<String>> result = new MutableLiveData<>();

        imageServer.getImages().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }
}
