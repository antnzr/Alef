package com.antnzr.alef.images;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface ImagesRepository {
    MutableLiveData<List<String>> getImages();
}
