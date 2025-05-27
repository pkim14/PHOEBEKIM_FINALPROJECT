///// ... existing imports and class attributes remain unchanged
//
//private void drawCactus(Graphics2D g2d, int size) {
//    // Set the cactus color to a dark gray/green for visibility
//    g2d.setColor(new Color(83, 121, 78)); // Chrome Dino cactus color approximation
//
//    int baseX = x + width / 2;
//    int baseY = y;
//
//    if (size == 1) {
//        // Single small cactus (single column)
//        g2d.fillRect(baseX - 8, baseY - 40, 16, 40); // main column
//
//        // Left arm
//        g2d.fillRect(baseX - 16, baseY - 28, 8, 12);
//        g2d.fillRect(baseX - 16, baseY - 28, 6, 8);
//
//        // Right arm
//        g2d.fillRect(baseX + 8, baseY - 30, 8, 14);
//        g2d.fillRect(baseX + 10, baseY - 30, 6, 8);
//    } else {
//        // Large cactus (double/triple columns)
//        // Center main column
//        g2d.fillRect(baseX - 8, baseY - 60, 16, 60);
//
//        // Left column
//        g2d.fillRect(baseX - 20, baseY - 40, 12, 40);
//        g2d.fillRect(baseX - 24, baseY - 28, 6, 14);
//
//        // Right column
//        g2d.fillRect(baseX + 8, baseY - 48, 12, 48);
//        g2d.fillRect(baseX + 18, baseY - 36, 6, 14);
//
//        // Extra branch/arm for visual interest
//        g2d.fillRect(baseX - 12, baseY - 55, 6, 18);
//        g2d.fillRect(baseX + 16, baseY - 40, 6, 14);
//    }
//}
