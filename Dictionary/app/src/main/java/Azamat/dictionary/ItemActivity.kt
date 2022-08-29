package Azamat.dictionary

import Azamat.dictionary.databinding.ActivityItemBinding
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.util.zip.Inflater

class ItemActivity : AppCompatActivity() {

    private lateinit var adapter: WordAdapter

    private lateinit var binding : ActivityItemBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        binding = ActivityItemBinding.inflate(layoutInflater)

        var isEnglish = intent.getBooleanExtra("isEnglish", true)
        var a = intent.getStringExtra("id")


        if(isEnglish){

            val b = WordDB.getInstance()!!.updateItemUseEng(a!!.toInt())
            WordDB.getInstance()!!.updateUseEng(a!!.toInt(), b)

        }
        else{
            val b = WordDB.getInstance()!!.updateItemUseUzb(a!!.toInt())
            WordDB.getInstance()!!.updateUseUzb(a!!.toInt(), b)
        }




        findViewById<LinearLayout>(R.id.search_view).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        var data1 =  ArrayList<String>()

        if (isEnglish) {
            data1 = WordDB.getInstance()!!.getItemEng(a!!.toInt())
        }
        else {
            data1 = WordDB.getInstance()!!.getItemUzb(a!!.toInt())
        }

        findViewById<TextView>(R.id.word).text = data1.get(0)
        findViewById<TextView>(R.id.translate).text = data1.get(1)
        findViewById<TextView>(R.id.forExample).text = data1.get(2)

    }
}