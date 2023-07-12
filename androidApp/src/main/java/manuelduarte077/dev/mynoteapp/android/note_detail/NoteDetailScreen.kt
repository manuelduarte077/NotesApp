package manuelduarte077.dev.mynoteapp.android.note_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import manuelduarte077.dev.mynoteapp.android.note_list.NoteListViewModel
import manuelduarte077.dev.mynoteapp.android.redHatFont

@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: Long,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    viewNoteModel: NoteListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.hasNoteBeenSaved.collectAsState()

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if (hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (noteId == -1L) "New Note" else "Edit Note",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = redHatFont,
                            color = Color(0xffffffff)
                        )
                    )
                },
                backgroundColor = Color(0xff252525), elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Share Note",
                            tint = Color(0xffffffff),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Note",
                            tint = Color(0xffffffff),
                        )
                    }
                    IconButton(
                        onClick = {
                            viewNoteModel.deleteNoteById(noteId)
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Note",
                            tint = Color(0xffffffff)
                        )
                    }
                },
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::saveNote, backgroundColor = Color.Black,
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note",
                    tint = Color.White
                )
            }
        },
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(state.noteColor))
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                TransparentHintTextField(
                    text = state.noteTitle,
                    hint = "Enter a title...",
                    isHintVisible = state.isNoteTitleHintVisible,
                    onValueChange = viewModel::onNoteTitleChange,
                    onFocusChange = {
                        viewModel.onNoteTitleFocusChange(it.isFocused)
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = redHatFont,
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TransparentHintTextField(
                    text = state.noteContent,
                    hint = "Enter some Content...",
                    isHintVisible = state.isNoteContentHintVisible,
                    onValueChange = viewModel::onNoteContentChange,
                    onFocusChange = {
                        viewModel.onNoteContentFocusedChange(it.isFocused)
                    },
                    singleLine = false,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = redHatFont,
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }

}