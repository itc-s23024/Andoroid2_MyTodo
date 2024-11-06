package jp.ac.it_college.std.s23024.mytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.it_college.std.s23024.mytodo.deta.Item
import jp.ac.it_college.std.s23024.mytodo.ui.theme.MyTodoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTodoTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    val app = application as TodoApplication
                    val db = app.container.itemsRepository
                    val item = Item(
                        title = "牛乳を買う",
                        description = "2カートン",
                        done = true
                    )
                    Row(
                        modifier = Modifier.safeDrawingPadding()
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    db.insertItem(item)
                                }
                            }
                        ) {
                            Text(text = "データをインサート")
                        }
                    }
                }
            }
        }
    }
}

