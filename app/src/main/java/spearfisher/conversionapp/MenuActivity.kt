package spearfisher.conversionapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import spearfisher.conversionapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setting.setOnClickListener {
            val intent = Intent(applicationContext, EntryActivity::class.java)
            startActivity(intent)
        }

        binding.measureButton.setOnClickListener {
            val intent = Intent(applicationContext, MeasureActivity::class.java)
            startActivity(intent)
        }

        binding.calendarButton.setOnClickListener {
            val intent = Intent(applicationContext, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.shoesButton.setOnClickListener {
            val intent = Intent(applicationContext, ShoesActivity::class.java)
            startActivity(intent)
        }

        binding.alcoholButton.setOnClickListener {
            val intent = Intent(applicationContext, AlcoholActivity::class.java)
            startActivity(intent)
        }



    }

}