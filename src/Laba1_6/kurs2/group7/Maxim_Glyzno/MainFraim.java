package Laba1_6.kurs2.group7.Maxim_Glyzno;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFraim extends JFrame
{
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;

	private JMenuItem  pauseMenuItem;
	private JMenuItem resumeMenuItem;
	private JCheckBoxMenuItem xarizmaMenuItem;


	private Field field = new Field();

	public MainFraim()
	{
		super("Программирование и синхронизация потоков");
		setSize(WIDTH,HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
		setExtendedState(MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu ballMenu = new JMenu("Мячи");
		Action addBallAction = new AbstractAction("Добавить мяч")
		{
			public void actionPerformed(ActionEvent arg0)
			{
				field.addBall();
				if(!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled())
				{
					pauseMenuItem.setEnabled(true);
				}
			}
		};

		menuBar.add(ballMenu);
		ballMenu.add(addBallAction);
		JMenu controlMenu = new JMenu("Управление");
		menuBar.add(controlMenu);

		Action pauseAction = new AbstractAction("Приостановить движение"){
			public void actionPerformed(ActionEvent arg0) 
			{
				field.pause();
				pauseMenuItem.setEnabled(false);
				resumeMenuItem.setEnabled(true);
			}
		};
		pauseMenuItem = controlMenu.add(pauseAction);
		pauseMenuItem.setEnabled(false);

		Action resumeAction = new AbstractAction("Возобновить движение"){
			public void actionPerformed(ActionEvent arg0){
				field.resume();
				resumeMenuItem.setEnabled(true);
				resumeMenuItem.setEnabled(false);
			}
		};
		resumeMenuItem = controlMenu.add(resumeAction);
		resumeMenuItem.setEnabled(false);

		JMenu effectMenu = new JMenu("Режим управления");
		menuBar.add(effectMenu);
		AbstractAction effectAction = new AbstractAction("Следовать за курсором"){
			public void actionPerformed(ActionEvent arg0) {
				field.xar = xarizmaMenuItem.isSelected();}
		};
		xarizmaMenuItem = new JCheckBoxMenuItem(effectAction);
		effectMenu.add(xarizmaMenuItem);
		xarizmaMenuItem.setEnabled(true);

		getContentPane().add(field,BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		MainFraim frame = new MainFraim();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true); 
		class T1 extends Thread { 
			public void run() {
			} 
		} 
		Thread thread1 = new T1(); 
		thread1.start(); 
	}
}
