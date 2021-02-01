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
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfNotes = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //daList.adapter = MyNotesAdapter(this,listOfNotes) //         DEL      ?????


        loadQuery("%")




//        listOfNotes.add(Note(1,"Go to job","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
//        listOfNotes.add(Note(2,"Go to zoo","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
//        listOfNotes.add(Note(3,"Go to mom","Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum."))
    }

    fun loadQuery(title:String) {
        var dbManager = DbManager(this)
        val projections = arrayOf("ID","title","description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.queryDB(projections,"Title like ?",selectionArgs,"Title")
        listOfNotes.clear()
        if(cursor.moveToFirst()){
            do{
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val title=cursor.getString(cursor.getColumnIndex("Title"))
                val description=cursor.getString(cursor.getColumnIndex("Description"))
                listOfNotes.add(Note(ID,title,description))
            }while(cursor.moveToNext())
        }
        daList.adapter = MyNotesAdapter(this,listOfNotes)

    }

    override fun onResume() {
        super.onResume()
        loadQuery("%")
    }

    inner class MyNotesAdapter:BaseAdapter {
        var listOfNotes = ArrayList<Note>()
        var context:Context?=null
        constructor(context: Context, listOfNotes:ArrayList<Note>){
            this.listOfNotes=listOfNotes
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket,null)
            var myNote = listOfNotes[position]
            myView.id = listOfNotes[position].id!!
            myView.tilte.text = listOfNotes[position].title
            myView.description.text = listOfNotes[position].description
            myView.ivDelete.setOnClickListener(View.OnClickListener {
                var dbManager = DbManager(this.context!!)
                val selectionArgs = arrayOf(myView.id.toString())
                dbManager.deleteDB("ID=?",selectionArgs)
                loadQuery("%")
            })
            myView.ivEdit.setOnClickListener(View.OnClickListener {
                goToUpdate(myNote)
            })
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
                loadQuery("%"+query+"%")
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

    fun goToUpdate(note:Note){
        var intent = Intent(this,AddNote::class.java)
        intent.putExtra("ID",note.id)
        intent.putExtra("Title",note.title)
        intent.putExtra("Description",note.description)
        startActivity(intent)
    }

}
