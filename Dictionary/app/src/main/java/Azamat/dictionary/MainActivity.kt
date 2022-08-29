package Azamat.dictionary

import Azamat.dictionary.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WordAdapter

    private lateinit var binding: ActivityMainBinding

    private var isEnglish = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        binding = ActivityMainBinding.inflate(layoutInflater)

        adapter = WordAdapter(this, WordDB.getInstance()!!.getEnUz())

        findViewById<ListView>(R.id.list_view).adapter = adapter

        loadActions()

    }




    private fun loadActions() {

        // Change language
        findViewById<ImageView>(R.id.change_view).setOnClickListener {

            if(isEnglish)
                adapter.changeCursor(WordDB.getInstance()!!.getUzEn())

            else
                adapter.changeCursor(WordDB.getInstance()!!.getEnUz())

            isEnglish = !isEnglish


        }

        findViewById<ListView>(R.id.list_view).setOnItemClickListener { parent, view, p, l ->



            var intent = Intent(this, ItemActivity::class.java)
                intent.putExtra("id", l.toString())
                intent.putExtra("isEnglish", isEnglish)

                startActivity(intent)

        }

        findViewById<TextView>(R.id.likes).setOnClickListener {


            if(isEnglish)
                adapter.changeCursor(WordDB.getInstance()!!.getEnLike())
            else
                adapter.changeCursor(WordDB.getInstance()!!.getUzLike())
        }

        findViewById<TextView>(R.id.all_words).setOnClickListener {
            if(isEnglish)
                adapter.changeCursor(WordDB.getInstance()!!.getEnUz())
            else
                adapter.changeCursor(WordDB.getInstance()!!.getUzEn())

        }
    }
}