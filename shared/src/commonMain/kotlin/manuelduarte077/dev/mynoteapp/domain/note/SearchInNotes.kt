package manuelduarte077.dev.mynoteapp.domain.note

import manuelduarte077.dev.mynoteapp.domain.time.DateTimeSupport

class SearchInNotes {
    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeSupport.toEpochMilli(it.createdAt)
        }
    }
}