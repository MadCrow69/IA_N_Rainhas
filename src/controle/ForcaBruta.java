/*Ideia principal do codigo tirado do link:
* https://gist.github.com/marcoscastro/501e816d6bb42a104eba
*/

package controle;

public class ForcaBruta {
	
	
	public static String msg = "";
	public static int sol = 0;
	
	
	static void mostrarTabuleiro(int[][] tab, int N)		// Função para mostrar o tabuleiro gerado.
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(tab[i][j] == 1){
					//System.out.println(cout + "R\t");
					msg = msg + "R\t";
				}
				else{
					//System.out.println(cout + "-\t");
					msg = msg +"-\t";
				}
			}
			//System.out.println("\n");
			msg = msg +"\n\n";
		}
		//System.out.println("\n");
		msg = msg +"\n";
	}

	
	// verifica se é seguro colocar a rainha numa determinada coluna
	private static boolean seguro(int[][] tab, int N, int lin, int col)
	{
		int i, j;

		// verifica ataque na linha
		for(i = 0; i < N; i++)
		{
			if(tab[lin][i] == 1)
				return false;
		}

		//verifica ataque na coluna
		for(i = 0; i < N; i++)
		{
			if(tab[i][col] == 1)
				return false;
		}

		// verifica ataque na diagonal principal
		// acima e abaixo
		for(i = lin, j = col; i >= 0 && j >= 0; i--, j--)
		{
			if(tab[i][j] == 1)
				return false;
		}
		for(i = lin, j = col; i < N && j < N; i++, j++)
		{
			if(tab[i][j] == 1)
				return false;
		}

		// verifica ataque na diagonal secundária
		// acima e abaixo
		for(i = lin, j = col; i >= 0 && j < N; i--, j++)
		{
			if(tab[i][j] == 1)
				return false;
		}
		for(i = lin, j = col; i < N && j >= 0; i++, j--)
		{
			if(tab[i][j] == 1)
				return false;
		}

		// Caso não de ataque ele retorna como verdadeiro
		return true;
	}
	
/*
	função que resolve o problema
	retorna true se conseguiu resolver e false caso contrário
*/
 private static void executar(int[][] tab, int N, int col)
	{
		if(col == N)
		{
			//System.out.println("Solucao " + sol + 1 + ":\n\n");
			msg = msg + "Solucao " + (sol + 1) + ":\n\n";
			mostrarTabuleiro(tab, N);
			sol++;
			return;
		}
	
		for(int i = 0; i < N; i++)
		{
			// verifica se é seguro colocar a rainha naquela coluna
			if(seguro(tab, N, i, col))
			{
				// insere a rainha (marca com 1)
				tab[i][col] = 1;
	
				// chamada recursiva
				executar(tab, N, col + 1);
	
				// remove a rainha (backtracking)
				tab[i][col] = 0;
			}
		}
	}
 
 public static String rodaForcaBruta(int N) {

	// tabuleiro (matriz)
 	msg = "";		// Zera msg
 	sol = 0;		// Zera contador
	int[][] tab = new int[N][N];


	// inserindo todas as linhas
	for (int l = 0; l < N; l++){
		for(int c = 0; c < N; c++){
			tab[l][c] = 0;
		}
	}

	// imprime todas as soluções
	executar(tab, N, 0);

	// imprime a quantidade de soluções
	//System.out.println("\nEncontradas " + sol + " solucoes!\n");
	//msg = msg + "Encontradas " + sol + " solucoes!";
	if (msg == ""){
		return "Sem solução";
	}
	return msg;
	}
	
}
