const {comparePasswordWithEmail} = require('./Auth');
const { getRest } = require('./restaurantQueries');
const { findMenuByRes,findMenuDetail } = require('./menuQueries');
const express = require('express');
const bodyParser = require('body-parser');
const {prisma} = require('./prismaImport')
const mysql = require('mysql');
const app = express();

app.use(bodyParser.json());
// Define an endpoint to get all restaurants
app.get('/restaus/getall', async (req, res) => {
  try {
    const restaus = await getRest();
    res.json(restaus);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching restaurants' });
  }
});


// Get menus of restaurant
app.get('/menus/:restaurantId', async(req, res) => {
  try{
    const restaurantId = req.params.restaurantId;
    // console.log(restaurantId);
    const menu = await findMenuByRes(restaurantId);
  
    res.json(menu);
} catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching menus' });
}
});
// Get menu details
app.get('/menu/:menuId', async(req, res) => {
  try{
    const menuId = req.params.menuId;
    //console.log(menuId);
    const menuDetails = await findMenuDetail(menuId);
  
    res.json(menuDetails);
} catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching menu details' });
}
});
// LogIn

// Example route handler in Express.js
app.post('/login', async (req, res) => {
 const { mail, password } = req.body;
 console.log(req.body);
  // Compare the password with the stored hashed password
  const comparisonResult = await comparePasswordWithEmail(mail, password);

  // Send the comparison result back to Kotlin
  res.json(comparisonResult);
});


app.get('/', async (req, res) => {
  res.send('Hello World!')
  });
  
// Start the server
app.listen(4004, () => {
  console.log('Server is running on port 4004');
});
