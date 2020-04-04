package com.antnzr.alef.gateway;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.antnzr.alef.utils.Constants.IMAGES_LIST;

public interface ImageServer {

    @GET(IMAGES_LIST)
    Call<List<String>> getImages();
}
