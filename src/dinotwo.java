//private void drawCactus(Graphics2D g2d, int size) {
//    // Use a Chrome Dino-like color
//    g2d.setColor(new Color(83, 121, 78)); // greenish
//
//    int mainX = x + width / 2 - 6;
//    int mainY = y - height + 30;
//    int mainWidth = 12;
//    int mainHeight = height;
//
//    // Main column
//    g2d.fillRect(mainX, mainY, mainWidth, mainHeight);
//
//    // Arms/branches
//    if (size == 1) { // Small cactus, single column, two arms
//        g2d.fillRect(mainX - 8, mainY + mainHeight / 3, 6, 16); // left lower
//        g2d.fillRect(mainX + mainWidth + 2, mainY + mainHeight / 2, 6, 16); // right middle
//    } else { // Large cactus, three columns and more arms
//        // Left column
//        g2d.fillRect(mainX - 16, mainY + 20, 8, mainHeight - 20);
//        // Right column
//        g2d.fillRect(mainX + mainWidth + 8, mainY + 10, 8, mainHeight - 10);
//
//        // Left arms
//        g2d.fillRect(mainX - 18, mainY + 30, 6, 14);
//        // Right arms
//        g2d.fillRect(mainX + mainWidth + 14, mainY + 30, 6, 14);
//    }
//}
