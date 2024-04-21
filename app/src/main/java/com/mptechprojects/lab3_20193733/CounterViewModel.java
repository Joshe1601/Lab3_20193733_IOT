package com.mptechprojects.lab3_20193733;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private final MutableLiveData<Integer> counter = new MutableLiveData<>();

    public MutableLiveData<Integer> getCounter() {
        return counter;
    }
}
