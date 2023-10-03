package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lich.Model.User;
import com.example.lich.databinding.ActivityUpdateUserBinding;
import com.example.lich.databinding.HomeCalendarBinding;
import com.example.lich.viewmodel.UpdateUserViewModel;
import com.squareup.picasso.Picasso;

public class UpdateUser extends AppCompatActivity {

    private ActivityUpdateUserBinding binding;
    private UpdateUserViewModel updateUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateUserViewModel = new ViewModelProvider(this).get(UpdateUserViewModel.class);
        updateUserViewModel.setDataUser(this);
        updateUserViewModel.getDataUser().observe(this, new Observer<User.Data>() {
            @Override
            public void onChanged(User.Data data) {
                binding.txtNameUser.setText(data.getUserName());
                binding.editAddress.setText(data.getAddress());
                binding.editBirthday.setText(data.getBirthday());
                if (!data.getImage().equals("")) {
                    Picasso.get().load(data.getImage()).into(binding.imgUser);
                } else {

                }
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = binding.editAddress.getText().toString();
                String birthday = binding.editBirthday.getText().toString();
                if (!address.equals("") && !birthday.equals("")) {
                    updateUserViewModel.updateUser(UpdateUser.this, address, birthday);
                    finish();
                } else {
                    Toast.makeText(UpdateUser.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();

                }
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}