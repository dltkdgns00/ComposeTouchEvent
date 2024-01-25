package com.sl.touchevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sl.touchevent.ui.theme.TouchEventTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
Box(modifier = Modifier.width())
            }
        }
    }
}


@Composable
fun TouchRedirectComponent(onButtonClick: () -> Unit) {
        // 터치를 감지할 영역
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        if (isInDesiredRange(offset.x, offset.y)) {
                            // 터치 영역 내에서의 터치를 감지하면 버튼의 클릭 동작 실행
                            println("offset.x: ${offset.x}, offset.y: ${offset.y}")
                            onButtonClick()
                        }
                    }
                }
        ) {
            Text("터치 영역", modifier = Modifier.align(Alignment.Center))
        }

//        Spacer(modifier = Modifier.height(20.dp))

//        // 실제 버튼
//        Button(onClick = onButtonClick) {
//            Text("버튼")
//        }

}

fun isInDesiredRange(x: Float, y: Float): Boolean {
    val screenWidth = 1920f
    val screenHeight = 2160f

    val centerX = screenWidth / 2
    val centerY = screenHeight / 2

    // 탐지하려는 영역의 크기 (예: 50x50 픽셀)
    val detectionSize = 150f

    val minX = centerX - (detectionSize / 2)
    val maxX = centerX + (detectionSize / 2)

    val minY = centerY - (detectionSize / 2)
    val maxY = centerY + (detectionSize / 2)

    return (x in minX..maxX) && (y in minY..maxY)
}
