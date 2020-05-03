package com.example.studentinfo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.makeText;


/**
 * A simple {@link Fragment} subclass.
 */
public class list_student extends Fragment {


    String[] names;

    FloatingActionButton add;
    ArrayList<MainActivity.Student> list;
    ListView listView;

    public list_student(ArrayList list) {
        // Required empty public constructor
        this.list = list;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_list_student, container, false);

        if(list.size()>0)
        {
            listView=(ListView) view.findViewById(R.id.list_add_stud);
            CustomAdapter arrayAdapter=new CustomAdapter();
            listView.setAdapter(arrayAdapter);

        }
        listView=(ListView) view.findViewById(R.id.list_add_stud);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayinfo displayinfo1=new displayinfo(list,position);
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.minLayout,displayinfo1).commit();
            }
        });
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



    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=getLayoutInflater().inflate(R.layout.listitemdesign,null);


                TextView t1=(TextView) v.findViewById(R.id.textview);

                ImageView imageView=(ImageView) v.findViewById(R.id.imgview);

                t1.setText(list.get(position).name);

                ImageView imageView1=(ImageView) v.findViewById(R.id.imgview);


            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),list.get(position).uri);
                imageView1.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return v;
        }
    }

}
