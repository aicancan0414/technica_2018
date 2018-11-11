package c.group24.techapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addProject extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproject);
        Button post_pj = findViewById(R.id.post_pj);
        Button post_and_join = findViewById(R.id.post_and_join);


        post_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pjName = findViewById(R.id.pj_name);
                TextView pjseats = findViewById(R.id.pj_seats);
                TextView pjDes = findViewById(R.id.pj_des);

                final String name = pjName.getText().toString();
                final String des = pjDes.getText().toString();
                final String seats = pjseats.getText().toString();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Projects");
                // add project to Projects list
                ref.child(name);
                Log.i("name",name);
                ref.child(name).child("Description").setValue(des);
                ref.child(name).child("Seats").setValue(seats);

            }
        });
        post_and_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pjName = findViewById(R.id.pj_name);
                TextView pjseats = findViewById(R.id.pj_seats);
                TextView pjDes = findViewById(R.id.pj_des);

                final String name = pjName.getText().toString();
                final String des = pjDes.getText().toString();
                final String seats = pjseats.getText().toString();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Projects");
                // add project to Projects list
                ref.child(name);
                ref.child(name).child("Description").setValue(des);
                ref.child(name).child("Seats").setValue(Integer.toString(Integer.parseInt(seats)-1));
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String uidStr = auth.getCurrentUser().getUid();
                String emailStr = auth.getCurrentUser().getEmail();
                String proj_name = name;
                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Taken projects and members").child(proj_name);
                ref2.child(uidStr).setValue(emailStr);
            }
        });

    }
}
