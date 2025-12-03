package jp.ac.jec.cm0199.kmpapptemplate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpapptemplate.composeapp.generated.resources.Res
import kmpapptemplate.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        // 再構成および構成の変更後も UI のトグルとカウンターを維持する
        var showContent by rememberSaveable { mutableStateOf(false) }
        var count by rememberSaveable { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("count: $count")
            Button(onClick = {
                showContent = !showContent
                count++
            }) {
                Text("Click me!")
            }
            // 切り替えるとプラットフォーム固有の挨拶が表示される
            AnimatedVisibility(showContent) {
                val greeting = Greeting().greet()
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
