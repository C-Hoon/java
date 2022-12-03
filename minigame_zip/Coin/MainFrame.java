package minigame_zip.Coin;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Enemy2 {

	 

	 Image img; //이미지 참조변수

	 int x, y; //이미지 중심 좌표

	 int w, h; //이미지 절반폭, 절반높이

	 

	 int dy; //적군의 변화량

	 

	 int width, height; //화면(panel)의 사이즈

	 //본인 객체가 죽었는지 여부!

	 boolean isDead = false;

	 

	 public Enemy2(Image imgEnemy, int width, int height) {

	 this.width = width;

	 this.height = height;

	 

	 //멤버변수 값 초기화..

	 img = imgEnemy.getScaledInstance(64, 64, Image.SCALE_SMOOTH);

	 w = 32; //이미지 절반넓이

	 h = 32;

	 

	 Random rnd = new Random();

	 x = rnd.nextInt(width - w * 2) + w; //w ~ width - w

	 y = -h;

	 

	 dy =+ rnd.nextInt(15) + 1;//떨어지는 속도 랜덤하게

	 }

	 

	 void move() { //Enemy의 움직이는 기능 메소드

	 y += dy;

	 //만약 화면 밑으로 나가버리면 객체 없애기

	 if( y > height + h ) { //ArrayList에서 제거

	 isDead = true; //죽음 표식! 

	 }
	 }}

public class MainFrame extends JFrame {

 GamePanel panel;

 GameThread gThread;

 public MainFrame() {

 

 setTitle("Mini-Game <코인 획득 게임>");

 setDefaultCloseOperation(EXIT_ON_CLOSE);

 setBounds(300, 300, 700, 700);

 setResizable(false);

 

 panel = new GamePanel();

 add(panel,BorderLayout.CENTER);

 

 setVisible(true);

 //게임 진행시키는 스레드 객체 생성 및 스타트

 gThread = new GameThread();

 gThread.start(); //run() 메소드 자동실행!!

 

 //프레임에 키보드 입력에 반응하는 keyListner 등록

 addKeyListener(new KeyListener() { 

 @Override

 public void keyTyped(KeyEvent e) {

 // TODO Auto-generated method stub

 } 

 @Override

 public void keyReleased(KeyEvent e) {

 //눌러진 키가 무엇인지 알아내기 

 int keyCode = e.getKeyCode();

 switch( keyCode ) {

 case KeyEvent.VK_LEFT:

 panel.dx = 0; //원랜 getsetter 만들어야함

 break;

 case KeyEvent.VK_RIGHT:

 panel.dx = 0;

 break;

 case KeyEvent.VK_UP:

 panel.dy = 0;

 break;

 case KeyEvent.VK_DOWN:

 panel.dy = 0;

 break; 

 }

 //방향키 4개 구분

 } 

 @Override

 public void keyPressed(KeyEvent e) {

 //눌러진 키가 무엇인지 알아내기 

 int keyCode = e.getKeyCode();

 switch( keyCode ) {

 case KeyEvent.VK_LEFT:

 panel.dx = -8; //원랜 getsetter 만들어야함

 break;

 case KeyEvent.VK_RIGHT:

 panel.dx = 8;

 break;

 case KeyEvent.VK_UP:

 panel.dy = -8;

 break;

 case KeyEvent.VK_DOWN:

 panel.dy = 8;

 break; 

 }

 //방향키 4개 구분 

 }

 });

 }//생성자

 

 ///////////////////////////////////////////////////////

 class GamePanel extends JPanel { //게임화면 그려낼 Panel

 

 //화면에 보여질 이미지 객체 참조변수 - 멤버변수

 Image imgBack, imgPlayer, imgEnemy;

 int width, height;//패널 사이즈 가져오기

 int x, y, w, h;//xy : 플레이어의 중심 좌표 / wh : 이미지 절반폭;

 int dx = 0, dy = 0;//플레이어 이미지의 이동속도, 이동방향

 //적군 객체 참조변수, 여러마리일수있으므로 ArrayList(유동적 배열) 활용

 ArrayList<Enemy2> enemies = new ArrayList<Enemy2>();

 

 int score; //점수

 

 

 public GamePanel() {

 //GUI 관련 프로그램의 편의를 위해 만들어진 도구상자(Toolkit) 객체 

 Toolkit toolkit = Toolkit.getDefaultToolkit();

 //생성자에서 사이즈를 구하려하면 무조건 0임, 아직 패널이 안붙어서 사이즈를 모르기때문

 //width = getWidth(); 
 
 //height = getHeight();

 

 imgBack = toolkit.getImage("images/back2.jpg");//배경 이미지

 imgPlayer = toolkit.getImage("images/bear.png");//플레이어 이미지 객체

 imgEnemy = toolkit.getImage("images/COIN.png");//적군 이미지 객체 

 

 }//생성자 

 

 //화면에 보여질때  보여질 내용물 작업을 수행하는 메소드 : 자동 실행(콜백 메소드)

 @Override

 protected void paintComponent(Graphics g) {

 //화면에 보여질 작업 코딩

 if( width == 0 || height == 0) { //처음 호출시엔 느려서 안보이다 이후 보임

 width = getWidth();

 height = getHeight();

 //리사이징

 imgBack = imgBack.getScaledInstance(width, height, Image.SCALE_SMOOTH);

 imgPlayer = imgPlayer.getScaledInstance(128, 128, Image.SCALE_SMOOTH);

 x = width/2;//플레이어의 좌표 계산

 y = height - 100;

 w = 64;

 h = 64;

 } 

 //이곳에 화가객체가 있으므로 그림 그리는 작업은 무조건 여기서 

 g.drawImage(imgBack, 0, 0, this);//배경 그리기 

 for(Enemy2 t : enemies) {

 g.drawImage(t.img, t.x-t.w, t.y-t.h, this);

 }

 

 g.drawImage(imgPlayer, x - w, y - h, this);//플레이어

 g.setFont(new Font(null, Font.BOLD, 20));//점수 표시하기

 g.drawString("Score : " + score,10, 30);

 //여러장면 만들기 위해 일정시간마다 다시 그리기(re painting)

 }//paintComponent method                                       

 

 void move() { //플레이어 움직이기(좌표 변경)

 //적군들 움직이기

 //중간에 배열의 개수 변경될 여지가 있다면

 //맨 마지막 요소부터 거꾸로 0번 요소까지 역으로 처리해야함.

 for(int i = enemies.size()-1; i >= 0; i--) {

 Enemy2 t = enemies.get(i); 

 t.move();

 if(t.isDead)  //ArrayList에서 제거

 enemies.remove(i);

 } 

 

 //for(Enemy t : enemies) { //foreach문으론 불가 }

 x += dx;

 y += dy;

 //플레이어 좌표가 화면 밖으로 나가지 않도록

 if(x < w) x = w;

 if(x > width - w) x = width - w;

 if(y < h) y = h;

 if(y > height - h) y = height - h;

 }

 

 void makeEnemy() { //적군 생성 메소드

 if(width == 0 || height == 0) return;

 

 Random rnd = new Random();//50번에 한번꼴로 만들기

 int n = rnd.nextInt(15);

 if( n == 0 ) {

 enemies.add(new Enemy2(imgEnemy, width, height));

 }

 }//makeenemy

 

 //충돌체크 작업 계산 메소드

 void checkCollision() { //플레이어와 적군의 충돌

 

 for(Enemy2 t : enemies) {

 int left = t.x - t.w;

 int right = t.x + t.w;

 int top = t.y - t.h;

 int bottom = t.y + t.h;

 

 if(x > left && x < right && y > top && y < bottom) {

 t.isDead = true; //충돌했음

 score += 5;

 }

 } 

 }

 

 }//GamePanel class

 ///////////////////////////////////////////////////////

 //일정 시간마다 개엠화면을 갱신시키는 작업 수행하는 별도 스레드 클래스

 class GameThread extends Thread {

 @Override

 public void run() {

 while(true) {

 //적군 객체 만들어내는 기능 메소드 호출

 panel.makeEnemy();

 //GamePanel의 플레이어 좌표 변경 

 //panel.x += -1;// 객체의 멤버값 변경은  

 //panel.y += -5;/// 그 객체가 스스로 하도록 하는것이 OOP이 기본 모티브

 panel.move(); 

 panel.checkCollision();//충돌 체크 기능 호출 

 panel.repaint();//GamePanel의 화면 갱신

 try { //너무 빨리 돌아서 천천히 돌도록

 sleep(20);

 } catch (InterruptedException e) {}

 }

 }

 }

 

 

}
