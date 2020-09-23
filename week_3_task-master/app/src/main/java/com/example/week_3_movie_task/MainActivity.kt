package com.example.week_3_movie_task


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_details.*

class MainActivity : AppCompatActivity() {
    var currentPageNumber = 1
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var llm: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(mutableListOf()){ element -> run{
            setContentView(R.layout.movie_details)
            textView2.setMovementMethod(ScrollingMovementMethod())
            textView6.text = element.title
            textView10.text = element.rating.toString()
            textView11.text = element.voteCount.toString()
            textView13.text = element.popularity.toString()
            textView2.text = element.overview
            textView14.text = element.id.toString()
            textView15.text = element.releaseDate
            textView16.text = element.isAdult.toString()
            textView17.text = element.originalLanguage
           
            Glide.with(imageView).load("https://image.tmdb.org/t/p/w342${element.backdropPath}")
                .transform(CenterCrop())
                .into(imageView)


        } }

        llm = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv_movies.adapter = moviesAdapter
        rv_movies.layoutManager = llm
        getPopularMovies()
        // This is in development branch
    }

    fun getPopularMovies(){
        Log.d(
            "Popular Movies",
            "here"
        )
        MoviesClient.fetchPopularMovies(
            currentPageNumber,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onError() {
        Toast
                .makeText(this, "Failed to fetch movies", Toast.LENGTH_SHORT)
                .show()
    }

    private fun onPopularMoviesFetched(list: MutableList<Movie>) {
        moviesAdapter.appendMovies(list)
        attachOnScrollListener()
    }

    fun attachOnScrollListener(){
        rv_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemsCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if (firstVisibleItem + visibleItemsCount >= totalItems / 2) {
                    rv_movies.removeOnScrollListener(this)
                    currentPageNumber++
                    getPopularMovies()
                }
            }
        })
    }

}