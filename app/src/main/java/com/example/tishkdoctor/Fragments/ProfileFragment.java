package com.example.tishkdoctor.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tishkdoctor.Activitys.SettingActivity;
import com.example.tishkdoctor.Models.Doctor;
import com.example.tishkdoctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference patientRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String currentUserId;
    private Doctor currentPateint;
    private Dialog popupEditProfile;
    private CircleImageView profileImg;
    private ImageView edtProfile;

    private TextView txtName,txtMail,txtGender,txtEducation,txtMobile,
            txtWork,txtDateOfBirth,txtCity;
    private String bloodGroup="",education="",work="",gender="",marriage="",dateOfBirth="",city="";
    private String doctorType="Surgeon";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        patientRef=mFirebaseDatabase.getReference("Doctors");

        txtName=(TextView)v.findViewById(R.id.name);
        txtMail=(TextView)v.findViewById(R.id.email);
        txtMobile=(TextView)v.findViewById(R.id.mobileNumber);


        txtEducation=(TextView)v.findViewById(R.id.education);
        txtWork=(TextView)v.findViewById(R.id.occupation);
        txtGender=(TextView)v.findViewById(R.id.gender);
        txtDateOfBirth=(TextView)v.findViewById(R.id.dob);

        txtCity=(TextView)v.findViewById(R.id.location);
        edtProfile=(ImageView)v.findViewById(R.id.edit);

        doctorType=getDoctorType();
        profileImg=(CircleImageView)v.findViewById(R.id.profile);

        userProfileDisplay(profileImg,txtMail,txtMobile,txtDateOfBirth,txtEducation,txtWork,txtName,txtCity,txtGender);


        edtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);

            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUserId=mCurrentUser.getUid();

        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(currentUserId)) {
                    //currentPateint = dataSnapshot.child(currentUserId).getValue(Doctor.class);
                    //Prelevents.currentOnlineUser = currentUser;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

//    private void updateOnlyUserInfo() {
//
//
//        HashMap<String, Object> userMap = new HashMap<>();
//
//        if (!edtPopUpUserName.getText().toString().isEmpty() && !edtPopUpUserMail.getText().toString().isEmpty() &&
//                !edtPopUpUserPhone.getText().toString().isEmpty() && !edtPopUpUserDepartment.getText().toString().isEmpty()) {
//
//            userMap.put("username", edtPopUpUserName.getText().toString());
//            userMap.put("userMail", edtPopUpUserMail.getText().toString());
//            userMap.put("userPhoneNumber", edtPopUpUserPhone.getText().toString());
//            userMap.put("userDepartment", edtPopUpUserDepartment.getText().toString());
//
//            userNodeDatabaseRefrence.child(mCurrentUser.getUid()).updateChildren(userMap);
//            showMessage("Profile info updated successfully");
//
//        } else {
//            showMessage("Confirm All field");
//        }
//
//    }

    private void showMessage(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }


    private void userProfileDisplay(final CircleImageView profileImageView, final TextView Mail, final TextView mobile,
                                    final TextView edtDateOfbirth, final TextView education, final  TextView work, final TextView name, final TextView City, final TextView Gender) {

        Toast.makeText(getContext(), doctorType, Toast.LENGTH_SHORT).show();
        DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference().child("Doctors").child(doctorType).child(mCurrentUser.getUid());
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("doctorImg").exists()){

                        String image = dataSnapshot.child("doctorImg").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);

                    }if (dataSnapshot.child("education").exists()){
                        String educationString = dataSnapshot.child("education").getValue().toString();
                        education.setText(educationString);
                    }if (dataSnapshot.child("dateOfBirth").exists()){
                        String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                        edtDateOfbirth.setText(dateOfBirth);
                    } if (dataSnapshot.child("phone").exists()){
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        mobile.setText(phone);
                    }  if (dataSnapshot.child("work").exists()){
                        String workString= dataSnapshot.child("work").getValue().toString();
                        work.setText(workString);
                    }  if (dataSnapshot.child("email").exists()){
                        String emailAddress = dataSnapshot.child("email").getValue().toString();
                        Mail.setText(emailAddress);
                    }   if (dataSnapshot.child("gender").exists()){
                        String gender=dataSnapshot.child("gender").getValue().toString();
                        Gender.setText(gender);
                    }   if (dataSnapshot.child("location").exists()){
                        String CityS=dataSnapshot.child("location").getValue().toString();
                        City.setText(CityS);
                    }
                    if (dataSnapshot.child("name").exists()){
                        String CityS=dataSnapshot.child("name").getValue().toString();
                        name.setText(CityS);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String getDoctorType() {
        SharedPreferences getTheme = getActivity().getSharedPreferences("myPref", MODE_PRIVATE);
        String DoctorType = getTheme.getString("doctorType", "Surgeon");
        return DoctorType;

    }


}
