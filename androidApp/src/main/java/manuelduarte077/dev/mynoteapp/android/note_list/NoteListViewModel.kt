package manuelduarte077.dev.mynoteapp.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import manuelduarte077.dev.mynoteapp.domain.note.NoteDao
import manuelduarte077.dev.mynoteapp.domain.note.Note
import manuelduarte077.dev.mynoteapp.domain.note.SearchInNotes
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDao: NoteDao,
    private val savedStateHande: SavedStateHandle
) : ViewModel() {
    private val searchNote = SearchInNotes()
    private val notes = savedStateHande.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHande.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHande.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNote.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(50000), NoteListState())

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHande["notes"] = noteDao.getAllNotes()
        }
    }

    fun onSearchTextDataChange(text: String) {
        savedStateHande["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHande["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHande["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDao.deleteNote(id)
            loadNotes()
        }
    }

}

