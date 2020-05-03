package com.example.studentinfo;


import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class addstudent extends Fragment {

    Uri uri;
    TextInputLayout name, email, prn;
    ImageView profile_url;
    Button add, pickimg;

    public addstudent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addstudent, container, false);
        name = (TextInputLayout) view.findViewById(R.id.name);
        prn = (TextInputLayout) view.findViewById(R.id.prn);
        email = (TextInputLayout) view.findViewById(R.id.email);
        pickimg = (Button) view.findViewById(R.id.pickimg);
        profile_url = (ImageView) view.findViewById(R.id.img);
        add = (Button) view.findViewById(R.id.add);

        pickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallary = new Intent();
                gallary.setType("image/*");
                gallary.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallary, "Select Picture"), 1);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getEditText().getText().toString();
                String prn1 = prn.getEditText().getText().toString();
                String email1=email.getEditText().getText().toString();

                addstudinterface addstudinterface1 = (addstudinterface) getActivity();
                addstudinterface1.add_stud_data(prn1, name1,email1,uri);


            }
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK)
        {
             uri=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                profile_url.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
