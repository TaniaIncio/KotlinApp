package com.tincio.pharmaapp.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.presentation.fragment.ClientesFragment

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.container, ClientesFragment()).commit()
    }
}
