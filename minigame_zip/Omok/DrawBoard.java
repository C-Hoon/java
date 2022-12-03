package minigame_zip.Omok;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class DrawBoard extends JPanel{

	private MapSize size;
	private Map map;
	private final int STONE_SIZE=28; // 돌 크기
	public DrawBoard(MapSize m, Map map) {
		
		setBackground(new Color(206,167,61));
		size = m;
		setLayout(null);
		this.map=map;		
	}
	
	
	
	
	
	public void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK);// 그려질 색 블랙으로 지정
		board(arg0); // 보드 그리기
		drawStone(arg0);
	}
	
	// 바둑판 그리기
	public void board(Graphics arg0) {
		for(int i=1;i<=size.getSize();i++) {
			//가로 줄 그리기
			arg0.drawLine(size.getCell(), i*size.getCell(), size.getCell()*size.getSize(), i*size.getCell());
			//시작점 x : 30, 시작점 y : i값 (줄번호)*30, 끝점 x : 600,끝점 y : i값 (줄번호)*30
			 //세로줄 그리기
			 arg0.drawLine(i*size.getCell(), size.getCell(), i*size.getCell() , size.getCell()*size.getSize()); 
			 //시작점 x : i값 (줄번호)*30, 시작점 y : 30, 끝점 x : i값 (줄번호)*30, 끝점 y : 600
		}
	}
	
	// 바둑돌 그리기
	public void drawStone(Graphics arg0) {
		for(int y=0;y<size.getSize();y++) {
			for(int x=0;x<size.getSize();x++) {
				//배열의 정보가 흑일경우 흑돌, 백일경우 백돌을 그림
				if(((Map) map).getXY(y, x)==map.getBlack()) {
					drawBlack(arg0,x,y);
				}
				else if(map.getXY(y,x)==map.getWhite()) {
					drawWhite(arg0,x,y);
				}
			}
		}
	}
	
	
	 public void drawBlack(Graphics arg0,int x,int y){
		 //그려질 색을 블랙으로 바꿈
		 arg0.setColor(Color.BLACK);
		 arg0.fillOval((x+1)*size.getCell()-15, (y)*size.getCell()+15, STONE_SIZE, STONE_SIZE);
		
		 }
		 public void drawWhite(Graphics arg0,int x,int y){
		 //그려질 색을 화이트로 바꿈
		 arg0.setColor(Color.WHITE);
		 arg0.fillOval(x*size.getCell()+15, y*size.getCell()+15, STONE_SIZE, STONE_SIZE);
		
		 }
	}
