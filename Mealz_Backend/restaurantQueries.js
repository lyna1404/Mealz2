const { prisma } = require('./prismaImport');

async function getRest() {
    return prisma.$queryRaw`
    SELECT r.*, t.Nom_Type
    FROM restaurant r
    JOIN restaurant_types rt ON r.ID_Restaurant = rt.ID_Restaurant
    JOIN type t ON rt.ID_Type = t.ID_Type
  `;
  }
module.exports = {
    getRest,
  };