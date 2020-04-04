package com.antnzr.alef.images;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ImagesViewModel extends ViewModel {

    private MutableLiveData<List<String>> images;
    private ImagesRepository imagesRepository;

    public ImagesViewModel(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public void init() {
        images = imagesRepository.getImages();
    }

    public LiveData<List<String>> getImages() {
        return images;
    }
}
