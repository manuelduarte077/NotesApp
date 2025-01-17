//
//  NotesAppApp.swift
//  NotesApp
//
//  Created by Manuel Duarte on 24/10/24.
//

import SwiftUI

@main
struct NotesAppApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                /// Setting Min Frame
                .frame(minWidth: 320, minHeight: 400)
        }
        .windowResizability(.contentSize)
        /// Adding Data Model to the App
        .modelContainer(for: [Note.self, NoteCategory.self])
    }
}
