package com.codingambitions.jetpackcomposeloginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingambitions.jetpackcomposeloginapp.data.LoginRepository
import com.codingambitions.jetpackcomposeloginapp.data.LoginViewModel
import com.codingambitions.jetpackcomposeloginapp.ui.theme.JetpackComposeLoginAppTheme
import com.codingambitions.loginapp.shared.Greeting


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLoginAppTheme {

                val loginRepository = LoginRepository()
                val loginViewModel = LoginViewModel(loginRepository)

                val isLoggedIn = loginViewModel.isLoggedIn.collectAsState()
                val user = loginViewModel.user.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var username by remember {
                            mutableStateOf("")
                        }
                        var password by remember {
                            mutableStateOf("")
                        }
                        var isValid by remember {
                            mutableStateOf(true)
                        }
                        Text(text = "Login", style = MaterialTheme.typography.displaySmall)
                        Spacer(modifier = Modifier.height(36.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username, onValueChange = {
                                username = it
                            },
                            label = { Text(text = "Username") },
                            visualTransformation = VisualTransformation.None
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = { Text(text = "Password") },
                            visualTransformation = PasswordVisualTransformation(),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            onClick = {
                                isValid = username.isNotEmpty() && password.isNotEmpty()
                                if (isValid) {
                                    loginViewModel.doLogin()
                                }
                            }) {
                            Text(text = "Login")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimatedVisibility(visible = isLoggedIn.value) {
                            Text(
                                text = "Login Successful for: ${user.value?.name}",
                                color = Color.Green.copy(alpha = 0.8f, green = 0.8f),
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                        AnimatedVisibility(visible = !isValid) {
                            Text(
                                text = "Invalid email or password",
                                color = Color.Red,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeLoginAppTheme {
        Greeting("Android")
    }
}