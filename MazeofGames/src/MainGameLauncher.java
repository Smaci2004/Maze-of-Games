import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGameLauncher extends JFrame {
    public MainGameLauncher() {
        setTitle("Maze of Games");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Përdorimi i panelit të personalizuar për sfondin
        BackgroundPanel panel = new BackgroundPanel("src/MazeofGames.jpg");
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        gbc.gridy = 0;

        JLabel title = new JLabel("MAZE OF GAMES");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(new Color(197, 136, 255));
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panel.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JButton pacManButton = createStyledButton("PACMAN", new Color(124, 52, 150), Color.WHITE);
        gbc.gridx = 0;
        panel.add(pacManButton, gbc);

        JButton spaceInvadersButton = createStyledButton("SPACE INVADERS", new Color(124, 52, 150), Color.WHITE);
        gbc.gridx = 1;
        panel.add(spaceInvadersButton, gbc);

        JButton flappyBirdButton = createStyledButton("FLAPPY BIRD", new Color(124, 52, 150), Color.WHITE);
        gbc.gridx = 2;
        panel.add(flappyBirdButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;

        JButton quitButton = createStyledButton("QUIT", new Color(200, 0, 0), Color.WHITE);
        panel.add(quitButton, gbc);

        add(panel);

        pacManButton.addActionListener((ActionEvent e) -> launchGame("PacMan"));
        spaceInvadersButton.addActionListener((ActionEvent e) -> launchGame("SpaceInvaders"));
        flappyBirdButton.addActionListener((ActionEvent e) -> launchGame("FlappyBird"));

        quitButton.addActionListener((ActionEvent e) -> dispose());
    }

    private void launchGame(String gameName) {
        setVisible(false);
        JFrame gameFrame = new JFrame(gameName);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setResizable(false);

        switch (gameName) {
            case "PacMan":
                gameFrame.add(new PacMan());
                break;
            case "SpaceInvaders":
                gameFrame.setSize(512, 512);
                gameFrame.add(new SpaceInvaders());
                break;
            case "FlappyBird":
                gameFrame.add(new FlappyBird());
                break;
        }

        gameFrame.pack(); 
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                setVisible(true);
            }
        });
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        button.setPreferredSize(new Dimension(180, 80));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGameLauncher launcher = new MainGameLauncher();
            launcher.setVisible(true);
        });
    }
}

// Paneli me sfond të personalizuar
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            System.out.println("Background image not found: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
