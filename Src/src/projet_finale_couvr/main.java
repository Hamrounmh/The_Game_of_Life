package projet_finale_couvr;

import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Event;

import static java.lang.System.exit;

/**
 * La classe principale qui permet de lancer le jeu de la vie <br/>
 * Elle fournit aussi un menu de commandes pour excecuter les programmes a partir de la console.
 */
public class main {

	public static void main(String[] args)  {
		String help = ". java -jar JeuDeLaVie.jar -name  affiche vos noms et prénoms\n" +
				". java -jar JeuDeLaVie.jar -h  rappelle la liste des options du programme \n" +
				". java -jar JeuDeLaVie.jar -s d fichier.lif  exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération. \n" +
				". java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul. \n" +
				". java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html. \n" +
				". java -jar JeuDeLaVie.jar -circulaire max fichier.lif taille_X Taille_Y \n" +
				". java -jar JeuDeLaVie.jar -fini max fichier.lif taille_X Taille_Y\n";
		if(args.length ==0) {System.out.println(help);exit(1);}
		switch (args[0])
		{
			case "-name":
				System.out.println("Les noms: Hamroun Houssem\n Allouche Karim \n Chatti Oussama \n Chebbah Antar"); break;
			case "-h":
				System.out.println(help);break;
			case "-s": if(args.length!=3) {System.out.println(help);exit(1);}
				try{
					int d = Integer.parseInt(args[1]);
					Jeux jx =new Jeux(args[2]);
					simulerJeu(d, jx);


				}catch(IOException e){System.out.println(e.getMessage()); exit(1);}
				catch(Exception e) { System.out.println(help);exit(1); }
				break;
			case "-c": if(args.length!=3) {System.out.println(help);exit(1);}
				try{
					int d = Integer.parseInt(args[1]);
					System.out.println((new Comportement(args[2],d)).getResultat());
				}catch(IOException e){System.out.println(e.getMessage()); exit(1);}
				catch(Exception e) { System.out.println(help);exit(1); }
				break;
			case "-w":
				if(args.length!=3) {System.out.println(help);exit(1);}
				try{
					int d = Integer.parseInt(args[1]);
					File folder = new File(args[2]);
					File[] fichiers = folder.listFiles();
					File fichierHtml=new File(args[2] +"\\resultat.html");
					if (!fichierHtml.exists()) {
						fichierHtml.createNewFile();
					}
					FileWriter fw =new FileWriter(fichierHtml);
					fw.write("<!doctype html>\n" +
							"<html lang=\"fr\">\n" +
							"<head>\n" +
							"  <meta charset=\"utf-8\">\n" +

							"</head>\n" +
							"<body>");
					for (File fichier:fichiers)
					{
						if (fichier.isFile() && fichier.getName().endsWith(".lif")) {
							fw.write("<p>--"+fichier.getName()+"--"+(new Comportement(fichier.getAbsolutePath(),d)).getResultat() +"</p>");
						}
					}
					fw.write("</body>\n" +
							"</html>");
					fw.close();
				}catch(IOException e){System.out.println(e.getMessage()); exit(1);}
				catch(Exception e) { System.out.println(help);exit(1); }
				break;
			case "-circulaire":
				if(args.length!=5) {System.out.println(help);exit(1);}
				try{
					int d = Integer.parseInt(args[1]);
					int x = Integer.parseInt(args[3]);
					int y = Integer.parseInt(args[4]);
					Jeux jx =new Jeux_Circulaire(args[2],x,y);
					simulerJeu(d, jx);


				}catch(IOException e){System.out.println(e.getMessage()); exit(1);}
				catch(Exception e) { System.out.println(help);exit(1); }
				break;
			case "-fini":
				if(args.length!=5) {System.out.println(help);exit(1);}
				try{
					int d = Integer.parseInt(args[1]);
					int x = Integer.parseInt(args[3]);
					int y = Integer.parseInt(args[4]);
					Jeux jx =new Jeux_Fini(args[2],x,y);
					simulerJeu(d, jx);

				}catch(IOException e){System.out.println(e.getMessage()); exit(1);}
				catch(Exception e) { System.out.println(help);exit(1); }
				break;
			default:System.out.println(help);break;
		}

	}

	private static void simulerJeu(int d, Jeux jx) throws InterruptedException {
		while(!jx.getListe().isEmpty() && d-->=0) {
			Thread.sleep(500);
			try {
				if (System.getProperty("os.name").contains("Windows"))
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				else
					Runtime.getRuntime().exec("clear");
			} catch (IOException | InterruptedException ex) {}

			System.out.println(jx);
			jx.etat_suivant();
		}
	}


}
