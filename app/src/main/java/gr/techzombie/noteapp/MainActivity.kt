package gr.techzombie.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfNotes = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        daList.adapter = MyNotesAdapter(listOfNotes)

        listOfNotes.add(Note(1,"Go to job","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
        listOfNotes.add(Note(2,"Go to zoo","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
        listOfNotes.add(Note(3,"Go to mom","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
    }

    inner class MyNotesAdapter:BaseAdapter {
        var listOfNotes = ArrayList<Note>()
        constructor(listOfNotes:ArrayList<Note>){
            this.listOfNotes=listOfNotes
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket,null)
            myView.id = listOfNotes[position].id!!
            myView.tilte.text = listOfNotes[position].title
            myView.description.text = listOfNotes[position].description
            return  myView

        }

        override fun getItem(position: Int): Any {
            return listOfNotes[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfNotes.size
        }
    }
}
