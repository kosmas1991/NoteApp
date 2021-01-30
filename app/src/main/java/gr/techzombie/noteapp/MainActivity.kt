package gr.techzombie.noteapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                //TODO: search database
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO("not implemented") To change body of created functions use File | Settings | File Templates.
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add_note -> {
                var intent = Intent(this,AddNote::class.java)
                startActivity(intent)
            }
            R.id.app_bar_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
