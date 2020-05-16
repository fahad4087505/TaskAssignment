package net.gahfy.mvvmposts.ui.post
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ActivitySigninBinding
import net.gahfy.mvvmposts.viewmodel.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var binding: ActivitySigninBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_signin)
        binding!!.lifecycleOwner = this
        binding!!.loginViewModel = loginViewModel
        loginViewModel!!.user!!.observe(this, Observer { loginUser ->
            if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strEmailAddress)) {
                binding!!.txtEmailAddress.error = "Enter an E-Mail Address"
                binding!!.txtEmailAddress.requestFocus()
            } else if (!loginUser!!.isEmailValid) {
                binding!!.txtEmailAddress.error = "Enter a Valid E-mail Address"
                binding!!.txtEmailAddress.requestFocus()
            } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strPassword)) {
                binding!!.txtPassword.error = "Enter a Password"
                binding!!.txtPassword.requestFocus()
            } else if (!loginUser.isPasswordLengthGreaterThan5) {
                binding!!.txtPassword.error = "Enter at least 6 Digit password"
                binding!!.txtPassword.requestFocus()
            } else {
                binding!!.lblEmailAnswer.text = loginUser.strEmailAddress
                binding!!.lblPasswordAnswer.text = loginUser.strPassword
                loginViewModel!!.singinUser(loginUser.strEmailAddress,loginUser.strPassword)
            }
        })
    }
}