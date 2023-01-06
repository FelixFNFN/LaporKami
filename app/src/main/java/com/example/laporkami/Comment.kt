package com.example.laporkami

data class Comment(
    var id:Long,
    var id_laporan:Long,
    var id_user:Long,
    var comment:String
) {
}