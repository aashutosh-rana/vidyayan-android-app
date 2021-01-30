package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_otp_verify.*
import java.util.concurrent.TimeUnit


class OtpVerifyActivity : AppCompatActivity() {

    private lateinit var btnVerify:Button
    private lateinit var txtNumber:TextView
    private lateinit var txtResend:TextView
    private lateinit var etOtp:EditText

    private var mVerificationId: String? = null
    private lateinit var mAuth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        val number = intent.getStringExtra("mobileNumber")!!.toString()
        val userType=intent.getStringExtra("studentOrTeacher")!!.toString()

        btnVerify=findViewById(R.id.btnVerify)
        txtNumber=findViewById(R.id.txtNumber)
        txtResend=findViewById(R.id.txtResend)
        etOtp=findViewById(R.id.etOtp)
        txtNumber.text = "+91 $number"
        txtNumber.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }

        mAuth = FirebaseAuth.getInstance()
        sendVerificationCode(number)

        btnVerify.setOnClickListener(){
            val code: String = etOtp.getText().toString().trim()

//            if(etOtp.text.toString()== "1234" && userType =="student"){
//                startActivity(Intent(this, HomeActivity::class.java))
//            }
//
//            if(etOtp.text.toString()!="1234" && userType == "student" ){
//                startActivity(Intent(this, ChooseClassActivity::class.java))
//            }
//
//            if(etOtp.text.toString()== "1234" && userType =="teacher"){
//                startActivity(Intent(this, HomeTeacher::class.java))
//            }
//
//            if(etOtp.text.toString()!="1234" && userType == "teacher" ){
//                startActivity(Intent(this, TeacherRegistrationActivity2::class.java))
//            }
            verifyVerificationCode(code)

        }
    }

    private val mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    etOtp.setText(code)
                    verifyVerificationCode(code)
                }
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@OtpVerifyActivity, e.message, Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                mVerificationId = s
            }
        }

    private fun sendVerificationCode(mobile: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mobile",
            60,
            TimeUnit.SECONDS,
            this,
            mCallbacks
        )
    }

    private fun verifyVerificationCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this@OtpVerifyActivity) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@OtpVerifyActivity, ChooseClassActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    var message = "Somthing is wrong, we will fix it soon..."
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }
                    val snackbar = Snackbar.make(
                        findViewById(R.id.parent),
                        message, Snackbar.LENGTH_LONG
                    )
                }
            }
    }

}