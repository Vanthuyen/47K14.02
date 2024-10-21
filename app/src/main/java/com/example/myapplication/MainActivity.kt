//package com.example.myapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.myapplication.ui.theme.MyApplicationTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Thuyên",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}
package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class CounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    // Biến trạng thái lưu trữ số lần bấm
    var count by remember { mutableStateOf(0) }

    // Sử dụng Box để căn giữa nội dung
    Box(
        modifier = modifier
            .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
            .padding(16.dp), // Padding cho Box
        contentAlignment = Alignment.Center // Căn giữa nội dung trong Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử trong Column
        ) {
            // Hiển thị số lần bấm
            Text(
                text = "Bạn đã bấm nút $count lần.",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Nút bấm để tăng bộ đếm
            Button(onClick = { count++ }) {
                Text(text = "Bấm")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    MyApplicationTheme {
        CounterApp()
    }
}

