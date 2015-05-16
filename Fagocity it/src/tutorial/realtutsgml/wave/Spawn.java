package tutorial.realtutsgml.wave;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private int timer = 0;
	private Random r = new Random ();
	private int x = 100;
	
	
	/*construtor*/
	public Spawn(Handler handler)
	{
		this.handler = handler;	
	}
	
	public void CriaJogador () //Cria o jogador e o coloca na tela

	{
		handler.addObjeto(new Jogador(Fagocity.LARGURA/2 -32/2, Fagocity.ALTURA/2 -32/2, ID.Jogador, handler));
	}
	
	public void tick()
	{
		timer++;
		
		/*spawna um inimigo, em intervalos cada vez maiores*/
		if (timer % x == 0)
		{
			handler.addObjeto(new InimigoSeguidor(r.nextInt(Fagocity.LARGURA - 50), r.nextInt(Fagocity.ALTURA - 50),
			ID.Inimigo, handler ));
			x += x;//aumenta o intervalo para spawn
		}
	}
}
