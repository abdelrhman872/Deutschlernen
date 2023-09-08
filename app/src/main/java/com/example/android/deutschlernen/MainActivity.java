/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.deutschlernen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesGV = findViewById(R.id.idGVcourses);

        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel("Family", R.drawable.famm));
        courseModelArrayList.add(new CourseModel("Animals", R.drawable.ani));
        courseModelArrayList.add(new CourseModel("Fruits", R.drawable.fruu));
        courseModelArrayList.add(new CourseModel("Veggie", R.drawable.vegg));
        courseModelArrayList.add(new CourseModel("Numbers", R.drawable.numm));
        courseModelArrayList.add(new CourseModel("Colors", R.drawable.colo));
        courseModelArrayList.add(new CourseModel("school suplies", R.drawable.schol));
        courseModelArrayList.add(new CourseModel("jobs", R.drawable.joob));
        courseModelArrayList.add(new CourseModel("Clothes", R.drawable.cloth));
        courseModelArrayList.add(new CourseModel("Share App", android.R.drawable.ic_menu_share));

        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
        //a3mal setOnItemClick ya m3lm
        coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //b3d mt3mal 7ot gwaha if (al position ) 3la4an t7dd al mkan
                if (position == 0) {
                    Intent a = new Intent(MainActivity.this,family.class);
                    startActivity(a);
                }
                if (position == 1) {
                    Intent b = new Intent(MainActivity.this, animals.class);
                    startActivity(b);
                }
                if (position == 2) {
                    Intent c = new Intent(MainActivity.this,fruits.class);
                    startActivity(c);
                }
                if (position == 3) {
                    Intent d = new Intent(MainActivity.this, Veggie.class);
                    startActivity(d);
                }
                if (position == 4) {
                    Intent e = new Intent(MainActivity.this,Numbers.class);
                    startActivity(e);
                }
                if (position == 5) {
                    Intent f = new Intent(MainActivity.this,colors.class);
                    startActivity(f);
                }
                if (position == 6) {
                    Intent g = new Intent(MainActivity.this,Schoolsup.class);
                    startActivity(g);
                }
                if (position == 7) {
                    Intent h = new Intent(MainActivity.this, Arbeit.class);
                    startActivity(h);
                }
                if (position == 8) {
                    Intent i = new Intent(MainActivity.this,kleidung.class);
                    startActivity(i);
                }
                if (position == 9) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                    String app_url = " https://play.google.com/store/apps/details?id=com.example.android.deutschlernen";
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                    startActivity(Intent.createChooser(shareIntent, "Share App"));
                }

            }
        });
    }

}
