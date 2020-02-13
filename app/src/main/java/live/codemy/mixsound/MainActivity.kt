package live.codemy.mixsound

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import live.codemy.mixsoundlib.MixSound
import live.codemy.mixsoundlib.SoundType

//isimlendirmeden sonra activity
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activity öne geliyor sonra xml ismi gruplu bir şekilde görmek için

        setContentView(R.layout.activity_main)


        val imgbMic = findViewById<ImageButton>(R.id.imgb_mic)
        val imgbFast = findViewById<ImageButton>(R.id.imgb_fast)
        val imgbSlow = findViewById<ImageButton>(R.id.imgb_slow)
        val imgbDarthVader = findViewById<ImageButton>(R.id.imgb_DarthVader)
        val imgbChimmonk = findViewById<ImageButton>(R.id.imgb_chimmonk)

        imgbFast.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Fast)
            "Fast Tamam" extShowToast this
        }
        imgbSlow.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Slow)
            "Slow Tamam" extShowToast this
        }
        imgbDarthVader.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.DarthVader)
            "Darth Vader" extShowToast this
        }

        imgbMic.setOnClickListener {
            MixSound.getInstance(this).recordSound()
            "mic" extShowToast this
        }
        imgbChimmonk.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Chipmunk)
            "Chimmonk" extShowToast this
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            MixSound.CODE_SPEECH_RECOGNIZER -> {
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    MixSound.recordSound = result.first()
                }
            }
        }
    }
}

infix fun String.extShowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT)

}