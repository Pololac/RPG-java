package animations;

public class MonstreAnime {
    private final String[] frames = {
            """
           (o_o)
          /|_|\\
          /   \\
        """,
            """
           (O_O)
          /|_|\\
          /   \\
        """,
            """
           (o_o)
          /|-|\\
          /   \\
        """,
            """
           (x_x)
          /|_|\\
          /   \\
        """
    };

    public void afficher() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.print("\033[H\033[2J"); // Nettoie la console
            System.out.flush();

            System.out.println(frames[i % frames.length]);
            Thread.sleep(300);
        }
    }
}

