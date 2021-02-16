package com.katepatty.katessearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {


    private lateinit var toolbar: Toolbar
    lateinit var adapter: ArrayAdapter<*>
    private lateinit var listView: ListView
    private lateinit var tView: TextView


    // List Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Kates Search"
        toolbar = findViewById(R.id.toolbar)
        listView = findViewById(R.id.listView)
        tView = findViewById(R.id.textView)
        adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.zodiac_array))

        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { adapterView, _, i, _ ->
            Toast.makeText(this@MainActivity, adapterView.getItemAtPosition(i).toString(),
                    Toast.LENGTH_SHORT).show()
        }
        listView.emptyView = tView

    }

    // SearchBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search your horoscope~"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


}