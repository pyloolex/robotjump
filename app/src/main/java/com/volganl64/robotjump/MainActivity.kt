package com.volganl64.robotjump

import android.os.Bundle
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
            MessageCard(Message("Bill", "Hello, what are you up to?"))
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
        )

        Spacer(modifier=Modifier.width(8.dp))

        Column {
            Text(text=msg.author)
            Spacer(modifier=Modifier.height(4.dp))
            Text(text=msg.body)
        }
    }
}


@Preview
@Composable
fun MessageExample()
{
    MessageCard(Message("Jack", "Running errands."))
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
