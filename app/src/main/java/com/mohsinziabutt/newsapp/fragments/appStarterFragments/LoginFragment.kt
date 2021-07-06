package com.mohsinziabutt.newsapp.fragments.appStarterFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mohsinziabutt.firstkotlinproject.api.LoginRegistrationApiClient
import com.mohsinziabutt.newsapp.R
import com.mohsinziabutt.newsapp.activities.NewsActivityMain
import com.mohsinziabutt.newsapp.responses.LoginResponse
import com.mohsinziabutt.newsapp.prefmanager.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        recipient = requireArguments().getString("recipient")!!
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
        val shiftToRegister = view.findViewById<Button>(R.id.shiftToRegister)

        buttonLogin.setOnClickListener(this)
        shiftToRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.buttonLogin -> {

                var email = editTextEmail.text.toString()
                var password = editTextPassword.text.toString()

                if(email.isEmpty()){
                    editTextEmail.error = "Email required"
                    editTextEmail.requestFocus()
                }

                if(password.isEmpty()){
                    editTextPassword.error = "Password required"
                    editTextPassword.requestFocus()
                }

                if(!password.isEmpty() && !email.isEmpty())
                {
                    LoginRegistrationApiClient.instance.userLogin(email, password)
                        .enqueue(object: Callback<LoginResponse> {

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                if(!response.body()?.error!!)
                                {
                                    //to check log
                                    Log.d("LOGIN_RESPONSE", "" + response.body())

                                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                                    context?.let { SharedPrefManager.getInstance(it).saveUser(response.body()?.user!!) }

                                    val intent = Intent(context, NewsActivityMain::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                }
                                else
                                {
                                    //to check log
                                    Log.d("LOGIN_RESPONSE", "" + response.body())

                                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                                }
                            }

                        })
                }
            }

            R.id.shiftToRegister -> {
                navController!!.navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }
}