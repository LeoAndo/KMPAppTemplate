package jp.ac.jec.cm0199.kmpapptemplate

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPAppTemplate",
    ) {
        App()
    }
}