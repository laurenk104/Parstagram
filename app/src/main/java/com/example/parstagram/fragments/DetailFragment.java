package com.example.parstagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.R;
import com.parse.ParseFile;

public class DetailFragment extends Fragment {

    private TextView detailUsername;
    private ImageView detailImage;
    private TextView detailDescription;
    private TextView detailDate;
    private ImageView detailProfile;
    private Button btnBack;

    private Post post;

    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(Post post) {
        this.post = post;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailUsername = view.findViewById(R.id.detailUsername);
        detailImage = view.findViewById(R.id.detailImage);
        detailDescription = view.findViewById(R.id.detailDescription);
        detailDate = view.findViewById(R.id.detailDate);
        detailProfile = view.findViewById(R.id.detailProfile);
        btnBack = view.findViewById(R.id.btnBack);

        detailUsername.setText(post.getUser().getUsername());
        detailDescription.setText(post.getDescription());
        detailDate.setText(post.getDate());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(view.getContext()).load(image.getUrl()).into(detailImage);
        }
        ParseFile profile = post.getProfile();
        Log.d("profile", "" + profile);
        if (profile != null) {
            Glide.with(view.getContext()).load(profile.getUrl()).into(detailProfile);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, new PostsFragment())
                        .commit();
            }
        });
    }
}
