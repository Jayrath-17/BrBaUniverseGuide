package com.example.breakingbaduniverse

class Blog {
    private var episode_Number: String? = null
    private var name: String? = null
    private var highlights: String? = null
    private var death: String? = null
    private var quote: String? = null
    private var poster: String? = null

    constructor(
        episode_Number: String?,
        name: String?,
        highlights: String?,
        death: String?,
        quote: String?,
        poster: String?
    ) {
        this.episode_Number = episode_Number
        this.name = name
        this.highlights = highlights
        this.death = death
        this.quote = quote
        this.poster = poster
    }

    constructor()


    fun getEpisode_Number(): String?
    {
      return episode_Number
    }

    fun getName(): String?
    {
     return name
    }

    fun getDeath(): String?
    {
     return death
    }

    fun getQuote(): String?
    {
        return quote
    }

    fun getPoster(): String?
    {
        return poster
    }

    fun getHighlights(): String? {
        return highlights
    }

}