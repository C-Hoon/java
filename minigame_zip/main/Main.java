package minigame_zip.main;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import minigame_zip.Air_Hockey.AirhockeyGUI;
import minigame_zip.Coin.MainFrame;
import minigame_zip.Omok.OmokGUI;
import minigame_zip.one_to_fifteen.One_to_Fifteen;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphics;
	
	private Image introBackground = new ImageIcon("images//tetris_art.jpg").getImage(); // 배경이미지
	private ImageIcon CoinBtnIamge = new ImageIcon("images//coingame.PNG");
	private ImageIcon OmokBtnIamge = new ImageIcon("images//omokgame.PNG");
	private ImageIcon AirhockeyBtnIamge = new ImageIcon("images//airhockeygame.PNG");
	private ImageIcon NumberBtnIamge = new ImageIcon("images//numbergame.PNG");
	
	private JButton omokbtn = new JButton(OmokBtnIamge);
	private JButton airhockeybtn = new JButton(AirhockeyBtnIamge);
	private JButton numberbtn = new JButton(NumberBtnIamge);
	private JButton coinbtn = new JButton(CoinBtnIamge);
	
	int SCREEN_WiDTH = 960;
	int SCREEN_HEIGHT = 540;
	
	public Main() {
		
		setTitle("미니게임");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel panel = new MainPanel();
		panel.setLayout(null);
		
		
		Container c = getContentPane();
		
		omokbtn.setBounds(30,50,280,73);
		airhockeybtn.setBounds(30,153,280,73);
		numberbtn.setBounds(30,253,280,73);
		coinbtn.setBounds(30,353,280,73);
		
		panel.add(omokbtn);
		panel.add(airhockeybtn);
		panel.add(numberbtn);
		panel.add(coinbtn);
		
		
		omokbtn.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new OmokGUI();
				setVisible(false);
			}
		});
		
		airhockeybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AirhockeyGUI();
				setVisible(false);
			}
		});
		numberbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new One_to_Fifteen();
				setVisible(false);
			}
		});
		coinbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				setVisible(false);
			}
		});
		
		c.add(panel);
		
		setSize(SCREEN_WiDTH,SCREEN_HEIGHT);
		setVisible(true);
		
		
		
		
		
		
		
	}
	
	
	class MainPanel extends JPanel{
		
		public void paintComponent(Graphics g) {
			screenImage = createImage(SCREEN_WiDTH, SCREEN_HEIGHT);
			screenGraphics = screenImage.getGraphics();
			screenDraw(screenGraphics);
			g.drawImage(screenImage,0,0,null);
			
		}
		
		public void screenDraw(Graphics g) {
			g.drawImage(introBackground,0,0,null);
			//paintComponents(g);
			this.repaint();
		}
	}
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
