package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	protected Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int larguraTela = 900;
	private static float velocidade = (float) 1.2;

	private static int contador = 0;
	
	public Inimigo(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon referencia;

		if(contador++ % 3 == 0){
			referencia = new ImageIcon("res\\Giant_Fly.gif");
		
		} else {
			
			referencia = new ImageIcon("res\\MonstroVoadorEspaco.gif");
		}
		setImagem(referencia.getImage());
		
		this.largura = getImagem().getWidth(null);
		this.altura = getImagem().getHeight(null);

		isVisivel = true;
	}

	public void mexer(){

		if(this.x < 0){
			this.x = larguraTela;
			
		} else {
			this.x -= velocidade;
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

	public static void setVelocidade(int velocidade) {
		Inimigo.velocidade = velocidade;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}
	
	
}