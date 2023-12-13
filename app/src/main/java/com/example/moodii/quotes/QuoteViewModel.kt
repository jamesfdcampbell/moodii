package com.example.moodii.quotes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {
    // Mutable state to hold a list of quotes
    private val _quotes = mutableStateOf<List<Quote>?>(null)

    // Expose the quotes as a read-only State
    val quotes: State<List<Quote>?> = _quotes

    // Initialize the ViewModel by fetching a random quote
    init {
        fetchRandomQuote()
    }

    // Fetches a random quote based on the given category;
    // hardcoded to "inspirational" but could be changed by the user with different implementation
    private fun fetchRandomQuote() {
        viewModelScope.launch {
            try {
                // Fetch a random quote from the API
                val fetchedQuotes = RetrofitInstance.api.getRandomQuote("inspirational")

                // Update the mutable state with the fetched quotes
                _quotes.value = fetchedQuotes
            } catch (e: Exception) {
                // Log any errors that occur during the fetch
                Log.e("QuoteViewModel", "Error fetching quotes", e)
            }
        }
    }

    // Function tied to the "new quote" button; fetches a new random quote
    fun newQuote() {
        fetchRandomQuote()
    }
}
