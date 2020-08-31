package com.example.shared.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shared.mvp.view.BaseView

abstract class BaseActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}