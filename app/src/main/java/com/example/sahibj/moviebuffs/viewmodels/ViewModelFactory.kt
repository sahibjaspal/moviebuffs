package com.example.sahibj.moviebuffs.viewmodels

/**
 * Created by sahibjaspal on 1/28/18.
 */
class ViewModelFactory {

    fun <T: PopMoviesViewModel> getViewModelClass(fragmentType: String?): Class<T> {
        when (fragmentType) {
            "Top Rated" -> return TopRatedMoviesViewModel::class.java as Class<T>
            "Upcoming" -> return UpcomingMoviesViewModel::class.java as Class<T>
            "Now Playing" -> return NowPlayingMoviesViewModel::class.java as Class<T>
        }
        return PopMoviesViewModel::class.java as Class<T>
    }
}