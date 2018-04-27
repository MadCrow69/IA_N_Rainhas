package controle;
import java.util.Arrays;

/**
 * Created by mrsfy on 05-Jun-17.
 * Tirado de base de https://github.com/selimfirat/ai-n-queens
 */
public class HillClimbing {

    // Faz um movimento para subida
    public static int[] firstChoiceHillClimbing(int n, int maxNumOfIterations) {
        int[] r = SolverUtils.generateRandomState(n);
        int costToBeat = SolverUtils.getHeuristicCost(r);

        // Termina quando atingi o número máximo de iterações ou o problema for solucionado.
        for (int x = 0; x < maxNumOfIterations && costToBeat > 0; x++) {

            boolean flag = true;
            int tempCostToBeat = costToBeat;
            for (int col = 0; col < n && flag; col++) {

                for (int row = 0; row < n; row++) {
                	// Não precisa avaliar porque já se sabe o custo atual por costToBeat.
                    if (row == r[col]){
                        continue;
                    }

                    // init new copy
                    int[] rc = Arrays.copyOf(r, n);
                    rc[col] = row;
                    int cost = SolverUtils.getHeuristicCost(rc);
                    if (costToBeat > cost) {
                        r[col] = row;
                        costToBeat = cost;
                        flag = false;
                        break;
                    }
                }
            }

            // Se ficar preso no local máximo
            if (tempCostToBeat == costToBeat){
                r = SolverUtils.generateRandomState(n);
            }
        }
        return costToBeat == 0 ? r : null; // return solution if solved
    }


    public static int[] solve(int n, int maxNumOfIterations) {

        return firstChoiceHillClimbing(n, maxNumOfIterations);
    }

}
