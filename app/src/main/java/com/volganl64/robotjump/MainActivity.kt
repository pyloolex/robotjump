package com.volganl64.robotjump

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.volganl64.robotjump.ui.theme.RobotJumpTheme


/**
 * SampleData for Jetpack Compose Tutorial
 */
// object SampleData {
//     // Sample conversation data
//     val conversationSample = listOf(
//         Message(
//             "Lexi",
//             "Test...Test...Test..."
//         ),
//         Message(
//             "Lexi",
//             """List of Android versions:
//             |Android KitKat (API 19)
//             |Android Lollipop (API 21)
//             |Android Marshmallow (API 23)
//             |Android Nougat (API 24)
//             |Android Oreo (API 26)
//             |Android Pie (API 28)
//             |Android 10 (API 29)
//             |Android 11 (API 30)
//             |Android 12 (API 31)""".trim()
//         ),
//         Message(
//             "Lexi",
//             """I think Kotlin is my favorite programming language.
//             |It's so much fun!""".trim()
//         ),
//         Message(
//             "Lexi",
//             "Searching for alternatives to XML layouts..."
//         ),
//         Message(
//             "Lexi",
//             """Hey, take a look at Jetpack Compose, it's great!
//             |It's the Android's modern toolkit for building native UI.
//             |It simplifies and accelerates UI development on Android.
//             |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
//         ),
//         Message(
//             "Lexi",
//             "It's available from API 21+ :)"
//         ),
//         Message(
//             "Lexi",
//             "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
//         ),
//         Message(
//             "Lexi",
//             "Android Studio next version's name is Arctic Fox"
//         ),
//         Message(
//             "Lexi",
//             "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
//         ),
//         Message(
//             "Lexi",
//             "I didn't know you can now run the emulator directly from Android Studio"
//         ),
//         Message(
//             "Lexi",
//             "Compose Previews are great to check quickly how a composable layout looks like"
//         ),
//         Message(
//             "Lexi",
//             "Previews are also interactive after enabling the experimental setting"
//         ),
//         Message(
//             "Lexi",
//             "Have you tried writing build.gradle with KTS?"
//         ),
//     )
// }


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
            DrawRobotLayout()
        }
    }
}


val MENU_WIDTH = 80.dp
val DEFAULT_MARGIN = 8.dp


@Preview
@Composable
fun DrawRobotLayout()
{
    RobotJumpTheme {
        Box(Modifier.background(Color.Cyan).fillMaxSize()/*.width(300.dp).height(600.dp)*/) {
            Column {
                Box(Modifier.background(Color.Red).fillMaxWidth().height(50.dp)) {
                    Box(Modifier.padding(DEFAULT_MARGIN).background(Color.Yellow)
                            .fillMaxSize())
                }

                Box(Modifier.background(Color.Magenta).weight(1f)
                        .fillMaxWidth()) {
                    Row {
                        Box(Modifier.background(Color.Green).fillMaxHeight()
                                .width(MENU_WIDTH)) {
                            Column {
                                Box(Modifier.padding(DEFAULT_MARGIN).background(Color.Gray)
                                        .fillMaxWidth().height(30.dp))
                                Box(Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                                        .background(Color.DarkGray).fillMaxWidth().height(30.dp)
                                )
                                Spacer(Modifier.weight(1f))
                                Button(
                                    {},
                                    Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                                        .fillMaxWidth().aspectRatio(1f),
                                    shape=RoundedCornerShape(5),
                                ) {}
                            }
                        }
                        Box(Modifier.background(Color.Blue).fillMaxHeight()
                                .weight(1f)) {
                        }
                    }
                }

                Box(Modifier.background(Color.LightGray).height(MENU_WIDTH)
                        .fillMaxWidth()) {
                    Row {
                        Button(
                            {},
                            Modifier.padding(DEFAULT_MARGIN)
                                .fillMaxHeight().aspectRatio(1f),
                            shape=RoundedCornerShape(5),
                        ) {}
                        Button(
                            {},
                            Modifier.padding(top=DEFAULT_MARGIN, bottom=DEFAULT_MARGIN)
                                .fillMaxHeight().aspectRatio(1f),
                            shape=RoundedCornerShape(5),
                        ) {}
                        Spacer(Modifier.weight(1f))
                        Button(
                            {},
                            Modifier.padding(top=DEFAULT_MARGIN, bottom=DEFAULT_MARGIN)
                                .fillMaxHeight().aspectRatio(1f),
                            shape=RoundedCornerShape(5),
                        ) {}
                        Button(
                            {},
                            Modifier.padding(DEFAULT_MARGIN)
                                .fillMaxHeight().aspectRatio(1f),
                            shape=RoundedCornerShape(5),
                        ) {}

                    }
                }
            }
        }
    }
}

// data class Message(val author: String, val body: String)


// @Composable
// fun MessageCard(msg: Message)
// {
//     Row(modifier=Modifier.padding(all=8.dp)) {
//         Image(
//             painter=painterResource(R.drawable.robotimage),
//             contentDescription="Robot",
//             modifier=Modifier
//                 .size(40.dp)
//                 .clip(CircleShape)
//                 .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//         )

//         Spacer(modifier=Modifier.width(8.dp))

//         var isExpanded by remember { mutableStateOf(false) }
//         val surfaceColor by animateColorAsState(
//             if (isExpanded) MaterialTheme.colorScheme.primary
//             else MaterialTheme.colorScheme.surface
//         )

//         Column(modifier=Modifier.clickable { isExpanded = !isExpanded }) {
//             Text(
//                 text=msg.author,
//                 color=MaterialTheme.colorScheme.secondary,
//                 style=MaterialTheme.typography.titleSmall,
//             )
//             Spacer(modifier=Modifier.height(4.dp))
//             Surface(shape=MaterialTheme.shapes.medium,
//                     shadowElevation=1.dp,
//                     color=surfaceColor,
//                     modifier=Modifier.animateContentSize().padding(1.dp)
//             ) {
//                 Text(
//                     text=msg.body,
//                     modifier=Modifier.padding(all=4.dp),
//                     style=MaterialTheme.typography.bodyMedium,
//                     maxLines=if (isExpanded) Int.MAX_VALUE else 1,
//                 )
//             }
//         }
//     }
// }


// @Composable
// fun Conversation(messages: List<Message>)
// {
//     LazyColumn {
//         items(messages) { message ->
//             MessageCard(message)
//         }
//     }

// }


// @Preview(name="Light Mode")
// @Preview(name="Dark Mode",
//          uiMode=Configuration.UI_MODE_NIGHT_YES,
//          showBackground=true)
// @Composable
// fun MessageExample()
// {
//     RobotJumpTheme {
//         Surface {
//             Conversation(SampleData.conversationSample)
//         }
//     }
// }





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
