package com.example.limitlesstech.limitlessnews.data.local.room.bookmark



import androidx.room.Entity
import androidx.room.PrimaryKey

//Ye class Room database me bookmarked news article save karne ke liye use hoti hai.

@Entity(tableName = "bookmarks")//it is the name of the table in the database where we will store the bookmarks
data class BookmarkEntity(//it is the sturucture of the data that we want to store in the database(Ek bookmark article ka structure)

    @PrimaryKey
    val id: String,//har article ki unique id hogi. same article do baar store na ho jaye isliye hum id ko primary key bana rahe hai

    val title: String,
    val description:String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val date: String,
    val link: String

)