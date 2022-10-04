package com.example.flixsterplus_part2


/** This interface is used by the [InTheaterRecycleViewAdapter] to ensure it has an appropriate
 *  Listener. In this app, it's implemented by [InTheaterMovieFragment].  */

interface OnListFragmentInteractionListener {
    fun onItemClick(item: Movie) {}
}