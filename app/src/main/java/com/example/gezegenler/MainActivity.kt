package com.example.gezegenler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    val KILO_TO_POUND=2.2045
    val MARS=0.38
    val JUPITER=2.34
    val VENUS=0.91
    val POUND_TO_KILO=0.45359237


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.drawable.dunya).into(imageView2)

        tvSonuc.text=savedInstanceState?.getString("sonuc")
        cbVenus.setOnClickListener(this)
        cbMars.setOnClickListener(this)
        cbJupiter.setOnClickListener(this)
    }

    private fun kiloToPound(kilo:Double):Double{
        return kilo*KILO_TO_POUND
    }
    private fun poundToKilo(pound:Double):Double{
        return pound*POUND_TO_KILO
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("sonuc",tvSonuc.text.toString())
    }

    override fun onClick(v: View?) {
        v as CheckBox
        var isChecked:Boolean=v.isChecked

        if (!TextUtils.isEmpty(etKilo.text.toString())){
            var kullaniciKilo=etKilo.text.toString().toDouble()
            var kullaniciPound=kiloToPound(kullaniciKilo)

            when(v.id){
                R.id.cbMars -> if (isChecked){
                    cbJupiter.isChecked=false
                    cbVenus.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
                R.id.cbJupiter -> if (isChecked){
                    cbVenus.isChecked=false
                    cbMars.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
                R.id.cbVenus -> if (isChecked){
                    cbMars.isChecked=false
                    cbJupiter.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
            }
        }
    }

    fun hesaplaAgirlikPound(pound:Double,checkBox: CheckBox){
            var sonuc: Double =0.0
            when(checkBox.id){
                R.id.cbMars -> sonuc=pound*MARS
                R.id.cbJupiter -> sonuc=pound*JUPITER
                R.id.cbVenus -> sonuc=pound*VENUS
                else -> sonuc=0.0
            }
            var sonucToKilo=poundToKilo(sonuc)
            tvSonuc.text=sonucToKilo.toString()
    }
}
