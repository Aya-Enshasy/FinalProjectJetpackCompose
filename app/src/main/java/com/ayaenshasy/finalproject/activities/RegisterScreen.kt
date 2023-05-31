package com.ayaenshasy.finalproject.activities

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.viewModel.LoginViewModel


@Composable
fun RegisterScreen(navController: NavController, loginViewModel: LoginViewModel) {
    Scaffold() {
        var name by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var phone by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
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
        )
        {

            Image(
                painter = painterResource(id = R.drawable.back),
                "",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .padding(top = 30.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

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
                        value = name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 16.dp, end = 16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Name"
                            )
                        },
                        onValueChange = { name = it },
                        label = { Text(text = "Name") },
                        placeholder = { Text(text = "Enter your Name") },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = email,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email"
                            )
                        },
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        placeholder = { Text(text = "Enter your Email") },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = phone,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "phone"
                            )
                        },
                        onValueChange = { phone = it },
                        label = { Text(text = "Phone Number") },
                        placeholder = { Text(text = "Enter your Phone Number") },
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

                    var isChecked by remember { mutableStateOf(false) }

                    Row(modifier = Modifier.padding(start = 5.dp)) {
                        Checkbox(
                            checked = isChecked,
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(id = R.color.blue),
                                uncheckedColor = colorResource(id = R.color.light_blue),
                                checkmarkColor = colorResource(id = R.color.white)
                            ),
                            onCheckedChange = { isChecked = it }
                        )
                        Row() {
                            Text(
                                text = "I Read and Accept",
                                modifier = Modifier.padding(top = 15.dp)
                            )

                            Text(
                                text = " Home Service Terms & Conditions",
                                modifier = Modifier.padding(top = 15.dp),
                                colorResource(id = R.color.blue)
                            )
                        }
                    }


                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 16.dp)
                        .clickable {
                            navController.popBackStack()
                        }) {
                        Column() {
                            Text(
                                text = "Have Account?",
                                color = colorResource(id = R.color.black),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 30.dp),

                                )
                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "SIGN IN",
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
                                loginViewModel.register(
                                    name.toString(),
                                    email.toString(),
                                    phone.toString(),
                                    password.toString(),
                                    navController,
                                    context
                                )


                            })
                        {
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
                                Text(text = "Sign Up", color = Color.White)
                            }
                        }

                    }
                }


            }
        }
    }
}
