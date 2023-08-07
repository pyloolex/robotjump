package com.volganl64.robotjump

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.volganl64.robotjump.ui.theme.RobotJumpTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent {
        //     RobotJumpTheme {
        //         // A surface container using the 'background' color from the theme
        //         Surface(
        //             modifier = Modifier.fillMaxSize(),
        //             color = MaterialTheme.colorScheme.background
        //         ) {
        //             Greeting("robot")
        //         }
        //     }
        // }
        setContent {
            RobotJumpTheme {
                Surface(modifier=Modifier.fillMaxSize()) {
                    MessageCard(Message("Bill", "Hello, what are you up to?"))
                }
            }
        }
    }
}


data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message)
{
    Row(modifier=Modifier.padding(all=8.dp)) {
        Image(
            painter=painterResource(R.drawable.robotimage),
            contentDescription="Robot",
            modifier=Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier=Modifier.width(8.dp))

        Column {
            Text(
                text=msg.author,
                color=MaterialTheme.colorScheme.secondary,
                style=MaterialTheme.typography.titleSmall,
            )
            Spacer(modifier=Modifier.height(4.dp))
            Surface(shape=MaterialTheme.shapes.medium,
                    shadowElevation=1.dp) {
                Text(
                    text=msg.body,
                    modifier=Modifier.padding(all=4.dp),
                    style=MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}


@Preview(name="Light Mode")
@Preview(name="Dark Mode",
         uiMode=Configuration.UI_MODE_NIGHT_YES,
         showBackground=true)
@Composable
fun MessageExample()
{
    RobotJumpTheme {
        Surface {
            MessageCard(Message("Jack", "Running errands."))
        }
    }
}


// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
//     Text(
//         text = "Hello $name!",
//         modifier = modifier
//     )
// }

// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//     RobotJumpTheme {
//         Greeting("Android")
//     }
// }
