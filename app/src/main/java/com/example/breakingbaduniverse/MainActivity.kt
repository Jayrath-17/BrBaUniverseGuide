package com.example.breakingbaduniverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var mBlogList: RecyclerView
    lateinit var Database_reference: DatabaseReference
    lateinit var spinner_list: Spinner
    lateinit var spinner_values: Array<String>
    lateinit var array_adapter: ArrayAdapter<String>
   lateinit var progress:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress=findViewById(R.id.pro)

        mBlogList = findViewById(R.id.myrecycleview)
        mBlogList.setHasFixedSize(true)
        mBlogList.layoutManager = LinearLayoutManager(this)

        spinner_values = resources.getStringArray(R.array.SelectSeason)
        spinner_list = findViewById(R.id.select_seasons)

        var aa = ArrayAdapter(this, R.layout.spinner_list_layout, spinner_values)
        spinner_list.adapter = aa


        spinner_list.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                var DefaultItem = parent?.getItemAtPosition(0).toString()
                onStart(DefaultItem)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var Selecteditem = parent?.getItemAtPosition(position).toString()
                onStart(Selecteditem)
            }
        }

    }

    fun onStart(selectedSeason: String) {
        super.onStart()
        progress.visibility=View.VISIBLE
        Toast.makeText(this, selectedSeason, Toast.LENGTH_SHORT).show()
        var fire_adapter: FirebaseRecyclerAdapter<Blog, BlogViewHolder>

        Database_reference = FirebaseDatabase.getInstance().getReference().child(selectedSeason)
        Database_reference.keepSynced(true)

        fire_adapter = object : FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
            Blog::class.java, R.layout.blog_row,
            BlogViewHolder::class.java, Database_reference
        ) {
            override fun populateViewHolder(
                viewHolder: BlogViewHolder?,
                model: Blog?,
                position: Int
            ) {




                if (viewHolder != null && model != null) {
                    model.getEpisode_Number()?.let { viewHolder.setEpisode_Number(it) }
                    model.getName()?.let { viewHolder.setName(it) }
                    model.getDeath()?.let { viewHolder.setDeath(it) }
                    model.getHighlights()?.let { viewHolder.setHeighlight(it) }
                    model.getQuote()?.let { viewHolder.setQuote(it) }
                    model.getPoster()?.let { viewHolder.setPoster(it) }


                }

            }

            override fun onDataChanged() {
                super.onDataChanged()
                progress.visibility=View.GONE
            }
        };
        mBlogList.adapter = fire_adapter
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        public fun setEpisode_Number(episode_number: String) {
            var Episode_number = itemView.findViewById<TextView>(R.id.episode_number)
            Episode_number.setText(episode_number)
        }

        public fun setName(name: String) {
            var Name = itemView.findViewById<TextView>(R.id.episode_name)
            Name.setText(name)
        }

        public fun setHeighlight(heighlights: String) {
            var highlight = itemView.findViewById<TextView>(R.id.episode_heighlights)
            highlight.setText(heighlights)
            highlight.movementMethod=ScrollingMovementMethod()
        }

        public fun setDeath(death: String) {
            var Death = itemView.findViewById<TextView>(R.id.Dead_souls_count)
            Death.setText(death)
        }

        public fun setQuote(quote: String) {
            var Quote = itemView.findViewById<TextView>(R.id.quote)
            Quote.setText(quote)
            Quote.movementMethod=ScrollingMovementMethod()
        }

        public fun setPoster(poster: String) {
            var Poster = itemView.findViewById<ImageView>(R.id.card_image)
            Picasso.with(itemView.context).load(poster).into(Poster)
        }

    }
}