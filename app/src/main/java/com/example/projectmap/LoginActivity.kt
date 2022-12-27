package com.example.projectmap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.example.belajarrecyclerview.R
import com.example.projectmap.API.RetrofitHelper
import com.example.projectmap.API.UserApi
import com.example.projectmap.API.Users

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var btnMoveToSignUp : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText

    val apiKey = ""
    val token = "Bearer $apiKey"

    val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.button_login)
        btnMoveToSignUp = findViewById(R.id.txt_Move_To_SignUp)
        etEmail = findViewById(R.id.usn_login)
        etPassword = findViewById(R.id.password_login)

        btnLogin.setOnClickListener {
            signIn(etEmail.text.toString(), etPassword.text.toString())
        }

        btnMoveToSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
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