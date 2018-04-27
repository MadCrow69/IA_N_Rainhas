package controle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by mrsfy on 07-Jun-17.
 * Tirado de base de https://github.com/selimfirat/ai-n-queens
 */
public class GeneticAlgorithm {


    public int[] solve(int n, int populationSize, double mutationProbability, int numOfGenerations) {

        populationSize = populationSize - (populationSize % 2); 	// cada um deve ter um par.

        int[][] population = generatePopulation(n, populationSize);

        int maxFitness = getMaxFitness(n);

        for (int x = 0; x < numOfGenerations; x++) {

            population = getSelectedPopulation(population);

            population = handleCrossovers(population, n);

            for (int i = 0; i < populationSize; i++) {

                if (getFitness(population[i]) == maxFitness)
                    return population[i];

                population[i] = tryToMutate(population[i], mutationProbability);
                if (getFitness(population[i]) == maxFitness)
                    return population[i];
            }

        }

        return null;
    }

    private int[][] handleCrossovers(int[][] population, int n) {
        for (int i = 0; i < population.length; i += 2) {

            int crossoverPos = (int) (Math.random() * n);

            for (int j = 0; j < crossoverPos; j++) {
                int tmp = population[i][j];
                population[i][j] = population[i+1][j];
                population[i+1][j] = tmp;
            }

        }
        return population;
    }

    private int[][] getSelectedPopulation(int[][] population) {			// Retorna a população gerada
        Arrays.sort(population, Comparator.comparingInt(this::getFitness));

        return population;
    }

    private int[] tryToMutate(int[] r, double mutationProbability) {	// Tenta uma nova mutação
        if (satisfyProb(mutationProbability))
            r[(int)(Math.random()*r.length)] = (int)(Math.random()*r.length);

        return r;
    }

    private boolean satisfyProb(double prob) {
        return prob >= Math.random();
    }

    private int getFitness(int[] r) {	// 
        return getMaxFitness(r.length) - SolverUtils.getHeuristicCost(r);
    }

    private int getMaxFitness(int n) {		// 
        return n*(n-1)/2;
    }

    private int[] generateChromosome(int n) {		// Gera o cromossomo
        return SolverUtils.generateRandomState(n);
    }

    private int[][] generatePopulation(int n, int populationSize) {		// Gera a população
        int[][] population = new int[populationSize][];
        for (int i = 0; i < populationSize; i++)
            population[i] = generateChromosome(n);

        return population;
    }
}
