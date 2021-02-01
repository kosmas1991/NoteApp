package gr.techzombie.noteapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun bu_add(view: View){
        var dbManager = DbManager(this)
        var values=ContentValues()
        values.put("Title",editText.text.toString())
        values.put("Description",description.text.toString())
        val ID = dbManager.insertDB(values)
        if(ID>0) {
            Toast.makeText(this,"note added",Toast.LENGTH_LONG).show()
            finish()
        }else {
            Toast.makeText(this,"cannot add note",Toast.LENGTH_LONG).show()
        }
    }
}
