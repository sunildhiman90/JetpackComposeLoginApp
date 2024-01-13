//
//  ContentView.swift
//  iosApp
//
//  Created by Sunil Kumar on 07/01/24.
//

import SwiftUI
import shared
import UIKit

//Connecting the compose multiplatform view to swiftui
struct ComposeView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
    
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
    }
}

#Preview {
    ContentView()
}
