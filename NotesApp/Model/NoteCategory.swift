//
//  NoteCategory.swift
//  NotesApp
//
//  Created by Manuel Duarte on 24/10/24.
//

import SwiftUI
import SwiftData

@Model
class NoteCategory {
    var categoryTitle: String
    /// Relationship
    @Relationship(deleteRule: .cascade, inverse: \Note.category)
    var notes: [Note]?
    
    init(categoryTitle: String) {
        self.categoryTitle = categoryTitle
    }
}
