package manuelduarte077.dev.mynoteapp.data.factories

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import manuelduarte077.dev.mynoteapp.database.NoteDatabase

actual class DbDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NoteDatabase.Schema, "note.db")
    }
}