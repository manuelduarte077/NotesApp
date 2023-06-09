package manuelduarte077.dev.mynoteapp.di

import manuelduarte077.dev.mynoteapp.data.factories.DbDriverFactory
import manuelduarte077.dev.mynoteapp.data.note.SqlDelightNoteDao
import manuelduarte077.dev.mynoteapp.database.NoteDatabase
import manuelduarte077.dev.mynoteapp.domain.note.NoteDao

class DatabaseModule {

    private val factory by lazy { DbDriverFactory() }
    val noteDao: NoteDao by lazy {
        SqlDelightNoteDao(NoteDatabase(factory.createDriver()))
    }

}