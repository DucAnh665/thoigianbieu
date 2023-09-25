package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lich.R;
import com.example.lich.databinding.ActivityUpdateUserBinding;
import com.example.lich.databinding.HomeCalendarBinding;
import com.example.lich.shared.DataUserStorage;
import com.squareup.picasso.Picasso;

public class UpdateUser extends AppCompatActivity {

    private ActivityUpdateUserBinding binding;
    private DataUserStorage dataUserStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }


    public void initData() {
        dataUserStorage = new DataUserStorage(this);
        binding.txtNameUser.setText(dataUserStorage.loadData().getUserName());
        binding.editAddress.setText(dataUserStorage.loadData().getAddress());
        binding.editBirthday.setText(dataUserStorage.loadData().getBirthday());
        if (!dataUserStorage.loadData().getImage().equals("")) {
            Picasso.get().load(dataUserStorage.loadData().getImage()).into(binding.imgUser);
        } else {

        }
    }
}