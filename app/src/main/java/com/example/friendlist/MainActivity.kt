package com.example.friendlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "friendList") {
                composable("friendList") {
                    FriendListScreen(navController, FriendsRepository.friends)
                }
                composable(
                    "friendDetail/{friendId}",
                    arguments = listOf(navArgument("friendId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val friendId = backStackEntry.arguments?.getInt("friendId")
                    val friend = FriendsRepository.friends.find { it.id == friendId }
                    friend?.let { FriendDetailScreen(it) }
                }
            }
        }
    }
}

data class Friend(
    val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val imageRes: Int
)


object FriendsRepository {
    val friends = listOf(
        Friend(1,"Ahmad Nugraha", "ahmad@gmail.com", "Jl. manunggal", R.drawable.friend1),
        Friend(2,"Angga Tirta Aditia", "anggatirta@gmail.com", "Jl. HR. Soebrantas ", R.drawable.friend2),
        Friend(3,"Apri Supandi Pasaribu", "aprisupandi@gmail.com", "Jl. Labersa", R.drawable.friend3),
        Friend(4,"Dendi Setiawan", "dendisetiawan@gmail.com", "Jl. swakarya", R.drawable.friend4),
        Friend(5,"Elfarina Fadma Sucitra", "elfarina@gmail.com", "Jl. manunggal", R.drawable.friend5),
        Friend(6,"Hariri Hasnul Habib", "habib@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend6),
        Friend(7,"Haziel Wisma Attar", "hazielattar@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend7),
        Friend(8,"Jelita Aurelia", "jelita@gmail.com", "Jl. HR. Soebrantas ", R.drawable.friend8),
        Friend(9,"M.Adli Ulhaq", "adli@gmail.com", "Jl. manunggal", R.drawable.friend9),
        Friend(10,"M.Lukman Hakim", "lukmanhakim@gmail.com", "Jl. swakarya", R.drawable.friend10),
        Friend(11,"M.Wildan Alif", "wildanalif@gmail.com", "Jl. swakarya", R.drawable.friend11),
        Friend(12,"Muhammad Zacky", "zacky@gmail.com", "Jl. manunggal", R.drawable.friend12),
        Friend(13,"Nazrul Ihsan", "ihsan@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend13),
        Friend(14,"Putri Maharani", "putrimaharani@gmail.com", "Jl. HR. Soebrantas ", R.drawable.friend14),
        Friend(15,"Rafif Haqqi", "rafif@gmail.com", "Jl. HR. Soebrantas ", R.drawable.friend15),
        Friend(16,"Rani Dwi Sopia", "ranidwi@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend16),
        Friend(17,"Rialin Ardiansyah", "rialin@gmail.com", "Jl. swakarya", R.drawable.friend17),
        Friend(18,"Rikenro Aldifo", "rikenro@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend18),
        Friend(19,"Zahra Anisa", "zahraanisa@gmail.com", "Jl. HR. Soebrantas ", R.drawable.friend19),
        Friend(20,"Zhafira Ramadhani", "zhafira@gmail.com", "Jl. Garuda Sakti Panam", R.drawable.friend20)

        )
}

@Composable
fun FriendListScreen(navController: NavController, friends: List<Friend>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Friend List",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
        )
        LazyColumn {
            items(friends) { friend ->
                FriendCard(friend) {
                    navController.navigate("friendDetail/${friend.id}")
                }
            }
        }
    }
}


@Composable
fun FriendCard(friend: Friend, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = friend.name, style = MaterialTheme.typography.bodyLarge)
            }
            Image(
                painter = painterResource(id = friend.imageRes),
                contentDescription = friend.name,
                modifier = Modifier.size(64.dp)
            )
        }

    }
}

@Composable
fun FriendDetailScreen(friend: Friend) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = friend.imageRes),
            contentDescription = friend.name,
            modifier = Modifier.size(350.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = friend.name, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = friend.email, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = friend.address, style = MaterialTheme.typography.bodyLarge)
            }
        }

    }
}

