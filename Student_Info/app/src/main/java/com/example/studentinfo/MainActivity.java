package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements addstudinterface {

    FloatingActionButton add;
    ArrayList<Student> list = new ArrayList<>();

    HashMap<String, ArrayList> map = new HashMap<>();

    list_student list_student1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_student1 = new list_student(list);

        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().add(R.id.minLayout,list_student1).commit();
        FragmentTransaction t = fm.beginTransaction();
        t.add(R.id.minLayout, list_student1);
        t.commit();

    }


    @Override
    public void add_stud_data(String prn, String name, String email, Uri uri) {

        Student s = new Student(prn, name, email, uri);
        list.add(s);

        list_student1 = new list_student(list);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.minLayout, list_student1).commit();

    }

    public class Student {
        String prn;
        String name;
        String email;
        Uri uri;

        public Student(String prn, String name, String email, Uri uri) {
            this.prn = prn;
            this.name = name;
            this.email = email;
            this.uri = uri;

        }

        public Student() {

        }
    }

}
