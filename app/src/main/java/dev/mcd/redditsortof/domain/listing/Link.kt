package dev.mcd.redditsortof.domain.listing

data class Link(
    val author: String,
    val createdUtc: Long,
    val downs: Int,
    val isSelf: Boolean,
    val numComments: Int,
    val score: Int,
    val selfText: String,
    val title: String,
    val ups: Int,
)
