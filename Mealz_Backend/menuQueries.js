const { prisma } = require('./prismaImport');



async function findMenuByRes(restId) {
        const menus = await prisma.$queryRaw`
          SELECT m.*, t.Nom_TMenu
          FROM menu m
          JOIN type_menu t ON m.ID_TMenu = t.ID_TMenu
          WHERE m.ID_Restaurant = ${restId};
        `;
        return menus;
      }
async function findMenuDetail(MenuId) {
  const menu = await prisma.menu.findFirst({
    where: {
      ID_Menu: parseInt(MenuId,10) ,
    },
  });
  return menu;
}
  
  module.exports = {
    findMenuByRes,
    findMenuDetail,
  };