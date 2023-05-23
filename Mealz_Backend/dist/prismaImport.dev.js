"use strict";

var express = require('express');

var bodyParser = require('body-parser');

var mysql = require('mysql');

var app = express();

var _require = require('@prisma/client'),
    PrismaClient = _require.PrismaClient;

var prisma = new PrismaClient();
module.exports = {
  prisma: prisma
};