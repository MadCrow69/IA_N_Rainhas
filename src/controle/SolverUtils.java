package controle;

/**
 *  A classe Solver utils fornece alguns métodos auxiliares, como a criação do estado inicial.
 *  Tirado de base de https://github.com/selimfirat/ai-n-queens
 */
public class SolverUtils {


    public static int[] generateAllOneState(int n) { 	// Gera o estado em que todas as rainhas têm linha # 0
    	
        return new int[n];
    }

    
    public static int[] randomizeState(int[] r) {		// Gera o estado randomico

        for (int i = 0; i < r.length; i++)
            r[i] = (int) (Math.random() * r.length);

        return r;
    }

    
    public static int[] generateRandomState(int n) { 	// Gera o estado inicial aleatório

        return randomizeState(generateAllOneState(n));
    }

    
    public static int getHeuristicCost(int[] r) {		// Retorna o custo da Heuristica
        int h = 0;

     // incrementa o custo se duas rainhas estiverem na mesma linha ou na mesma diagonal.
        for (int i = 0; i < r.length; i++)
            for (int j = i + 1; j < r.length; j++)
                if (r[i] == r[j] || Math.abs(r[i] - r[j]) == j - i)
                    h += 1;

        return h;
    }

}
