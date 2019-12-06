package jogo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Fundo {
	Image imagem;
	int x, y;
	float velocidade;
	int larguraTela = 900;

	public Fundo() {
		ImageIcon referencia = new ImageIcon("res\\Fundo2.jpg");
		imagem = referencia.getImage();
		x = 0;
		y = 0;
		velocidade = 2;
	}

	public void mexer() {
			this.x -= velocidade;
			if (x < 0) {
				x = 900;
			}
		}
	

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(float velocidade) {
		this.velocidade = velocidade;
	}

}
