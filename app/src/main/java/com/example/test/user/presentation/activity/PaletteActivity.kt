package com.example.test.user.presentation.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.palette.graphics.Palette
import com.example.test.R
import com.example.test.databinding.ActivityPaletteBinding

class PaletteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaletteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaletteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
        extractColorsAndApply(bitmap)



    }

    private fun extractColorsAndApply(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val vibrantColor = palette?.vibrantSwatch?.rgb ?: 0x000000
            val darkMutedColor = palette?.darkMutedSwatch?.rgb ?: 0x000000

            // Set Toolbar color
            binding.toolbar.setBackgroundColor(vibrantColor)
            // Set Status Bar color
            window.statusBarColor = darkMutedColor
        }
    }


}