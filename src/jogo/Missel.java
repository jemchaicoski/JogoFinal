package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {

	protected Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int larguraTela = 900;
	private static final int velocidade = 10;

	public Missel(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("res\\missel.png");
		setImagem(referencia.getImage());

		this.largura = getImagem().getWidth(null);
		this.altura = getImagem().getHeight(null);
		
		isVisivel = true;
	}

	public void mexer(){
		  
		this.x += velocidade;
		if(this.x > larguraTela-65){//o valor 65 faz com que o tiro n�o chegue dps da tela e mate os inimigos previamente
			isVisivel = false;
		}
		
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	
}
