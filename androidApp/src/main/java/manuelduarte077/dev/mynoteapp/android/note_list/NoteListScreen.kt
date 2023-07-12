package manuelduarte077.dev.mynoteapp.android.note_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
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
import manuelduarte077.dev.mynoteapp.android.redHatFont

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController, viewModel: NoteListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Notes", style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = redHatFont,
                            color = Color(0xffffffff)
                        )
                    )
                },
                backgroundColor = Color(0xff252525), elevation = 0.dp,
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Add Note",
                            tint = Color(0xffffffff),
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Add Note",
                            tint = Color(0xffffffff)
                        )
                    }
                },
            )

        },

        floatingActionButton = {
            FloatingActionButton(

                onClick = {

                    navController.navigate("note_detail/-1L")
                },
                modifier = Modifier
                    .padding(0.dp)
                    .size(60.dp),
                backgroundColor = Color(0xff252525),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note",
                    tint = Color.White
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                contentAlignment = Alignment.CenterStart,
//                propagateMinConstraints = true
//            ) {
//
//                HideableTextField(
//                    text = state.searchText,
//                    isSearchActive = state.isSearchActive,
//                    onTextChange = viewModel::onSearchTextDataChange,
//                    onSearchClick = viewModel::onToggleSearch,
//                    onCloseClick = viewModel::onToggleSearch,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(90.dp)
//                )
//
//                this@Column.AnimatedVisibility(
//                    visible = !state.isSearchActive, enter = fadeIn(), exit = fadeOut()
//                ) {
//                    Text(
//                        text = "All Notes",
//                        style = TextStyle(
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Bold,
//                            fontFamily = redHatFont,
//                        )
//
//                    )
//                }
//            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(items = state.notes, key = {
                    it.id!!
                }) { note ->
                    NoteItem(
                        note = note, backgroundColor = Color(note.colorHex), onNoteClick = {
                            navController.navigate("note_detail/${note.id}")
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 20.dp,
                                bottom = 10.dp
                            )
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}