package com.example.csc411project;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.csc411project.databinding.WelcomeBinding;
import com.example.csc411project.databinding.HomeScreenBinding;

import android.widget.VideoView;

import java.io.IOException;

public class HomeScreen extends Fragment {

    private HomeScreenBinding binding;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = HomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tutorialHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeScreen.this)
                        .navigate(R.id.action_HomeFragment_to_TutorialFragment);
            }
        });

        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeScreen.this)
                        .navigate(R.id.action_HomeFragment_to_SettingsFragment);
            }
        });

        binding.buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Process p=null;
                try {
                    p = new ProcessBuilder()
                            .command("test.sh")
                            .start();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(p!=null) p.destroy();
                }

                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                onActivityResult(REQUEST_VIDEO_CAPTURE, RESULT_OK, takeVideoIntent);
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
                if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
                    Uri videoUri = intent.getData();
                    VideoView videoView = null;
                    //videoView.setVideoURI(videoUri);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}