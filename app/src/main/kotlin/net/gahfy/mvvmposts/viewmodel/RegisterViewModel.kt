package net.gahfy.mvvmposts.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.gahfy.mvvmposts.model.RegisterUser
import net.gahfy.mvvmposts.ui.post.PostListActivity
import java.util.*


class RegisterViewModel(application: Application )  : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var fAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    var fStore: FirebaseFirestore? = FirebaseFirestore.getInstance()
    var Name = MutableLiveData<String>()
    var Phone = MutableLiveData<String>()
    var EmailAddress = MutableLiveData<String>()
    var Password = MutableLiveData<String>()
    var userID: String? = null

    private var userMutableLiveData: MutableLiveData<RegisterUser>? = null
    val user: MutableLiveData<RegisterUser>?
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData<RegisterUser>()
            }
            return userMutableLiveData
        }

    fun onClick(view: View?) {
        val registerUser = RegisterUser(Name.value,EmailAddress.value, Password.value,Phone.value)
        userMutableLiveData!!.setValue(registerUser)
    }
    fun registerUser(fullName:String,email:String,password:String,phone:String){


        // register the user in firebase
        fAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                // send verification link
                val fuser = fAuth!!.currentUser
                fuser!!.sendEmailVerification().addOnSuccessListener { Toast.makeText(context, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show() }.addOnFailureListener {
                     }
                Toast.makeText(context, "User Created.", Toast.LENGTH_SHORT).show()
                userID = fAuth!!.currentUser!!.uid
                val documentReference = fStore!!.collection("users").document(userID!!)
                val user: MutableMap<String, Any> = HashMap()
                user["fName"] = fullName
                user["email"] = email
                user["phone"] = phone
                documentReference.set(user).addOnSuccessListener {
                }.addOnFailureListener {
                }
                context.startActivity(Intent(context,PostListActivity ::class.java))
            } else {
                Toast.makeText(context, "Error ! " + task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}