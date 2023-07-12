package manuelduarte077.dev.mynoteapp.android.note_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import manuelduarte077.dev.mynoteapp.android.redHatFont
import manuelduarte077.dev.mynoteapp.domain.note.Note
import manuelduarte077.dev.mynoteapp.domain.time.DateTimeSupport

@Composable
fun NoteItem(
    note: Note,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(note.createdAt) {
        DateTimeSupport.formatNoteDate(note.createdAt)
    }

    Column(modifier = modifier
        .clip(RoundedCornerShape(5.dp))
        .background(backgroundColor)
        .clickable { onNoteClick() }
        .padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = redHatFont,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = note.content,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = redHatFont,
                fontSize = 16.sp,
            ),
            maxLines = 3, overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate,
            style = TextStyle(
                color = Color.DarkGray,
                fontFamily = redHatFont,
                fontSize = 14.sp,
            ),
            modifier = Modifier.align(Alignment.End),
        )
    }
}