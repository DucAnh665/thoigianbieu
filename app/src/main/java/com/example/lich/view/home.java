package com.example.lich.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lich.databinding.HomeCalendarBinding;
import com.example.lich.shared.DataUserStorage;
import com.example.lich.viewmodel.LoginViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class home extends AppCompatActivity {


    HomeCalendarBinding binding;
    private LoginViewModel loginViewModel;
    private DataUserStorage dataUserStorage;

    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataUserStorage = new DataUserStorage(this);
        Anhxa();
        nhandulieu();

    }

    public void Anhxa() {

        binding.Thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, ThongtinActivity.class));
            }
        });
        binding.NutLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, MainActivity.class));
            }
        });

        binding.SuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, MainDatlich.class));
            }
        });

        binding.Thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUserStorage.clearData();
                finish();
            }
        });

        binding.btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startListening();
            }
        });

        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, ChangePassword.class));
            }
        });
    }

    private void startListening() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            Intent intent
                    = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                    Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                Toast
                        .makeText(home.this, " " + e.getMessage(),
                                Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startListening();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String message = Objects.requireNonNull(result).get(0);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                if (message.contains("lịch học") || message.contains("thời khóa biểu")) {
                    startActivity(new Intent(home.this, MainActivity.class));
                }
            }
        }
    }

    public void nhandulieu() {
        binding.txtTenSV.setText(dataUserStorage.loadData().getUserName());
        binding.txtMaSV.setText(dataUserStorage.loadData().getCodeStudent());
        binding.txtKhoa.setText(dataUserStorage.loadData().getNameFaculty());
        if (!dataUserStorage.loadData().getImage().equals("")) {
            Picasso.get().load(dataUserStorage.loadData().getImage()).into(binding.icon);
        } else {

        }
    }
}