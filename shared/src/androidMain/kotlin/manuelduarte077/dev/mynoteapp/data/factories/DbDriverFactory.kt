package manuelduarte077.dev.mynoteapp.data.factories

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import manuelduarte077.dev.mynoteapp.database.NoteDatabase

actual class DbDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NoteDatabase.Schema, context = context, name = "note.db")
    }
}