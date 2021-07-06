package com.mohsinziabutt.newsapp.fragments.appStarterFragments

import android.os.Bundle
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
import com.mohsinziabutt.newsapp.responses.DefaultResponse
import kotlinx.android.synthetic.main.fragment_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment(), View.OnClickListener {

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
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val buttonSignUp = view.findViewById<Button>(R.id.buttonSignUp)
        val shiftToLogin = view.findViewById<Button>(R.id.shiftToLogin)

        buttonSignUp.setOnClickListener(this)
        shiftToLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.buttonSignUp -> {

                val email = editTextEmail.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                val name = editTextName.text.toString().trim()

                if(email.isEmpty()){
                    editTextEmail.error = "Email required"
                    editTextEmail.requestFocus()
                }

                if(password.isEmpty()){
                    editTextPassword.error = "Password required"
                    editTextPassword.requestFocus()
                }

                if(name.isEmpty()){
                    editTextName.error = "Name required"
                    editTextName.requestFocus()
                }

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty())
                {
                    LoginRegistrationApiClient.instance.createUser(email, name, password)
                    .enqueue(object: Callback<DefaultResponse> {

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                            navController!!.navigate(R.id.action_registrationFragment_to_loginFragment)
                        }

                    })
                }
            }
            R.id.shiftToLogin -> {
                navController!!.navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
    }
}