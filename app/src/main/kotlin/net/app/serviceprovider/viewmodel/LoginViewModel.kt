package net.gahfy.serviceprovider.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import net.gahfy.serviceprovider.base.BaseViewModel
import net.gahfy.serviceprovider.model.LoginUser
import net.gahfy.serviceprovider.ui.post.MainActivity
import net.gahfy.serviceprovider.ui.post.RegisterActivity

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var fAuth: FirebaseAuth = FirebaseAuth.getInstance()


    private val context = getApplication<Application>().applicationContext
    var EmailAddress = MutableLiveData<String>()
    var Password = MutableLiveData<String>()
    private var userMutableLiveData: MutableLiveData<LoginUser>? = null
    val user: MutableLiveData<LoginUser>?
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData<LoginUser>()
            }

            return userMutableLiveData
        }

    fun onClick(view: View?) {
        val loginUser = LoginUser(EmailAddress.value, Password.value)
        userMutableLiveData!!.setValue(loginUser)
    }
    fun navigate(view: View?) {
        context.startActivity(Intent(context,RegisterActivity::class.java))
    }
    fun singinUser(email:String, password:String) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(getApplication(), "Logged in Successfully", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, MainActivity::class.java))
            } else {
                Toast.makeText(context, "Error ! " + task.exception!!.message, Toast.LENGTH_SHORT).show()
//                progressBar.setVisibility(View.GONE)
            }
        }
    }
}