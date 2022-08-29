package Azamat.dictionary

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import uz.ahronbek.db_helper.DbHelper
import kotlin.collections.ArrayList

class WordDB private constructor(context: Context): DbHelper(context, "Dictionary.db") {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var noteDB: WordDB? = null

        fun init(context: Context) {
            if (noteDB==null)
                noteDB = WordDB(context)

        }

        fun getInstance(): WordDB? {
            return noteDB
        }
    }


    fun getEnUz(): Cursor {
        return mDataBase.rawQuery("SELECT en._id, en.en as Eng, uz.uz as Uzb, en.example, en.last_used as Used FROM en_words_table as en, uz_worlds_table as uz WHERE en.uz = uz._id", emptyArray())
    }

    fun getUzEn(): Cursor {
        return mDataBase.rawQuery("SELECT uz._id, uz.uz as Uzb, en.en as Eng, uz.example, uz.last_used as Used FROM en_words_table as en, uz_worlds_table as uz WHERE uz.en = en._id    ", emptyArray())
    }

    fun getEnLike(): Cursor {
        return mDataBase.rawQuery("SELECT en._id, en.en as Eng, uz.uz as Uzb, en.example, en.last_used as Used FROM en_words_table as en, uz_worlds_table as uz WHERE en.uz = uz._id AND en.last_used>0", emptyArray())
    }

    fun getUzLike(): Cursor {
        return mDataBase.rawQuery("SELECT uz._id, uz.uz as Uzb, en.en as Eng, uz.example, uz.last_used as Used FROM uz_worlds_table as uz, en_words_table as en WHERE uz.en = en._id and uz.last_used>0", emptyArray())
    }



    fun getItemEng(index: Int): ArrayList<String> {

        val data = ArrayList<String>()

        val query ="SELECT en._id, en.en as Eng, uz.uz as Uzb, en.example, en.last_used as Used FROM en_words_table as en, uz_worlds_table as uz WHERE  en.uz = uz._id and en._id=$index"
        val cursor =mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        val word = cursor.getString(1)
        val translate = cursor.getString(2)
        val example = cursor.getString(3)
        data.add(word)
        data.add(translate)
        data.add(example)
        return data
    }

    fun getItemUzb(index: Int): ArrayList<String> {

        val data = ArrayList<String>()

        val query ="SELECT uz._id, uz.uz as Uzb, en.en as Eng, uz.example, uz.last_used as Used FROM en_words_table as en, uz_worlds_table as uz WHERE en.uz = uz._id and uz._id=$index"
        val cursor =mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        val word = cursor.getString(1)
        val translate = cursor.getString(2)
        val example = cursor.getString(3)
        data.add(word)
        data.add(translate)
        data.add(example)
        return data
    }

    fun updateItemUseEng(index: Int): Int {
        val query = "SELECT * FROM en_words_table where _id = $index"
        val cursor = mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        return cursor.getInt(4)
    }

    fun updateUseEng(index: Int, used:Int): Int {
        val query = "UPDATE en_words_table set last_used = $used+1 where _id = $index"
        val cursor = mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        return used+1
    }

    fun updateItemUseUzb(index: Int): Int {
        val query = "SELECT * FROM uz_worlds_table where _id = $index"
        val cursor = mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        return cursor.getInt(4)
    }

    fun updateUseUzb(index: Int, used:Int): Int {
        val query = "UPDATE uz_worlds_table set last_used = $used+1 where _id = $index"
        val cursor = mDataBase.rawQuery(query, emptyArray())
        cursor.moveToFirst()
        return used+1
    }



}