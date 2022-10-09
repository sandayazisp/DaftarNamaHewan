package id.sandayazisp.daftarnamahewan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import id.sandayazisp.daftarnamahewan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // kode dibawah digunakan untuk mendapatkan fragment host navigasi
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // kode dibawah digunakan untuk membuat instance dengan navController menggunakan navHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    /**
     * kode dibawah digunakan untuk mengaktifkan tombol kembali
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}