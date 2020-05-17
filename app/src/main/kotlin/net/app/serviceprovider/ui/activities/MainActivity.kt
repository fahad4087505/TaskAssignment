package net.app.serviceprovider.ui.activities
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_navigation.*
import net.app.serviceprovider.R
import net.app.serviceprovider.base.BaseActivity
import net.app.serviceprovider.fragments.PostListFragment

class MainActivity : BaseActivity() {
    private var t: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        t = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout!!.addDrawerListener(t!!)
        t!!.syncState()
        openFragment(PostListFragment())
        drawer_icon!!.setOnClickListener { drawer_layout!!.openDrawer(GravityCompat.START) }
        navigation_drawer_view!!.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            val id = item.itemId
            when (id) {
                R.id.nav_home -> Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                R.id.nav_gallery -> {
                    Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_slideshow -> {
                    Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                }
                else -> return@OnNavigationItemSelectedListener true
            }
            closeDrawer()
            true
        })
        signout_button.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        }
    }
    private fun closeDrawer() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        }
    }

    private fun openFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.container, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (t!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }
}