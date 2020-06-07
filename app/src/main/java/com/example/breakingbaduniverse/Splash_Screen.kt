package com.example.breakingbaduniverse

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Splash_Screen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        var top_left_imageView=findViewById<ImageView>(R.id.bb10)
        var top_right_imageView=findViewById<ImageView>(R.id.bb11)
        var middle_left_imageView=findViewById<ImageView>(R.id.bb20)
        var middle_right_imageView=findViewById<ImageView>(R.id.bb21)
        var bottom_left_imageView=findViewById<ImageView>(R.id.bb30)
        var bottom_right_imageView=findViewById<ImageView>(R.id.bb31)

        var welcome_text=findViewById<TextView>(R.id.welcome_text)

        top_left_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_top_left)
        top_right_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_top_right)
        middle_left_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_middle_left)
        middle_right_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_middle_left)
        bottom_left_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_bottom_left)
        bottom_right_imageView.animation=AnimationUtils.loadAnimation(this,R.anim.anim_bottom_left)

        welcome_text.animation=AnimationUtils.loadAnimation(this,R.anim.anim_welcome_text)


        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },3000)
    }
}
