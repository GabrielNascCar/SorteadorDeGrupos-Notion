package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o caminho do arquivo:");
        String path = sc.nextLine();
        // String path = "c:\\Projetos\\ListaNomes.txt";

        System.out.println("Digite a quantidade de integrantes do grupo:");
        int integrantes = sc.nextInt();

        List<String> listaNomes = new ArrayList<>();

        try(BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line = bf.readLine();

            while (line != null) {
                line = bf.readLine();
                listaNomes.add(line);
            }

            List<List<String>> grupos = dividirEmGrupos(listaNomes, integrantes);

            for (int i = 0; i < grupos.size(); i++) {
                System.out.println("Grupo " + (i + 1) + ": " + grupos.get(i));
            }

        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static List<List<String>> dividirEmGrupos(List<String> listaNomes, int integrantes) {
        Collections.shuffle(listaNomes);

        List<List<String>> grupos = new ArrayList<>();
        int totalGrupos = listaNomes.size() / integrantes;
        int restante = listaNomes.size() % integrantes;

        int indice = 0;

        for (int i = 0; i < totalGrupos; i++) {
            List<String> grupo = new ArrayList<>();
            for (int j = 0; j < integrantes; j++) {
                grupo.add(listaNomes.get(indice));
                indice++;
            }
            grupos.add(grupo);
        }

        if (restante > 0){
            List<String> novoGrupo = new ArrayList<>();
            for (int j = 0; j < integrantes; j++) {
                if (indice < listaNomes.size()){
                    novoGrupo.add(listaNomes.get(indice++));
                }
            }
            grupos.add(novoGrupo);
        }

        return grupos;

    }

}