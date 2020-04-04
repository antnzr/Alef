package com.antnzr.alef.images;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ImagesViewModelFactory implements ViewModelProvider.Factory {

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImagesViewModel(ImagesRepositoryRetrofit.getInstance());
    }
}
