package com.lksnext.parkingNBeltran.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lksnext.parkingNBeltran.R;
import com.lksnext.parkingNBeltran.databinding.ActivityMainBinding;
import com.lksnext.parkingNBeltran.databinding.FragmentMyVehiclesBinding;
import com.lksnext.parkingNBeltran.viewmodel.MyVehiclesViewModel;

public class MyVehiclesFragment extends Fragment {

    private MyVehiclesViewModel mViewModel;

    FragmentMyVehiclesBinding binding;

    public static MyVehiclesFragment newInstance() {
        return new MyVehiclesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Asignamos la vista/interfaz main (layout)
        binding = FragmentMyVehiclesBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyVehiclesViewModel.class);

        // TODO: Use the ViewModel
    }

}