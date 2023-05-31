package com.ayaenshasy.finalproject.activities


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.viewModel.LoginViewModel


@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(
                            R.color.blue
                        ), colorResource(R.color.light_blue)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            "",
            modifier = Modifier
                .width(130.dp)
                .height(130.dp)
                .padding(top = 70.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
        ) {


            Column(modifier = Modifier.fillMaxSize()) {

                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Customer",
                            color = colorResource(id = R.color.blue),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 30.dp),
                        )
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .width(15.dp)
                                .height(3.dp),
                            colorResource(id = R.color.blue)
                        )
                    }
                    Text(
                        text = "Service provider", color = Color.Gray, fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)

                        )
                }

                OutlinedTextField(
                    value = email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "email"
                        )
                    },
                    onValueChange = {
                        email = it
                    },
                    label = { Text(text = "Email") },
                    placeholder = { Text(text = "Enter your Email") },
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Enter your Password") },
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 16.dp)
                ) {

                    CheckboxExample()

                    Text(
                        text = "Forgot Password?", color = Color.Black, fontSize = 16.sp,
                        textAlign = TextAlign.End, modifier = Modifier.padding(top = 15.dp)

                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, end = 16.dp)
                    .clickable {
                        navController.navigate("signup_screen")
                    }) {
                    Column() {
                        Text(
                            text = "New Member?",
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(horizontal = 30.dp),

                            )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "SIGN UP",
                            color = colorResource(id = R.color.blue),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(horizontal = 30.dp),
                            style = TextStyle(textDecoration = TextDecoration.Underline)

                        )

                    }
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        contentPadding = PaddingValues(),

                        onClick = {
                            if (email.equals("")){
                                Toast.makeText(context,"email is empty",Toast.LENGTH_LONG).show();
                            }else if (password.equals("")){
                                Toast.makeText(context,"password is empty",Toast.LENGTH_LONG).show();
                            }else{
                                loginViewModel.login(email, password, navController, context)
                            }

                        }) {
                        Box(

                            modifier = Modifier
                                .height(48.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            colorResource(id = R.color.light_blue),
                                            colorResource(id = R.color.blue),
                                        )
                                    ),
                                )
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Login", color = Color.White)
                        }
                    }
                }

            }


        }
    }


}

@Preview
@Composable
fun CheckboxExample() {
    var isChecked by remember { mutableStateOf(false) }

    Row() {
        Checkbox(
            checked = isChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.blue),
                uncheckedColor = colorResource(id = R.color.light_blue),
                checkmarkColor = colorResource(id = R.color.white)
            ),
            onCheckedChange = { isChecked = it }
        )
        Text(text = "Remember me?", modifier = Modifier.padding(top = 15.dp))
    }
}
