package jogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class ContainerDeJanelas extends JFrame{
	
	public ContainerDeJanelas(){
		
		add(new Fase());
		setTitle("JogoNave");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ContainerDeJanelas();
	}
}