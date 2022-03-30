package com.byjusexamprep.hackathon.ui.composables.home_screen

import android.os.CountDownTimer
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.byjusexamprep.hackathon.R
import com.byjusexamprep.hackathon.ui.composables.common.GradientButton
import com.byjusexamprep.hackathon.ui.theme.Color_000000
import com.byjusexamprep.hackathon.ui.theme.Color_272D41
import com.byjusexamprep.hackathon.ui.theme.Color_333333
import com.byjusexamprep.hackathon.utils.Constants

@Composable
fun MainScreenComposable(
    navController: NavController,
    viewModel: MainViewModel
) {

    LaunchedEffect(key1 = true) {
        viewModel.buildTeamMap()
    }

    val randomSquad = viewModel.randomSquadFlow.collectAsState()
    val randomMember = viewModel.randomMemberFlow.collectAsState()
    val matchFlow = viewModel.matchFlow.collectAsState()
    val newText = viewModel.newText.collectAsState()
    val score = viewModel.score.collectAsState()
    val remainingAttempts = viewModel.remainingAttempts.collectAsState()
    val openDialogCustom = remember { mutableStateOf(false) }

    //change state
    val buttonState = rememberSaveable {
        mutableStateOf(true)
    }

    if (remainingAttempts.value == 0) {
        openDialogCustom.value = true
    }

    if (openDialogCustom.value) {
        CustomDialog(openDialogCustom = openDialogCustom, score, viewModel)
    }


    Box(modifier = Modifier.background(Color_272D41)) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = "Welcome to Match Tank! ",
                fontSize = 32.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.W700,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
                    .wrapContentSize()
            ) {

                SlotsComposables(randomSquad.value, randomMember.value, newText)

                Spacer(modifier = Modifier.height(12.dp))

                GradientButton(
                    text = "Spin",
                    textColor = Color.White,
                    disabledTextColor = Color.Gray,
                    isEnabled = buttonState.value,
                    gradient = Constants.primaryGradientBrush,
                    disabledGradient = Constants.disabledButtonBrush,
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                ) {
                    val timer = object : CountDownTimer(1000, 50) {
                        override fun onTick(millisUntilFinished: Long) {
                            viewModel.generateRandom()
                        }

                        override fun onFinish() {
                            viewModel.checkIfItsAMatchV1()
                        }
                    }
                    timer.start()
                }
            }


            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Score: ${score.value}/5",
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.W700,
                )

            }

        }

        MatchTextComposable(matchFlow)
    }

}

@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    score: State<Int>,
    viewModel: MainViewModel
) {
    Dialog(onDismissRequest = {
        openDialogCustom.value = false
        viewModel.clearAttempts()
    }) {
        CustomDialogUI(score)
    }
}


@Composable
fun CustomDialogUI(score: State<Int>) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.height(200.dp),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Column(
            Modifier.background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.game_over),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

                )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your score was ${score.value}",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.W500,
            )


        }
    }
}


@Composable
fun MatchTextComposable(matchFlow: State<Boolean>) {
    if (matchFlow.value) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.conf)
        )


        Column {
            LottieAnimation(
                composition,
                iterations = LottieConstants.IterateForever,
                clipSpec = LottieClipSpec.Progress(0.5f, 0.75f),
            )

            Text(
                text = "Its a match!!",
                fontSize = 32.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left,
                color = Color_333333,
                fontWeight = FontWeight.W700,
            )
        }


    }
}

@Composable
fun SlotsComposables(
    squadName: String,
    memberName: String,
    newText: State<Boolean>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OneSlotComposable(true, squadName, newText)
        Spacer(modifier = Modifier.width(12.dp))
        OneSlotComposable(false, memberName, newText)
    }

}

@Composable
fun OneSlotComposable(
    isSquadSlot: Boolean,
    textToShow: String,
    visible: State<Boolean>
) {

    val text = if (isSquadSlot) "Squad" else "Member"

    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(150.dp)
            .height(50.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White
    ) {

        if (visible.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color_000000,
                    fontWeight = FontWeight.W500
                )

                Text(
                    text = textToShow,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color_000000,
                    fontWeight = FontWeight.W700
                )

            }
        }

    }

}
