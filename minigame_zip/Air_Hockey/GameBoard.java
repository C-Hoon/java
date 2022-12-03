package minigame_zip.Air_Hockey;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import minigame_zip.main.Main;



@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener{
	
	Puck puck;
	GreenPaddle player;
	BluePaddle os;
	GameOver gameover;
	Goal goal;
	AirhockeyGUI air;
	static int scorePlayer = 0;
	static int scoreOs = 0;
	boolean upP = false; 
	boolean downP = false; 
	boolean leftP = false; 
	boolean rightP = false;
	Image buffImage;
	Graphics buffg;
	
	
	
	public GameBoard(){
		
		super();
		
		puck = new Puck(217,300,"images//puck.png");
		player = new GreenPaddle(210,550,"images//player1.png");
		os = new BluePaddle(210,25,"images//player2.png");
		gameover = new GameOver(150,350,"images//gameover.png");
		goal = new Goal(180,190 ,"images//goal.png" );
		
		PuckMove pm = new PuckMove(puck,this,player,os);
		pm.start();
		
		super.setFocusable(true);
		super.addKeyListener(this);
	}
	
	public void paint(Graphics g){
		
		if(buffg == null) {
			buffImage = createImage(this.getWidth(), this.getHeight());
			if(buffImage == null) {
				System.out.println("더블 버퍼링 실패");
			}
			else {
				buffg = buffImage.getGraphics();
				System.out.println("더블 버퍼링 성공");
			}
		}
		
		
		
		
		ImageIcon background = new ImageIcon("images//background.jpg");
		buffg.drawImage(background.getImage(), 0, 0, null);
		
		puck.draw(buffg);
		player.draw(buffg);
		os.draw(buffg);
		
		buffg.setColor(Color.BLUE);
		
		// 점수판
		buffg.drawString(""+scoreOs,450,300);
		buffg.drawString(""+scorePlayer,450,350);
		// g.drawString("||",450,325);
		
		// 골 먹힐때 표시
		if(scorePlayer < 3 && scoreOs < 3){
			
			if(puck.getY() < -30)
			goal.draw(buffg);
	
			else if(puck.getY() > 650)
			goal.draw(buffg);
		}
		
		// 승리자가 정해졌을 경우
		else if(scorePlayer == 3 || scoreOs == 3){
			
			gameover.draw(buffg);
			super.setFocusable(false);
			
			if(scorePlayer == 3){
				
				buffg.drawString("Player 1 Win The Match",185,170);
			}
		
			else if(scoreOs == 3){
				buffg.drawString("Player 2 Win The Match",185,170);
			}
			
			String[] buttons = {"다시하기","메인화면으로"};
			int answer = JOptionPane.showOptionDialog(this, "다시 하시겠습니까?", "ReGame",JOptionPane.YES_NO_OPTION
					,JOptionPane.QUESTION_MESSAGE,null,buttons,null);
			if(answer == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}
			else if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
				scorePlayer = 0;
				scoreOs = 0;
				
				puck.setX(217);
				puck.setY(300);
				
				player.setX(210);
				player.setY(550);
				
				os.setX(210);
				os.setY(25);
				
				
				
				PuckMove pm = new PuckMove(puck,this,player,os);
				pm.start();
				super.setFocusable(true);
				super.addKeyListener(this);
				puck.setY(puck.getY() + 1);
				
			} else if(answer == JOptionPane.NO_OPTION){  //사용자가 Yes 이외의 값을 눌렀을 경우
				new Main();
				
				
			} 
			
		}	
		
		g.drawImage(buffImage,0,0,this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// 위쪽 패들 움직임
		if(e.getKeyCode() == e.VK_W){
			
			if(os.getY() > 20)
				os.setY(os.getY() - 15);
		}

		else if(e.getKeyCode() == e.VK_A){
			
			if(os.getX() > 15 )
				   os.setX(os.getX() - 15);
		}
		
		else if(e.getKeyCode() == e.VK_S){
	
			if(os.getY() < 260)
				os.setY(os.getY() + 15);
		}
		
		else if(e.getKeyCode() == e.VK_D){
		
			if(os.getX() < 400)
				   os.setX(os.getX() + 15);
		}

		// 아래쪽 패들 움직임
		else if(e.getKeyCode() == e.VK_UP){
			
			upP	= true;
			if(player.getY() > 320)
				player.setY(player.getY() - 15);
		}
		
		else if(e.getKeyCode() == e.VK_LEFT){
			
			leftP = true;
			if(player.getX() > 15)
				player.setX(player.getX() - 15);
		}
		
		else if(e.getKeyCode() == e.VK_DOWN){
			
			downP = true;
			if(player.getY() < 560)
				player.setY(player.getY() + 15);
		}
		
		else if( e.getKeyCode() == e.VK_RIGHT){
			
			rightP = true;
			if(player.getX() < 400)
				player.setX(player.getX() + 15);
		}		
		
		// 리겜
		else if(e.getKeyCode() == e.VK_SPACE){
			puck.setX(217);
			puck.setY(300);
			
			player.setX(210);
			player.setY(550);
			
			os.setX(210);
			os.setY(25);
			
			
			PuckMove pm = new PuckMove(puck,this,player,os);
			pm.start();
			
			puck.setY(puck.getY() + 1);

			
		}
		
		
		super.repaint();

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			upP = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
		    downP = false; 
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    leftP = false; 
		} 
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
		    rightP = false; 
		} 
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}