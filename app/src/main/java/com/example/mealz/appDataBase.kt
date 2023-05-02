package com.example.mealz

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Restaurant::class, MenuItem::class,User::class,UserCart::class], exportSchema = false,version = 1)
    abstract class appDataBase : RoomDatabase() {
    abstract fun getRestaurantDao(): restaurantDAO
    abstract fun getMenuItemDAO(): MenuItemDAO
    abstract fun getUserEItemDAO(): UserDAO
    abstract fun getUserCartDAO(): UserCartDAO



    companion object {
        private var INSTANCE: appDataBase? = null
        fun buildDatabase(context: Context): appDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    appDataBase::class.java,
                    "food_db"
                ).allowMainThreadQueries().build()
                // initialize the database
                this.initializeDb(INSTANCE!!)


            }

            return INSTANCE
        }

        private fun initializeDb(INSTANCE: appDataBase) {
            INSTANCE?.getRestaurantDao()?.deleteAll()
            INSTANCE?.getMenuItemDAO()?.deleteAll()
            INSTANCE?.getUserEItemDAO()?.deleteAll()

            val restaurant1 = Restaurant(
                1,
                "Taste of india",
                R.drawable.restaurant_logo,
                "El Biar",
                "https://www.facebook.com/tasteofindiadz?mibextid=ZbWKwL",
                "https://www.facebook.com/tasteofindiadz?mibextid=ZbWKwL",
                "https://www.facebook.com/tasteofindiadz?mibextid=ZbWKwL",
                "https://www.facebook.com/tasteofindiadz?mibextid=ZbWKwL",
                "tasteOfIndia@gmail.com",
                "Indian",
                "0789990945",
                5
            )
            INSTANCE?.getRestaurantDao()?.addRestaurant(restaurant1)
            val restaurant2 = Restaurant(
                2,
                "Maison Tacos",
                R.drawable.taco,
                "Beb Ezzouar",
                "https://www.facebook.com/lamaisondutacos/?_rdc=1&_rdr",
                "https://web.facebook.com/lamaisondutacos/?_rdc=1&_rdr",
                "https://www.facebook.com/tasteofindiadz?mibextid=ZbWKwL",
                "https://web.facebook.com/lamaisondutacos/?_rdc=1&_rdr",
                "Lamaisondutacosdz@hotmail.com",
                "Italian",
                "0557808080",
                3
            )
            INSTANCE?.getRestaurantDao()?.addRestaurant(restaurant2)
            val restaurant3 = Restaurant(
                3,
                "Pizza Hut",
                R.drawable.pizza,
                "Dely Ibrahim",
                "https://www.facebook.com/PizzaHutAlgeria",
                "https://web.facebook.com/PizzaHutAlgeria",
                "https://web.facebook.com/PizzaHutAlgeria",
                "https://web.facebook.com/PizzaHutAlgeria",
                "Lamaisondutacosdz@hotmail.com",
                "Italian",
                "0554819958",
                4
            )
            INSTANCE?.getRestaurantDao()?.addRestaurant(restaurant3)

            val restaurant4 = Restaurant(
                4,
                "American Food",
                R.drawable.american,
                "Draria",
                "https://web.facebook.com/americandineralger",
                "https://web.facebook.com/americandineralger",
                "https://web.facebook.com/americandineralger",
                "https://web.facebook.com/americandineralger",
                "sarlamericandiner2018@gmail.com",
                "American",
                "023163646",
                5
            )
            INSTANCE?.getRestaurantDao()?.addRestaurant(restaurant4)

            val menuItem2 = MenuItem(1,
                name = "Masala Dosa",
                description = "Arguably South India’s most renowned culinary export, masala dosas are famous the world over. A sort of Indian pancake, dosas are made from a thin batter consisting of rice, flour and lentils.",
                image = R.drawable.plate_pic,
                type = "Principal plate",
                quantity = 2,
                price = 1500,
                id_res = 1
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem2)
            val menuItem3 = MenuItem(2,
                name = "Mysore masaladosa",
                description = "Mysore Masala Dosa is a perfect meal that is both satisfying and flavorful, and it is sure to tantalize your taste buds with every bite.",
                image = R.drawable.entry,
                type = "Entry",
                quantity = 2,
                price = 1200,
                id_res = 1
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem3)
            val menuItem4 = MenuItem(3,
                name = "Halloumi Tacos",
                description = "Halloumi tacos with homemade salsa macha, cherry tomatoes and avocados will give a little Middle Eastern kick to your taco night routine!",
                image = R.drawable.platetaco,
                type = "Principal plate",
                quantity = 4,
                price = 6500,
                id_res = 2
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem4)
            val menuItem5 = MenuItem(4,
                name = "Street Tacos",
                description = "Street tacos are delicious, amazing, and oh so mouthwatering! Bite into tender steak, zesty lime flavor with a hint of spice and add on tomatoes, avocado, and onions for a savory bite you are going to love!",
                image = R.drawable.tacoplate,
                type = "Principal plate",
                quantity = 3,
                price = 4500,
                id_res = 2
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem5)
            val menuItem6 = MenuItem(5,
                name = "Vegetable pizza",
                description = "Indulge in the goodness of fresh vegetables on a crispy crust with our delightful light vegetable pizza. Bursting with vibrant colors and flavors, this pizza is the perfect guilt-free treat.",
                image = R.drawable.pizza_plate,
                type = "Principal plate",
                quantity = 2,
                price = 2500,
                id_res = 3
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem6)
            val menuItem7 = MenuItem(6,
                name = "Sweet pie",
                description = "Whether enjoyed as a decadent snack or as the centerpiece of a fun and unique dessert spread, sweet pizza is a surefire hit for anyone with a sweet tooth.",
                image = R.drawable.pizzas,
                type = "Dessert",
                quantity = 2,
                price = 1000,
                id_res = 3
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem7)
            val menuItem8 = MenuItem(7,
                name = "Deep dish pizza",
                description = "No surprises here! Come take a bite of deep dish pizza that was invented at Pizzeria Uno in Chi-town in 1943. The super-stuffed pie is assembled upside-down to prevent overcooking all your favorite fixings.",
                image = R.drawable.americanf,
                type = "Principal plate",
                quantity = 3,
                price = 4000,
                id_res = 4
            )
            INSTANCE?.getMenuItemDAO()?.addItem(menuItem8)
            val user = User(1, "test@gmail.com", "test", "0556950490")
            INSTANCE?.getUserEItemDAO()?.addUser(user)

        }
    }
}
