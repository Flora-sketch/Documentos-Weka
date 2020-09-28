/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mapab
 */

// IMPORTAÇÕES IMPORTANTES QUE FAZEM FUNCIONAR
import java.util.Scanner;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.converters.ConverterUtils.DataSource;

public class artigo_weka {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
		
// onde o usuário colocou o arquivo que será usado no processo:
        System.out.println("Olá, vamos programar?");
        System.out.println("Localização e nome do arquivo?");
        System.out.println("    obs.: Digite o caminho completo, sem aspas");
        System.out.print("  Resposta: ");
        Scanner scanner = new Scanner(System.in);
        String arquivo = scanner.nextLine(); 
// essa parte serve para encontrar o arquivo que será feito o processo
// o meu arquivo estará no github 

//teste do arquivo e a criação de uma datasource e dataset com ele que serão usados pela biblioteca weka:
        try {
            DataSource ds = new DataSource(arquivo);
            System.out.println("             info: o arquivo deu certo");
           
        } catch (Exception ex) {
            System.err.println("erro: " + ex.getMessage());
            System.out.println("Recarregue o projeto, deu erro");
            System.out.println("    *provavelmente o nome está incorreto* ");
        } 
        DataSource ds = new DataSource(arquivo);
        Instances ins = ds.getDataSet(); 
        
        Scanner in = new Scanner(System.in);
        System.out.println("Quantas colunas há: ");
        System.out.print("  Resposta: ");
        int colunas = in.nextInt();
        // serve para saber quantaas colunas estaremos lidando
        ins.setClassIndex(colunas - 1); // 
        
        MultilayerPerceptron ml = new MultilayerPerceptron();
        ml.buildClassifier(ins);
        // estarei usando multilayer perceptron
        int classe = colunas; // a última coluna será a prevista
        DenseInstance novo = new DenseInstance(classe);
        novo.setDataset(ins);
        
        System.out.println("obs.: caso a informação na coluna não"
                + " importe, digite: 0 "); //caso a coluna for de datas....   
        for (int i = 1; i <= colunas; i++) {
            if (i != classe) {
                System.out.println("coluna " + i + ":");
                System.out.print("  Resposta: ");
                int valor = in.nextInt();
                novo.setValue(i - 1, valor);
            }
        } // aqui os valores  da linha da previsão são adicionados
        
        
        double probabilidade[] = ml.distributionForInstance(novo);
        System.out.println("valor utilizando MultilayerP.:" + " " + probabilidade[0]); 
        // previsão
        

    }
}

