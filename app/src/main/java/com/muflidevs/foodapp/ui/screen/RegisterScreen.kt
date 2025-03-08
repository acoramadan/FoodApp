package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.ui.design_system.AuthOption
import com.muflidevs.foodapp.ui.design_system.MyTextField
import com.muflidevs.foodapp.ui.theme.FoodAppTheme
import com.muflidevs.foodapp.ui.theme.orange

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.icon_app),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Register",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }
        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Name",
            leadingIcon = Icons.Outlined.AccountCircle,
            trailingIcon = Icons.Outlined.Check,
            modifier = Modifier.fillMaxWidth(),
            isPassword = false
        )
        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
            isPassword = false
        )
        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            modifier = Modifier.fillMaxWidth(),
            isPassword = true
        )
        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Phone number",
            leadingIcon = Icons.Outlined.Phone,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Phone,
            modifier = Modifier.fillMaxWidth(),
            isPassword = false
        )
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Register",
                fontSize = 17.sp,
                color = orange,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row (
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Text(
                text = "Have an account?",
                fontSize = 15.sp
            )
            Text(
                text = "Login",
                fontSize = 16.sp,
                color = orange,
                modifier = Modifier
                    .clickable {
                        navController?.navigate("login")
                    }
            )
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
@Preview( showBackground = true)
@Composable
fun RegisterScreenPreview(modifier: Modifier = Modifier) {
    FoodAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            RegisterScreen(modifier.padding(innerPadding))
        }
    }
}