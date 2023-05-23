const { prisma } = require('./prismaImport');
const bcrypt = require('bcrypt');

async function getUsers() {
    return prisma.utilisateur.findMany();
  }
  async function findUserByMail(mail) {
    const user = await prisma.utilisateur.findFirst({
      where: {
        Mail: mail ,
      },
    });
    return user;
  }
  async function insertUser(idUser,name,name2, email, adress,password) {
          // Encrypt the password
          const saltRounds = 10;
          const hashedPassword = await bcrypt.hash(password, saltRounds);
      
    const newUser = await prisma.utilisateur.create({
      data: {
        ID_Utilisateur:idUser,
        Nom: name,
        Prenom:name2,
        Mail: email,
        Password: hashedPassword,
        Adresse:adress,
      },
    });
    return newUser;
  }
module.exports = {
    getUsers,
    findUserByMail,
    insertUser,
  };
