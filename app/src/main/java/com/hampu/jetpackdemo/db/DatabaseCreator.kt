package com.hampu.jetpackdemo.db

import com.hampu.jetpackdemo.db.model.User
import java.util.*

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
class DatabaseCreator {

    private val firstName = arrayOf("Noah", "Emma", "Liam", "Olivia", "William", "Ava", "Mason", "Sophia", "James", "Isabella", "Benjamin", "Mia", "Jacob", "Charlotte", "Michael", "Abigail", "Elijah", "Emily", "Ethan", "Harper")

    private val street = arrayOf("High Street", "Station Road", "Main Street", "Park Road", "Church Road", "Church Street", "London Road", "Victoria Road", "Green Lane", "The Avenue", "The Crescent", "Queens Road", "New Road", "Grange Road", "Kings Road", "Kingsway", "Windsor Road", "Highfield Road", "Mill Lane", "Alexander Road", "York Road", "St. John’s Road", "Manor Road", "Church Lane", "Park Avenue")

    private val city = arrayOf("Ashland", "Aspen", "Atascadero", "Athens", "Atlanta", "Auburn", "Austin", "Ayer", "Babylon", "Bainbridge")

    private val state = arrayOf("New Hampshire", "New Jersey", "New Mexico", "New York")

    val randomUserList: List<User>
        get() {
            val users = arrayListOf<User>()
            var tempUser: User
            val cityRange = city.size
            val stateRange = state.size
            val nameRange = firstName.size
            val streetRange = street.size
            var address: String
            val random = Random()
            for (i in 1..200) {
                address = state[random.nextInt(stateRange)] + "," + city[random.nextInt(cityRange)] + "," +
                        random.nextInt(99999).toString() + "," + street[random.nextInt(streetRange)]
                tempUser = User()
                tempUser.address = address
                tempUser.firstName = firstName[random.nextInt(nameRange)]
                users.add(tempUser)
            }
            return users
        }
}