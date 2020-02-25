package com.example.oneside.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null

    private var mDelayHandler: Handler? = null
    private val mSplashTimeOut: Long = 3000 //3 seconds

    private val mRunnable: Runnable = Runnable {
        sharedPreferences = this.getSharedPreferences("PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
        if (!isFinishing) {
            if(sharedPreferences!!.getString("FixedArray", "DEFAULT_STRING")!! != "DEFAULT_STRING") {
                val intent = Intent(applicationContext, GameActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val intent = Intent(applicationContext, LandingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, mSplashTimeOut)

        iv_app_logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        tv_app_name.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
