package mypromotion.vboo.com.thelist.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "albumId") val albumId: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String?
)