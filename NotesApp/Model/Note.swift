//
//  Note.swift
//  NotesApp
//
//  Created by Manuel Duarte on 24/10/24.
//

import SwiftUI
import SwiftData

@Model
class Note {
    var content: String
    var isFavourite: Bool = false
    var category: NoteCategory?
    
    init(content: String, category: NoteCategory? = nil) {
        self.content = content
        self.category = category
    }
}
