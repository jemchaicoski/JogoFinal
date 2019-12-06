package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Fundo fundo;
	private Fundo fundo2;
	private Nave nave;
	private Timer timer;
	private Boss boss;
	private boolean isBossCalled = false;

	private boolean emJogo;

	private List<Inimigo> inimigos;

	private int[][] coordenadas = { { 950, 0 }, { 950, 50 }, { 950, 100 }, { 950, 150 }, { 950, 200 }, { 950, 250 },
			{ 950, 300 }, { 950, 350 }, { 1050, 0 }, { 1050, 50 }, { 1050, 100 }, { 1050, 150 }, { 1050, 200 },
			{ 2050, 250 }, { 2050, 300 }, { 2050, 350 }, { 2350, 0 }, { 2350, 50 }, { 2450, 100 }, { 2350, 150 },
			{ 3450, 200 }, { 3550, 250 }, { 3550, 300 }, { 3550, 350 }, { 3750, 0 }, { 3750, 50 }, { 3750, 80 },
			{ 1950, 120 }, { 1950, 230 }, { 2000, 250 }, { 2150, 275 }, { 2150, 320 }, { 2950, 0 }, { 2950, 50 },
			{ 2950, 90 }, { 2950, 130 }, { 2550, 200 }, { 2350, 230 }, { 2450, 260 }, { 3050, 373 }, { 3322, 0 },
			{ 2888, 32 }, { 2550, 120 }, { 2052, 64 }, { 2550, 264 }, { 2647, 285 }, { 2000, 311 }, { 2687, 347 },
			{ 2270, 341 }, { 2787, 247 }};
	private boolean youWin = false;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());

		fundo = new Fundo();
		fundo2 = new Fundo();
		nave = new Nave();
		boss = new Boss();
		
		emJogo = true;

		inicializaInimigos();

		timer = new Timer(5, this);
		timer.start();

	}
 
	public void inicializaInimigos() {

		inimigos = new ArrayList<Inimigo>();
		boolean primeira = true;
		if (primeira == true) {
			primeira = false;
			for (int i = 0; i < coordenadas.length; i++) {
				inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
			}

		} else {
		
			for (int i = 0; i < coordenadas.length; i++) {
				inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
				inimigos.get(i).setVelocidade(8);
			}
			
		}
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo.getImagem(), fundo.getX(), fundo.getY(), null);
		graficos.drawImage(fundo2.getImagem(), fundo2.getX()-900, fundo2.getY(), null);
		

		if (emJogo) {

			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
			fundo.mexer();
			fundo2.mexer();
			List<Missel> misseis = nave.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {

				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

			}

			for (int i = 0; i < inimigos.size(); i++) {

				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}

			graficos.setColor(Color.RED);
			graficos.drawString("INIMIGOS: " + inimigos.size(), 15, 15);

			graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);

		} else {
			if (youWin) {
				ImageIcon fimJogo = new ImageIcon("res\\youWin.png");
				graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			}else {
				ImageIcon fimJogo = new ImageIcon("res\\GameOver.png");
				graficos.drawImage(fimJogo.getImage(), 60, -100, null);
			}
			

		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (inimigos.size() == 0 && !isBossCalled) {
			isBossCalled = true;
			boss.anda();
			inicializaInimigos();

		}

		if (boss.vida.size() == 0) {
			emJogo = false;
			youWin  = true;
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}

		}

		nave.mexer();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {

		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
		Rectangle formaBoss;

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {

				nave.setVisivel(false);
				tempInimigo.setVisivel(false);

				emJogo = false;

			}

		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			formaBoss = boss.getBounds();
			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {

				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {

					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);

				}

			}

		}
		formaBoss = boss.getBounds();
		for (int i = 0; i < misseis.size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			int aumenta = 0;

			if (formaBoss.intersects(formaNave)) {
				nave.setVisivel(false);
				boss.setVisivel(false);

				emJogo = false;
			}

			if (formaBoss.intersects(formaMissel) && (boss.vida.size() != 0)) {
				boss.vida.remove(aumenta);
				aumenta++;
				tempMissel.setVisivel(false);
				System.out.println(boss.vida.size());
			}

		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				youWin = false;
				emJogo = true;
				nave = new Nave();
				inicializaInimigos();
				boss = new Boss();
				isBossCalled = false;
			}

			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}
	
}