package minigame_zip.Omok;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import minigame_zip.Air_Hockey.PuckMove;



public class MouseEventHandler extends MouseAdapter{
	private Map map;
	private MapSize ms;
	private DrawBoard d;
	private OmokGUI main;
	
	public MouseEventHandler(Map m, MapSize ms, DrawBoard d, OmokGUI main) {
		map = m;
		this.ms = ms;
		this.d = d;
		this.main = main;
	}
	
	
	
	public void mousePressed(MouseEvent arg0) {
		
		super.mousePressed(arg0);
		// 입력된 x,y 좌표를 Cell(30)으로 나눠 나온 값에 1, 2 를 빼 0 ~ 19 사이로 맞춤
		int x = (int) Math.round(arg0.getX() / (double) ms.getCell()) - 1;
		int y = (int) Math.round(arg0.getY() / (double) ms.getCell()) - 2;
		if(x<0||x>19||y<0||y>19) {
			return;
		}
		
		if(map.getXY(y, x) == map.getBlack()
				||map.getXY(y, x)==map.getWhite()) {
			return; //이미 놓여진 자리일 경우 return
		}
		
		GameSound.HitBallSound();
		
		System.out.println(x+" "+y); 
		map.setMap(y, x); // 입력된 자표를 배열에 정보 표시
		map.changeCheck(); // 차례를 바꿈
		d.repaint(); // 맵을 새롭게 다시 그림
		if(map.winCheck(x, y)) { // 승리확인 완료되면 팝업창을 띄움
			if(map.getCheck()==true) {
				main.showPopUp("백돌 승리");
				
			}
			else {
				main.showPopUp("흑돌 승리");
			}
		}
		
			
	}
		
	
}
