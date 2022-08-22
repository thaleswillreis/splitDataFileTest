/* Fazer um programa para ler o caminho de um arquivo .CSV contendo os dados do Senso do IBGE DE 2010 A 2014 (Arquivo anexo).
Cada item possui (ANO, COD_UF, ESTADO, COD_MUN, NOME_MUN, REGIAO, COD_MESO_REG, NOME_MESO_REG,
COD_MIC_REG, NOME_MIN_REG, PIB, POPULACAO, PIB_PER_CAP), você deve gerar CINCO novos arquivos (Um para cada ANO)
chamados "pib2010.csv", "pib2011.csv", "pib2012.csv", "pib2013.csv e "pib2014.csv, todos devem ser alocados
num novo diretório chamado "resultsenso" na mesma pasta do arquivo de origem.
Nesses novos arquivos deverá conter somente (ANO, ESTADO, NOME_MUN, PIB, POPULACAO, PIB_PER_CAP)
================================================================================================================*/

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Senso;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

			//entrada do usuario com o path do arquivo de origem
		System.out.println("Digite o caminho completo para o Arquivo Fonte (extensão .CSV): ");
		String arquivoFonteSc = sc.nextLine();

			//aquisicao do path de entrada e criacao do diretorio de destino
		File arquivoFonteNome = new File(arquivoFonteSc);
		String pastaFonteNome = arquivoFonteNome.getParent();
		boolean novaPasta = new File(pastaFonteNome + "\\resultsenso").mkdir();

			//confirmacao da criacao do diretorio de destino
		System.out.println("Pasta de destino criada :" + novaPasta);
		
			//listas temporarias para separacao de dados
		List<Senso> list2010 = new ArrayList<>();
		List<Senso> list2011 = new ArrayList<>();
		List<Senso> list2012 = new ArrayList<>();
		List<Senso> list2013 = new ArrayList<>();
		List<Senso> list2014 = new ArrayList<>();

			//variaveis guardando o path dos arquivos finais a serem gravados
		String arquivoFinal2010 = pastaFonteNome + "\\resultsenso\\pib2010.csv";
		String arquivoFinal2011 = pastaFonteNome + "\\resultsenso\\pib2011.csv";
		String arquivoFinal2012 = pastaFonteNome + "\\resultsenso\\pib2012.csv";
		String arquivoFinal2013 = pastaFonteNome + "\\resultsenso\\pib2013.csv";
		String arquivoFinal2014 = pastaFonteNome + "\\resultsenso\\pib2014.csv";

			//leitura do arquivo fonte com BufferedReader/FileReader
		try (BufferedReader br = new BufferedReader(new FileReader(arquivoFonteNome))) {

			String municipio = br.readLine();
			while (municipio != null) {

					//quebra da linha e armazenamento numa matriz de string
				String[] campo = municipio.split(",");

					//selecao dos campo de dados a serem copiados para o arquivo de destino
				int ano = Integer.parseInt(campo[0]);
				String estado = campo[2];
				String nome_mun = campo[4];
				double pib = Double.parseDouble(campo[10]);
				int populacao = Integer.parseInt(campo[11]);
				double pib_per_cap = Double.parseDouble(campo[12]);

					//escolha da lista temporaria correta de acordo com o ano
				List<Senso> list = null;
				switch (ano) {
				case 2010:
					list = list2010;
					break;
				case 2011:
					list = list2011;
					break;
				case 2012:
					list = list2012;
					break;
				case 2013:
					list = list2013;
					break;
				case 2014:
					list = list2014;
					break;
				}

					//insercao dos dados na lista temporaria escolhida no switch/case
				list.add(new Senso(ano, estado, nome_mun, pib, populacao, pib_per_cap));
				municipio = br.readLine();
			}

				//chamadas do metodo de gravacao passando os argumentos contendo as listas temporarias e seus respectivos arquivos de destino
			gravacao(arquivoFinal2010, list2010);
			gravacao(arquivoFinal2011, list2011);
			gravacao(arquivoFinal2012, list2012);
			gravacao(arquivoFinal2013, list2013);
			gravacao(arquivoFinal2014, list2014);

		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo de origem: " + e.getMessage());
		}
		sc.close();
	}

		//metodo de gravacao recebendo os argumentos contendo a lista temporaria e seu respectivo arquivo de destino
	public static void gravacao(String arquivoFinal, List<Senso> listAno) {
			//gravacao do arquivo de destino com BufferedWriter/FileWriter
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoFinal, StandardCharsets.UTF_8))) {

			for (Senso municip : listAno) {
				bw.write(municip.getAno() + "," + municip.getEstado() + "," + municip.getNome_mun() + ","
						+ municip.getPib() + "," + municip.getPopulacao() + "," + municip.getPib_per_cap());
				bw.newLine();
					//confirmacao visual da copia dos dados
				System.out.println(municip.getNome_mun() + " COPIADO");
			}
		} catch (IOException e) {
			System.out.println("Erro ao gravar no arquivo de destino: " + e.getMessage());
		}
	}
}
