package com.mayouf.catstechnicaltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mayouf.catstechnicaltest.presentation.cats.CatsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                CatsFragment.newInstance(),
                CatsFragment.FRAGMENT_NAME
            )
            .commitAllowingStateLoss()

    }
}