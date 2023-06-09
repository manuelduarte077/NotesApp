package manuelduarte077.dev.mynoteapp.data.note

import database.NoteListTable
import kotlinx.datetime.Instant
import kotlinx.datetime.toLocalDateTime
import manuelduarte077.dev.mynoteapp.domain.note.Note

fun NoteListTable.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        createdAt = Instant.fromEpochMilliseconds(created)
            .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
    )
}