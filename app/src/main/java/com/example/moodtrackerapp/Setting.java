package com.example.moodtrackerapp;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Setting extends AppCompatActivity {
    ActionBar actionBar;
    ImageView profileImage, profileImageBefore;
    final int PICK_IMAGE = 100;
    Uri imageUri;
    Dialog dialog;
    SwitchCompat notifySwitch, nightMode;
    TextView FullName, Username;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        actionBar = getSupportActionBar();
        actionBar.hide();

        //    Dialogs general lines
        dialog = new Dialog(Setting.this);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.WRAP_CONTENT);

        // notification
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.setAction("MY_NOTIFICATION_MESSAGE");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        notifySwitch = findViewById(R.id.notification_mode_btn);
        nightMode = findViewById(R.id.nightMode);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // Save switch state in shared preferences
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        notifySwitch.setChecked(sharedPreferences.getBoolean("value",true));

        notifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(notifySwitch.isChecked()) {
                    //ON Blue
                    // When switch checked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000*60*1 , broadcast);
                    Toast.makeText(Setting.this, "Notificatins ON", Toast.LENGTH_SHORT).show();
                }else {
                    //OFF orange
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    if(broadcast != null)
                        alarmManager.cancel(broadcast);
                }
            }
        });
//         Save night Mode switch state in shared preferences
        SharedPreferences nightModeShared = getSharedPreferences("save",MODE_PRIVATE);
        nightMode.setChecked(nightModeShared.getBoolean("value",false));
        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(nightMode.isChecked() || !nightMode.isChecked())
                    nightMode.setChecked(false);
                    Toast.makeText(Setting.this, "Night Moode is Coming Soon :)", Toast.LENGTH_SHORT).show();
            }
        });

        FullName = findViewById(R.id.name);
        Username = findViewById(R.id.user);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String usernameUser = intent.getStringExtra("Username");
        FullName.setText(nameUser);
        Username.setText("@" + usernameUser);
    }

    // setting dialog initialization
    public void about_Dialog(View view) {
        dialog.setContentView(R.layout.dialog_about);
        dialog.show(); // Showing the dialog here
    }

    public void rate_Dialog(View view) {
        dialog.setContentView(R.layout.dialog_rate);
        dialog.show(); // Showing the dialog here
    }

    public void terms_Dialog(View view) {
        dialog.setContentView(R.layout.dialog_terms);
        dialog.show(); // Showing the dialog here
    }

    public void editProfile_Dialog(View view) {
        dialog.setContentView(R.layout.dialog_edit_profile);
        dialog.show(); // Showing the dialog here
    }

    public void logout_dialog(View view) {
        dialog.setContentView(R.layout.dialog_logout);
        dialog.show(); // Showing the dialog here
    }

    // okay and cancel button function
    public void okay_dialog(View view) {
        dialog.dismiss();
    }

    public void cancel_dialog(View view) {
        dialog.dismiss();
    }

//    public void Choose_Picture(View view) {
//        profileImage = (ImageView) findViewById(R.id.profileImage);
//        profileImageBefore = dialog.findViewById(R.id.profileImageBefore);
//
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(i, 1);
//    }

    public void Choose_Picture(View view) {
        // Assign variables
        profileImage = (ImageView) findViewById(R.id.profileImage);
        profileImageBefore = dialog.findViewById(R.id.profileImageBefore);

        // pick image from galley
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            //show the preview of new image profile before save it
            profileImageBefore.setImageURI(imageUri);
        }
    }

    private void uploadPicture() {
        Intent intent = getIntent();
        StorageReference A = storageReference.child("image/" + intent.getStringExtra("Username"));

        // Create a reference to "mountains.jpg"
        A.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Snackbar.make(dialog.findViewById(R.id.profileImageBefore), "uploaded the Picture", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Setting.this, "Picture not upload", Toast.LENGTH_LONG).show();
            }
        });
    }

    // sava new profile image and name

    public void save_Profile_Changes(View view) {
        EditText newName = dialog.findViewById(R.id.newName);
        TextView FullName = findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();

        if (newName.getText().toString() != null) {
            FullName.setText(newName.getText().toString());
            Intent iGet = getIntent();
            Intent i = new Intent(getApplicationContext(), Setting.class);
            i.putExtra("Username", iGet.getStringExtra("Username"));
            i.putExtra("name", newName.getText().toString());
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

            database = FirebaseDatabase.getInstance();
            mDatabase = database.getReference("users");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query checkUserDatabase = reference.child("users");
            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = ds.getKey();
                        if (snapshot.child(key).child("userName").getValue(String.class).equals(iGet.getStringExtra("Username"))) {
                            mDatabase.child(key).child("fullName").setValue(newName.getText().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            Toast.makeText(Setting.this, "Please enter different name ", Toast.LENGTH_SHORT).show();
        }
//        profileImage.setImageDrawable(getResources());
        retrivePicture();
        dialog.dismiss();
    }

    public void deleteAccount(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) Log.d("TAG", "User account deleted.");
                    }
                });

        Intent iGet = getIntent();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query checkUserDatabase = reference.child("users");
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();
                    if (snapshot.child(key).child("userName").getValue(String.class).equals(iGet.getStringExtra("Username"))) {
                        mDatabase.child(key).setValue(null);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    public void getUserRating(View view) {
        //user review
        EditText review = (EditText) findViewById(R.id.feedback);

        //get the value of user rating
        RatingBar ratingbar = dialog.findViewById(R.id.rating);
        String ratingNum = String.valueOf(ratingbar.getRating()); //the value

        Toast.makeText(Setting.this, "Thank You", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }

    //confirm the logout and back to start activity
    public void logout(View view) {
        // from here to ?
        FirebaseAuth.getInstance().signOut();
        Intent logout = new Intent(this, MainActivity.class);
        startActivity(logout);
        //// maybe need more function for logout process//////
    }

    public void goTimeLine(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), timeLinePage.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void goSessions(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), sessions.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void retrivePicture() {
        Intent intent = getIntent();
        StorageReference A = storageReference.child("image/" + intent.getStringExtra("Username"));
        profileImage.setImageURI(imageUri);
    }
}