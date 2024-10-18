package com.ag.projects.shamsstorecompose.presentation.components.otp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    itemCount: Int,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Gray,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(4.dp),
    onOtpComplete: (String) -> Unit
) {
    var otpValues by remember { mutableStateOf(List(itemCount) { "" }) }
    val focusRequesters = remember { List(itemCount) { FocusRequester() } }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(itemCount) { index ->
            OutlinedTextField(
                value = otpValues[index],
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() || newValue.isEmpty() }) {
                        // Ensure previous fields are filled before allowing to move forward
                        if (index == 0 || otpValues.subList(0, index).all { it.isNotEmpty() }) {
                            otpValues = otpValues.toMutableList().apply { this[index] = newValue }

                            // Move to the next field if a number is entered
                            if (newValue.isNotEmpty() && index < itemCount - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }

                            // Move to the previous field when deleting
                            if (newValue.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }

                            // Check if OTP is complete
                            if (otpValues.all { it.isNotEmpty() }) {
                                onOtpComplete(otpValues.joinToString(""))
                            }
                        }
                    }
                },
                textStyle = LocalTextStyle.current.copy(
                    color = textColor,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None,
                modifier = Modifier
                    .size(65.dp)
                    .background(backgroundColor)
                    .border(BorderStroke(borderWidth, borderColor), shape)
                    .focusRequester(focusRequesters[index]),
                shape = shape,
                maxLines = 1
            )

            // Auto focus the first field
//            LaunchedEffect(Unit) {
//                if (index == 0) {
//                    focusRequesters[index].requestFocus()
//                }
//            }
        }
    }
}