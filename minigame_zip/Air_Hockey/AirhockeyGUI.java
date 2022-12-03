package minigame_zip.Air_Hockey;

import javax.swing.JFrame;





@SuppressWarnings("serial")
public class AirhockeyGUI extends JFrame{

	
	
	public AirhockeyGUI() {
		
		GameBoard gb = new GameBoard();
		
		JFrame gameFrame = new JFrame();
		
		gameFrame.setTitle("Air Hockey");
		gameFrame.setSize(495,678);
		gameFrame.setLocation(250, 30);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.add(gb);
		
		
		gameFrame.setVisible(true);
		
		
	}
	
	
}

