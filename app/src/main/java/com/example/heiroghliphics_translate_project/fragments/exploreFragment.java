package com.example.heiroghliphics_translate_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heiroghliphics_translate_project.MainActivity;
import com.example.heiroghliphics_translate_project.R;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class exploreFragment extends Fragment {
    public FlutterEngine flutterEngine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
        cashingFlutterEngine();

    }

    public void cashingFlutterEngine(){

        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(requireContext());

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);
        startActivity(
                FlutterActivity
                        .withCachedEngine("my_engine_id")
                        .build(requireContext())

        );

//        finish();


    }
}