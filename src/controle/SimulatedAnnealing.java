package controle;
/**
 * Created by mrsfy on 05-Jun-17.
 * Tirado de base de https://github.com/selimfirat/ai-n-queens
 */
public class SimulatedAnnealing {

    public static int[] solve(int n, int maxIteracoes, double temperature, double coolingFactor) {
        int[] r = SolverUtils.generateRandomState(n);

        int costToBeat = SolverUtils.getHeuristicCost(r);

        // Termina quando atingir o número máximo de iterações ou o problema for resolvido.
        for (int x = 0; x < maxIteracoes && costToBeat > 0; x++) {
            r = makeMove(r, costToBeat, temperature);
            costToBeat = SolverUtils.getHeuristicCost(r);
            temperature = Math.max(temperature * coolingFactor, 0.01);
        }

        return costToBeat == 0 ? r : null; // Retorna solução se encontrada
    }

    private static int[] makeMove(int r[], int costToBeat, double temp) {
        int n = r.length;

        while (true) {
            int nCol = (int) (Math.random() * n);

            int nRow = (int) (Math.random() * n);

            int tmpRow = r[nCol];
            r[nCol] = nRow;

            int cost = SolverUtils.getHeuristicCost(r);
            if (cost < costToBeat)
                return r;

            int dE = costToBeat - cost;
            double acceptProb = Math.min(1, Math.exp(dE / temp));

            if (Math.random() < acceptProb)
                return r;

            r[nCol] = tmpRow;
        }


    }

}
