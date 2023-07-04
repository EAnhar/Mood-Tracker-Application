package com.example.moodtrackerapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moodtrackerapp.Adapter.moodAdapter;
import com.example.moodtrackerapp.model.moodModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class timeLinePage extends AppCompatActivity {
    ActionBar actionBar;
    moodAdapter adapter;
    List<moodModel> list;
    FirebaseFirestore fire;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_page);
        actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = findViewById(R.id.moodRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new moodAdapter(this);
        recyclerView.setAdapter(adapter);

        list = new ArrayList<>();
        adapter.setMoods(list);



        Intent iGet = getIntent();
        fire = FirebaseFirestore.getInstance();
        Log.d("TAG", iGet.getStringExtra("Username"));
        fire.collection("moods")
                .whereEqualTo("userName", iGet.getStringExtra("Username"))
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                moodModel model = new moodModel();
                                model.setFaceMood(document.getString("faceMood"));
                                model.setTxtMood(document.getString("txtMood"));
                                model.setNote(document.getString("note"));
                                model.setDay(document.getString("day"));
                                model.setMonth(document.getString("month"));
                                list.add(model);
                            }
                            adapter.setMoods(list);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

//    public void passUserData() {
//        Intent intent = getIntent();
//        String userUsername = intent.getStringExtra("userUsername");
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//                    //String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
//                    Intent intent = new Intent(timeLinePage.this, Setting.class);
//                    intent.putExtra("userUsername", userUsername);
//                    intent.putExtra("name", nameFromDB);
//                    ///  intent.putExtra("email", emailFromDB);
//                    intent.putExtra("username", usernameFromDB);
//                    // intent.putExtra("password", passwordFromDB);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

    public void addFunc(View v) {
        Intent iGet = getIntent();
        Log.d("TAG", iGet.getStringExtra("Username"));
        Intent i = new Intent(getApplicationContext(), insertMoodFace.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i);
    }

    public void goSetting(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), Setting.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i);
    }


    public void goSessions(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), sessions.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i);
    }
}