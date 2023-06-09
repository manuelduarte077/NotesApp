package manuelduarte077.dev.mynoteapp.domain.note

import kotlinx.datetime.LocalDateTime
import manuelduarte077.dev.mynoteapp.utils.BabyBlueHex
import manuelduarte077.dev.mynoteapp.utils.LightGreenHex
import manuelduarte077.dev.mynoteapp.utils.RedOrangeHex
import manuelduarte077.dev.mynoteapp.utils.RedPinkHex
import manuelduarte077.dev.mynoteapp.utils.VioletHex

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}