package com.example.projectmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.example.belajarrecyclerview.R
import com.example.projectmap.API.RetrofitHelper
import com.example.projectmap.API.UserApi
import com.example.projectmap.API.Users

class RegisterActivity : AppCompatActivity() {

    lateinit var btnRegister : Button
    lateinit var btnMoveToSignIn : Button
    var btnGender : RadioGroup? = null
    lateinit var txtnama : EditText
    lateinit var txtusername : EditText
    lateinit var txtemail : EditText
    lateinit var txtpassword : EditText

    val apiKey = ""
    val token = "Bearer $apiKey"

    val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.button_register)
        btnMoveToSignIn = findViewById(R.id.txt_Move_To_SignIn)
        btnGender = findViewById<RadioGroup>(R.id.rg_gender)
        txtnama = findViewById(R.id.txt_nama)
        txtusername = findViewById(R.id.txt_username)
        txtemail = findViewById(R.id.input_email)
        txtpassword = findViewById(R.id.txt_password)


        btnRegister.setOnClickListener {
            signUp(txtemail.text.toString(), txtpassword.text.toString())

            val cekGenderRadioButton : Int = btnGender!!.checkedRadioButtonId
            val gender = findViewById<RadioButton>(cekGenderRadioButton)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

        btnMoveToSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val data = Users(email = email, password = password)
            val response = userApi.signIn(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if (response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if (jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                var email = jsonResponse.getJSONObject("user").get("email")
                msg = "Successfully login! Welcome back: $email"
            } else {
                msg += jsonResponse.get("error_description").toString()
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }
}