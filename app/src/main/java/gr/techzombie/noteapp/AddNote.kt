package gr.techzombie.noteapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import java.lang.Exception

class AddNote : AppCompatActivity() {
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)



        try {
            var bundle: Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                editText.setText(bundle.getString("Title").toString())
                description.setText(bundle.getString("Description").toString())
            }

        } catch (ex: Exception) {
        }
    }

    fun bu_add(view: View) {
        var dbManager = DbManager(this)
        var values = ContentValues()
        values.put("Title", editText.text.toString())
        values.put("Description", description.text.toString())

        if (id == 0) {
            val ID = dbManager.insertDB(values)
            if (ID > 0) {
                Toast.makeText(this, "note added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "cannot add note", Toast.LENGTH_LONG).show()
            }
        } else {
            val seletionArgs = arrayOf(id.toString())
            val ID = dbManager.updateDB(values, "ID=?", seletionArgs)
            if (ID > 0) {
                Toast.makeText(this, "note added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "cannot add note", Toast.LENGTH_LONG).show()
            }
        }
    }
}
