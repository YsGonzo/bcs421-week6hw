package edu.farmingdale.bcs421.bcs421_f22_w6_fragmentsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    lateinit var mBtn1:Button
    lateinit var mBtn2:Button
    lateinit var mTV:TextView
    lateinit var mSeekBar: SeekBar
    lateinit var mET: EditText
    var seekBarTapped = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mTV = findViewById(R.id.tv1)
        mET = findViewById(R.id.editText01)
        mSeekBar = findViewById(R.id.sb1)
        var frgmnt01 = Fragment01()
        var frgmnt02 = Fragment02()

        mSeekBar?.max = 60
        mSeekBar?.min = 15


            mSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Changes EditText value after the user taps the SeekBar
                    if (fromUser) {
                        seekBarTapped = true
                    }
                    // Forces the progress value to to stay within the range of 15-60
                    val newValue = progress.coerceIn(15, 60)
                    mSeekBar?.progress = newValue

                    // Sets the EditText element to the seekBar value
                    // and changes TextView font size to it
                    mET.setText(newValue.toString())
                    val fontSize = newValue.toFloat()
                    mTV.textSize = fontSize
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        mBtn1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt01)
                commit()
            }
        }
        mBtn2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt02)
                commit()
            }
            readFromSharedPref()
        }
    }

    private fun readFromSharedPref(){
        var sharedPref= getSharedPreferences(MainActivity().SHAREDPREF_FILENAME, MODE_PRIVATE)
        mTV.setText(sharedPref.getString("KEY", "not forund"))
    }
}