package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Boss {
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private boolean isVisivel;
	ArrayList vida = new ArrayList();

	private Image imagem;

	private int movimento = 1;

	public Boss() {

		ImageIcon referencia = new ImageIcon("res\\Boss.gif");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

		for (int i = 0; i < 100; i++) {
			vida.add(i);
			System.out.println(i);
		}

		this.x = 950;
		this.y = 0;

	}

	public void Morte() {
		ImageIcon nova = new ImageIcon("\\BossDeath.gif");
		setImage(nova.getImage());
	}

	public ArrayList getVida() {
		return vida;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 250, y + 70, largura, altura);// valores para ajustar o rtangulo do boss na tela
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImage(Image imagem) {
		this.imagem = imagem;
	}

	public void andaAux() {
		x -= movimento;
	}

	public void anda() {
		Timer timer = new Timer();
		int interval = 10;
		int delay = 0;

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;

			public void run() {
				if (i <= 600) {
					andaAux();
					i++;
				} else {
					timer.cancel();
					timer.purge();
				}
			}
		}, delay, interval);
	}
}
