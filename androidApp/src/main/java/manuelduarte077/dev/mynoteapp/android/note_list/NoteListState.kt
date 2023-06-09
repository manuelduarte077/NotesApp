package manuelduarte077.dev.mynoteapp.android.note_list

import manuelduarte077.devs.mynoteapp.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false

)

