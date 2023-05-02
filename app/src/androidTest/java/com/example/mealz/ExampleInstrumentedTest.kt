package com.example.mealz

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    lateinit var mDataBase:appDataBase
    @Before
    fun initDB(){
        mDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry. getInstrumentation().context,appDataBase::class.java).build()
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mealz", appContext.packageName)
    }
    @Test
    fun testAddRestau(){
        val restau = Restaurant(1,
            name="American Food",
           image = R.drawable.american,
            adress = "Draria",
            fbPageLink = "https://web.facebook.com/americandineralger",
            fbWebLink = "https://web.facebook.com/americandineralger",
            instPageLink = "https://web.facebook.com/americandineralger",
            instWebLink = "https://web.facebook.com/americandineralger",
            emailAdress = "sarlamericandiner2018@gmail.com",
           type = "American",
            phone = "023163646",
            rating = 5
        )
//        val restau2 = Restaurant(1,
//            name="American Food",
//            image = R.drawable.american,
//            adress = "Draria",
//            fbPageLink = "https://web.facebook.com/americandineralger",
//            fbWebLink = "https://web.facebook.com/americandineralger",
//            instPageLink = "https://web.facebook.com/americandineralger",
//            instWebLink = "https://web.facebook.com/americandineralger",
//            emailAdress = "sarlamericandiner2018@gmail.com",
//            type = "American",
//            phone = "023163646",
//            rating = 5
//        )
        mDataBase?.getRestaurantDao()?.addRestaurant(restau)
        var rescheck= mDataBase?.getRestaurantDao()?.getRestaurantsById(restau.idRes)
        assertEquals(restau,rescheck)
    }
    @Test
    fun testDeleteRestau(){
        val restau = Restaurant(1,
            name="American Food",
            image = R.drawable.american,
            adress = "Draria",
            fbPageLink = "https://web.facebook.com/americandineralger",
            fbWebLink = "https://web.facebook.com/americandineralger",
            instPageLink = "https://web.facebook.com/americandineralger",
            instWebLink = "https://web.facebook.com/americandineralger",
            emailAdress = "sarlamericandiner2018@gmail.com",
            type = "American",
            phone = "023163646",
            rating = 5
        )
        mDataBase?.getRestaurantDao()?.addRestaurant(restau)
        mDataBase?.getRestaurantDao()?.deleteRestaurant(restau)
        var deleted =mDataBase?.getRestaurantDao()?.getRestaurantsById(restau.idRes)
        assertNull(deleted)
    }
    @After
    fun closeDB(){
        mDataBase.close()
    }
}