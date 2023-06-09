package manuelduarte077.dev.mynoteapp.android.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import manuelduarte077.dev.mynoteapp.domain.note.Note
import manuelduarte077.dev.mynoteapp.domain.note.NoteDao
import manuelduarte077.dev.mynoteapp.domain.time.DateTimeSupport
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDao: NoteDao, private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("isNoteTitleFocused", false)
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
    private val isContentFocused = savedStateHandle.getStateFlow("isNoteContentHintVisible", false)
    private val noteColor = savedStateHandle.getStateFlow("noteColor", Note.generateRandomColor())

    val state = combine(
        noteTitle, isNoteTitleFocused, noteContent, isContentFocused, noteColor
    ) { title, isTitleFocused, content, isContentFocused, color ->
        NoteDetailState(
            noteTitle = title,
            isNoteTitleHintVisible = title.isBlank() && !isTitleFocused,
            noteContent = content,
            isNoteContentHintVisible = content.isEmpty() && !isContentFocused,
            noteColor = color
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if (existingNoteId == -1L) {
                return@let
            }
            this.existingNoteId = existingNoteId

            viewModelScope.launch {
                noteDao.getNoteById(existingNoteId)?.let { note ->
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteContent"] = note.content
                    savedStateHandle["noteColor"] = note.colorHex

                }
            }
        }
    }

    fun onNoteTitleChange(text: String) {
        savedStateHandle["noteTitle"] = text
    }

    fun onNoteContentChange(content: String) {
        savedStateHandle["noteContent"] = content
    }

    fun onNoteTitleFocusChange(isFocused: Boolean) {
        savedStateHandle["isNoteTitleFocused"] = isFocused
    }

    fun onNoteContentFocusedChange(isFocused: Boolean) {
        savedStateHandle["isNoteContentHintVisible"] = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDao.insertNote(
                Note(
                    existingNoteId,
                    noteTitle.value,
                    noteContent.value,
                    noteColor.value,
                    DateTimeSupport.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }

}