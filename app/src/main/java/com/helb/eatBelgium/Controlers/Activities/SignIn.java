package com.helb.eatBelgium.Controlers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helb.eatBelgium.Common.Common;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.User;

import java.util.Calendar;

//import com.google.firebase.iid.FirebaseInstanceIdService;
public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword=(EditText)findViewById(R.id.edtPassword);
        edtPhone=(EditText)findViewById(R.id.edtPhone);
        btnSignIn=(Button)findViewById((R.id.btSignIn));

        // dataBase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Patientez svp...");
                mDialog.show();
*/
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                         //   mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if(user!=null){

                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(SignIn.this, "Connexion reussite ! Bienvenue " , Toast.LENGTH_SHORT).show();
                                Intent toHome = new Intent(SignIn.this, HomeActivity.class);
                                Common.currentUser = user;
                                startActivity(toHome);
                            } else {
                                Toast.makeText(SignIn.this, "Connexion echouée ! :( ", Toast.LENGTH_SHORT).show();
                            }
                            }
                        }
                        else{
                            Toast.makeText(SignIn.this, "Utilisateur inexistant ! :( ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                                            //////////////// notification /////////////////////////////

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY,0);
                calendar.set(Calendar.MINUTE, 47);
                calendar.set(Calendar.SECOND, 0);

                Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

            }
        });

    }
}
