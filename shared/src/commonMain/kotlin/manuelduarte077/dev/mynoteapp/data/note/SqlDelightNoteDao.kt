package manuelduarte077.dev.mynoteapp.data.note

import manuelduarte077.dev.mynoteapp.database.NoteDatabase
import manuelduarte077.dev.mynoteapp.domain.note.Note
import manuelduarte077.dev.mynoteapp.domain.note.NoteDao
import manuelduarte077.dev.mynoteapp.domain.time.DateTimeSupport

class SqlDelightNoteDao(
    noteDb: NoteDatabase
) : NoteDao {
    private val queries = noteDb.noteQueries
    override suspend fun insertNote(note: Note) {
        queries.addNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeSupport.toEpochMilli(note.createdAt)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNotesById(id)
            .executeAsOneOrNull()
            ?.toNote()

    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map {
                it.toNote()
            }
    }

    override suspend fun deleteNote(id: Long) {
        queries.deleteNote(id)
    }
}