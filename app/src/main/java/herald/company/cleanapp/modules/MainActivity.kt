package herald.company.cleanapp.modules

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import herald.company.cleanapp.R
import herald.company.cleanapp.common.BaseActivity
import kotlinx.android.synthetic.main.widget_appbar.*

class MainActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_main

    override fun afterLayout(savedInstanceState: Bundle?) {
        super.afterLayout(savedInstanceState)
        val host = NavHostFragment.create(R.navigation.main_navigation)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, host)
            .setPrimaryNavigationFragment(host).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }

}
