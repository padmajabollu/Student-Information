package com.example.studentinfo;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class displayinfo extends Fragment {

    TextView prn,email,name;
    ImageView imageView;
    ArrayList<MainActivity.Student> list=new ArrayList<>();
    FloatingActionButton add;
    int pos;
    public displayinfo(ArrayList list,int position) {
        this.list=list;
        this.pos=position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_displayinfo, container, false);

        prn=(TextView) view.findViewById(R.id.prn1);
        email=(TextView) view.findViewById(R.id.email1);
        name=(TextView) view.findViewById(R.id.name);
        imageView=(ImageView) view.findViewById(R.id.profile);
        prn.setText(list.get(pos).prn);
        name.setText(list.get(pos).name);
        email.setText(list.get(pos).email);


        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),list.get(pos).uri);
            imageView.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        add=(FloatingActionButton) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addstudent addstudent1=new addstudent();
                getFragmentManager().beginTransaction().replace(R.id.minLayout,addstudent1).commit();

            }
        });


        return view;

    }


}
