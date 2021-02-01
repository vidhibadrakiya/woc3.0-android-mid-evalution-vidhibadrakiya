package com.example.onepluschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onepluschat.databinding.ActivityOTPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
    ActivityOTPBinding bi;
     FirebaseAuth a;
     String k;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         bi = ActivityOTPBinding.inflate(getLayoutInflater());
         setContentView(bi.getRoot());

         a = FirebaseAuth.getInstance();
         String pn = getIntent().getStringExtra("Number");
         PhoneAuthOptions op = PhoneAuthOptions.newBuilder(a)
                 .setPhoneNumber(pn)
                 .setTimeout(60L, TimeUnit.SECONDS)
                 .setActivity(OTP.this)
                 .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                     @Override
                     public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                     }

                     @Override
                     public void onVerificationFailed(@NonNull FirebaseException e) {

                     }
                     @Override
                     public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                         k = verifyId;
                     }
                 }).build();
         PhoneAuthProvider.verifyPhoneNumber(op);

        bi.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
             @Override
             public void onOtpCompleted(String otp) {
                 PhoneAuthCredential credential = PhoneAuthProvider.getCredential(k, otp);

                 a.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             Intent i = new Intent(getApplicationContext(), Profile.class);
                             startActivity(i);

                         } else {
                             Toast.makeText(OTP.this, "Failed.", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
             }
         });
         }
     }