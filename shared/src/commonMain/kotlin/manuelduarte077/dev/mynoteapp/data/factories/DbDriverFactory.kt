package manuelduarte077.dev.mynoteapp.data.factories

import com.squareup.sqldelight.db.SqlDriver

expect class DbDriverFactory {
    fun createDriver(): SqlDriver
}