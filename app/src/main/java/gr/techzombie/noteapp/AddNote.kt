package gr.techzombie.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun bu_add(view: View){
        finish()
    }
}
