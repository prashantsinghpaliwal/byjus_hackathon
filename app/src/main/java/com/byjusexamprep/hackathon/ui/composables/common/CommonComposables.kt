package com.byjusexamprep.hackathon.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.byjusexamprep.hackathon.ui.theme.Color_333333

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    disabledTextColor: Color,
    isEnabled: Boolean,
    gradient: Brush,
    disabledGradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        val buttonBg: Brush
        val tColor: Color
        if (isEnabled) {
            buttonBg = gradient
            tColor = textColor
        } else {
            buttonBg = disabledGradient
            tColor = disabledTextColor
        }

        Box(
            modifier = Modifier
                .background(buttonBg)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = tColor,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500
            )
        }
    }
}