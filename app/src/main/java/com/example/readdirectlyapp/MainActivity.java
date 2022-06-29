package com.example.readdirectlyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.readdirectlyapp.databinding.ActivityMainBinding;
import com.example.readdirectlyapp.fragment.LikeFragment;
import com.example.readdirectlyapp.fragment.SelectFragment;
import com.example.readdirectlyapp.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacementFragment(new SelectFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.menu_select:
                    replacementFragment(new SelectFragment());
                    break;
                case R.id.menu_like:
                    replacementFragment(new LikeFragment());
                    break;
                case R.id.menu_setting:
                    replacementFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}