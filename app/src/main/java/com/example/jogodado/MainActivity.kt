package com.example.jogodado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jogodado.ui.theme.JogoDadoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JogoDadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF099f17),
                                        Color(0xFF024700)
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                )
                            )
                            .padding(innerPadding)
                    ) {
                        jogoDado()
                    }
                }
            }
        }
    }
}

@Composable
fun jogoDado() {
    val dice1 = remember { mutableStateOf(R.drawable.dado_1) }
    val dice2 = remember { mutableStateOf(R.drawable.dado_1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopText(text = "JOGO DO DADO")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = dice1.value),
                contentDescription = "Jogo 1",
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Image(
                painter = painterResource(id = dice2.value),
                contentDescription = "Jogo 2",
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
        }

        Button(
            onClick = {
                dice1.value = imagemAleatoria()
                dice2.value = imagemAleatoria()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Text(text = "Lançar Dados")
        }
    }
}

fun imagemAleatoria(): Int {
    val diceImages = listOf(
        R.drawable.dado_1,
        R.drawable.dado_2,
        R.drawable.dado_3,
        R.drawable.dado_4,
        R.drawable.dado_5,
        R.drawable.dado_6
    )
    return diceImages[Random.nextInt(diceImages.size)]
}

@Composable
fun TopText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JogoDadoTheme {
        jogoDado()
    }
}
