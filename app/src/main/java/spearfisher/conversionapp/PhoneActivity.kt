package spearfisher.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import spearfisher.conversionapp.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}