package view;

import controle.*;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField qtdR;
	public int indiceSelecionado = 0;		// Variavel para verificar qual opção foi selecionada
	public int qtdRainha = 8;				// Variavel para a quantidade de rainhas por padrão.
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("N-Rainhas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{128, 117, 128, 0};
		gbl_contentPane.rowHeights = new int[]{23, 24, 0, 296, 14, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		JComboBox comboBoxIA = new JComboBox();
		comboBoxIA.setModel(new DefaultComboBoxModel(new String[] {"- Sem IA - For\u00E7a Bruta (Tentativa e erro)", "- Com IA - Hill Climbing", "- Com IA - Simulated Anneling", "- Com IA - Algoritmo Gen\u00E9tico"}));
		GridBagConstraints gbc_comboBoxIA = new GridBagConstraints();
		gbc_comboBoxIA.gridwidth = 2;
		gbc_comboBoxIA.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxIA.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxIA.gridx = 0;
		gbc_comboBoxIA.gridy = 0;
		contentPane.add(comboBoxIA, gbc_comboBoxIA);
		
		JButton btnExecutar = new JButton("Executar");
		GridBagConstraints gbc_btnExecutar = new GridBagConstraints();
		gbc_btnExecutar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExecutar.anchor = GridBagConstraints.NORTH;
		gbc_btnExecutar.insets = new Insets(0, 0, 5, 0);
		gbc_btnExecutar.gridx = 2;
		gbc_btnExecutar.gridy = 0;
		contentPane.add(btnExecutar, gbc_btnExecutar);
		
		JLabel lblQuantidadeRainhas = new JLabel("Quantidade Rainhas:");
		GridBagConstraints gbc_lblQuantidadeRainhas = new GridBagConstraints();
		gbc_lblQuantidadeRainhas.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidadeRainhas.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidadeRainhas.gridx = 0;
		gbc_lblQuantidadeRainhas.gridy = 2;
		contentPane.add(lblQuantidadeRainhas, gbc_lblQuantidadeRainhas);
		
		qtdR = new JTextField();
		qtdR.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {	// Valida que o vamor minimo de rainhas seja 4 (quantidade minima para existir solução)
				if (qtdR.getText() == ""){
					qtdR.setText("4");
				}
				if (qtdR.getText() == null){
					qtdR.setText("4");
				}
				
				Boolean erro = true;
				try {
					int total = Integer.parseInt(qtdR.getText());
					if ( total < 4){ 		// Se numero for menor que 4 ele preenche automaticamente 4		
						qtdR.setText("4");					
					}
					erro = false;				       
				} catch (NumberFormatException e){qtdR.setText("4");erro = true;}	
				
			}
		});
		qtdR.setText("8");
		qtdR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {	//Validador para não ter algo diferente de numeros digitado
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
				e.consume();
				}				
			}
		});
		GridBagConstraints gbc_qtdR = new GridBagConstraints();
		gbc_qtdR.fill = GridBagConstraints.HORIZONTAL;
		gbc_qtdR.insets = new Insets(0, 0, 5, 5);
		gbc_qtdR.gridx = 1;
		gbc_qtdR.gridy = 2;
		contentPane.add(qtdR, gbc_qtdR);
		qtdR.setColumns(10);
		
		
		TextArea textExibicao = new TextArea();
		textExibicao.setEditable(false);
		textExibicao.setText("Selecione o metodo desejado e entao clique em EXECUTAR");
		GridBagConstraints gbc_textExibicao = new GridBagConstraints();
		gbc_textExibicao.fill = GridBagConstraints.BOTH;
		gbc_textExibicao.insets = new Insets(0, 0, 5, 0);
		gbc_textExibicao.gridwidth = 3;
		gbc_textExibicao.gridx = 0;
		gbc_textExibicao.gridy = 3;
		contentPane.add(textExibicao, gbc_textExibicao);
		
		JLabel lblDiegoOAntunes = new JLabel("Diego O. Antunes 7 SIAN");
		GridBagConstraints gbc_lblDiegoOAntunes = new GridBagConstraints();
		gbc_lblDiegoOAntunes.anchor = GridBagConstraints.NORTH;
		gbc_lblDiegoOAntunes.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDiegoOAntunes.gridwidth = 3;
		gbc_lblDiegoOAntunes.gridx = 0;
		gbc_lblDiegoOAntunes.gridy = 4;
		contentPane.add(lblDiegoOAntunes, gbc_lblDiegoOAntunes);
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			// Tratamento do botão de execução
				
				textExibicao.setText(comboBoxIA.getSelectedItem().toString());	// Exibe no textPanel o metodo utilizado
				
				indiceSelecionado = comboBoxIA.getSelectedIndex();	// Pega a posição do metodo selecionado
				
				qtdRainha = Integer.parseInt(qtdR.getText());		// Converte para inteiro a quantidade de rainhas escolhido.

				switch (indiceSelecionado){		// Exibe no textPanel o resultado do metodo escolhido
				case 0:
					textExibicao.setText(forcaBruta());		
					break;
				case 1:					
					textExibicao.setText(HillClimb());
					break;
				case 2:
					textExibicao.setText(SimulatedAnnealing());
					break;
				case 3:
					textExibicao.setText(AlgoritmoGenetico());
					break;					
				}

			}
		});
		
	}
	
	private String forcaBruta(){			// Função para chamada da solução por tentativa e erro
		return new controle.ForcaBruta().rodaForcaBruta(qtdRainha);
	}
	
	private String HillClimb(){				// Função para chamada da solução por Hill Climb
		Boolean erro = true;
		int iteracao = 50000;
		do{
			String it = JOptionPane.showInputDialog("Qual o numero maximo de iterações?", iteracao);			
			if(it != null){						
				try {
					iteracao = Integer.parseInt(it);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor iteração Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		return tabuleiro(controle.HillClimbing.solve(qtdRainha, iteracao));
	}
	
	private String SimulatedAnnealing(){	// Função para chamada da solução por Simulated Annealing
		Boolean erro = true;
		int iteracao = 50000;
		int temperatura = 120;
		double fatorRefrigeracao = 0.95;
		
		do{
			String it = JOptionPane.showInputDialog("Qual o numero maximo de iterações?", iteracao);			
			if(it != null){						
				try {
					iteracao = Integer.parseInt(it);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor iteração Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		do{
			String tmp = JOptionPane.showInputDialog("Qual a temperatura?", temperatura);

			if(tmp != null){						
				try {
					temperatura = Integer.parseInt(tmp);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor Temperatura Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
			
		}while(erro);
		
		do{
			String fr = JOptionPane.showInputDialog("Qual o fator de refrigeração?", fatorRefrigeracao);
			
			
			if(fr != null){						
				try {
					fatorRefrigeracao = Double.parseDouble(fr);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor Fator Refrigeração Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		return tabuleiro(controle.SimulatedAnnealing.solve(qtdRainha, iteracao, temperatura, fatorRefrigeracao));
	}

	private String AlgoritmoGenetico(){		// Função para chamada da solução por Algoritmo Genetico
		Boolean erro = true;
		int populacao = 10;
		double mutacao = 0.5;
		int geracoes = 50000;
		
		do{
			String it = JOptionPane.showInputDialog("Qual o tamanho da população?", populacao);			
			if(it != null){						
				try {
					populacao = Integer.parseInt(it);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor população Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		do{
			String it = JOptionPane.showInputDialog("Qual a taxa de mutação?", mutacao);			
			if(it != null){						
				try {
					mutacao = Double.parseDouble(it);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor mutação Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		do{
			String it = JOptionPane.showInputDialog("Quantas gerações?", geracoes);			
			if(it != null){						
				try {
					geracoes = Integer.parseInt(it);
					erro = false;				       
				} catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Valor mutação Invalido");erro = true;}
				
			}
			else {
				return "Operação cancelada";
			}
		}while(erro);
		
		
		return tabuleiro(new controle.GeneticAlgorithm().solve(qtdRainha, populacao, mutacao, geracoes));
	}
	
	private String tabuleiro(int[] posRainha){		//Cria a String que será usada para exibir o tabuleiro no textPanel
		String msg = "";
		
		if (posRainha != null){						
			for(int x: posRainha){
				for(int y = 0; y < qtdRainha ; y++){
					if(x == y){
						msg = msg + "R\t";
					}
					else {
						msg = msg +"-\t";
					}
				}
				msg = msg +"\n\n";
			}
			return msg;
			}
			else{
				return "Sem Solução";
			}
	}

}

