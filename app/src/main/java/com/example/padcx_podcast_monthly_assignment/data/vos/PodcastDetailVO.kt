package com.example.padcx_podcast_monthly_assignment.data.vos

data class PodcastDetailVO(
    val country: String,
    val description: String,
    val earliest_pub_date_ms: Long,
    val email: String,
    val explicit_content: Boolean,
    val extra: ExtraVO,
    val genre_ids: List<Int>,
    val id: String,
    val image: String,
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_pub_date_ms: Long,
    val listennotes_url: String,
    val looking_for: LookingForVO,
    val publisher: String,
    val rss: String,
    val thumbnail: String,
    val title: String,
    val total_episodes: Int,
    val type: String,
    val website: String
)