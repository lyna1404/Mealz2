"use strict";

var _require = require('./prismaImport'),
    prisma = _require.prisma;

var bcrypt = require('bcrypt');

function getUsers() {
  return regeneratorRuntime.async(function getUsers$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          return _context.abrupt("return", prisma.utilisateur.findMany());

        case 1:
        case "end":
          return _context.stop();
      }
    }
  });
}

function findUserByMail(mail) {
  var user;
  return regeneratorRuntime.async(function findUserByMail$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap(prisma.utilisateur.findFirst({
            where: {
              Mail: mail
            }
          }));

        case 2:
          user = _context2.sent;
          return _context2.abrupt("return", user);

        case 4:
        case "end":
          return _context2.stop();
      }
    }
  });
}

function insertUser(idUser, name, name2, email, adress, password) {
  var saltRounds, hashedPassword, newUser;
  return regeneratorRuntime.async(function insertUser$(_context3) {
    while (1) {
      switch (_context3.prev = _context3.next) {
        case 0:
          // Encrypt the password
          saltRounds = 10;
          _context3.next = 3;
          return regeneratorRuntime.awrap(bcrypt.hash(password, saltRounds));

        case 3:
          hashedPassword = _context3.sent;
          _context3.next = 6;
          return regeneratorRuntime.awrap(prisma.utilisateur.create({
            data: {
              ID_Utilisateur: idUser,
              Nom: name,
              Prenom: name2,
              Mail: email,
              Password: hashedPassword,
              Adresse: adress
            }
          }));

        case 6:
          newUser = _context3.sent;
          return _context3.abrupt("return", newUser);

        case 8:
        case "end":
          return _context3.stop();
      }
    }
  });
}

module.exports = {
  getUsers: getUsers,
  findUserByMail: findUserByMail,
  insertUser: insertUser
};