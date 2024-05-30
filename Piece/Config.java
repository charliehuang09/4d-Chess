package Piece;

public class Config {
    public static boolean fastRender = false;
    public static String rules = 
    """
        4 Player Chess Rules:
        1. Pawns Promote when they get to the center
        2. Whoever gets the most points wins
          - Caputring a peice gives points
          - Checkmating gets points
          - Pawns that promote are still only worth one point when caputured
        3. When a player gets checkmated, their peices value all go to 

            """;
    public static int animateFrames = 100;
    public static long waitMS = 1;

}
