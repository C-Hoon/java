package minigame_zip.Omok;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;


import minigame_zip.main.Main;


@SuppressWarnings("serial")
public class OmokGUI extends JFrame{

	private Container c;
	MapSize size = new MapSize();
	
	
	public OmokGUI() {
		
		c= getContentPane();
		setBounds(200,20,650,700);
		c.setLayout(null);
		
		Map map = new Map(size);
		DrawBoard d = new DrawBoard(size,map);
		createToolbar();
		setContentPane(d);
		addMouseListener(new MouseEventHandler(map,size,d,this));	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String[] buttons = {"다시하기","메인화면으로"};
				int answer = JOptionPane.showConfirmDialog(c, "메인화면?", "Omok",JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.CLOSED_OPTION) {
					System.out.println("클로즈");
				}
				else if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					new Main();
					
					System.out.println("dPtm");
					//c.setVisible(false);
					
				} else if(answer == JOptionPane.NO_OPTION){  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("노");
				} 
			}
		});
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void createToolbar() {
		
		JToolBar bar = new JToolBar("Menu");
		JButton home = new JButton("home");
		bar.add(home);
		bar.setBounds(0,0,100,100);
		c.add(bar);
	}
	
	public void showPopUp(String message) {
		JOptionPane.showMessageDialog(this, message,"",JOptionPane.INFORMATION_MESSAGE);;
		System.exit(0);
	}
		
	
}


