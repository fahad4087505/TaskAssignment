package net.gahfy.mvvmposts.ui.post


import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ActivitySignupBinding
import net.gahfy.mvvmposts.viewmodel.RegisterViewModel
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private var registerViewModel: RegisterViewModel? = null
    private var binding: ActivitySignupBinding? = null
    var fAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_signup)
        binding!!.lifecycleOwner = this
        binding!!.registerViewModel=registerViewModel
        fAuth = FirebaseAuth.getInstance()
        registerViewModel!!.user!!.observe(this, Observer { registerUser ->
            if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).strEmailAddress)) {
                binding!!.txtEmailAddress.error = "Enter an E-Mail Address"
                binding!!.txtEmailAddress.requestFocus()
            } else if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).strEmailAddress)) {
                binding!!.txtEmailAddress.error = "Enter an E-Mail Address"
                binding!!.txtEmailAddress.requestFocus()
            } else if (!registerUser!!.isEmailValid) {
                binding!!.txtEmailAddress.error = "Enter a Valid E-mail Address"
                binding!!.txtEmailAddress.requestFocus()
            } else if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).strPassword)) {
                binding!!.txtPassword.error = "Enter a Password"
                binding!!.txtPassword.requestFocus()
            } else if (!registerUser.isPasswordLengthGreaterThan5) {
                binding!!.txtPassword.error = "Enter at least 6 Digit password"
                binding!!.txtPassword.requestFocus()
            } else {
                binding!!.lblEmailAnswer.text = registerUser.strEmailAddress
                binding!!.lblPasswordAnswer.text = registerUser.strPassword
                registerViewModel!!.registerUser(registerUser.strName,registerUser.strEmailAddress,registerUser.strPassword,registerUser.strPhone)
            }
        })
    }
}