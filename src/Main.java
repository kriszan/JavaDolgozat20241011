import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader =
                new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //2.a feladat
        ArrayList<Bejegyzes> bejegyzesekElso = new ArrayList<Bejegyzes>();
        bejegyzesekElso.add(new Bejegyzes("Dummy me", "Értekezés a valamiről"));
        bejegyzesekElso.add(new Bejegyzes("Dummy Not ME", "Igen"));


        //2.b feladat
        System.out.printf("Kérem adja meg mennyi darab bejegyzést szeretne felvenni");
        try {
            int tmp = Integer.valueOf(bufferedReader.readLine().trim());
            for (int i = tmp; i > 0; i--) {
                bejegyzesekElso.add(new Bejegyzes("Dummy " + String.valueOf(i), "TARTALOM"));
            }
        } catch (Exception e) {
            System.out.println("Nem intet adott meg");
        }

        //2.c feladat
        bejegyzesekElso.addAll(readFile("bejegyzesek.csv"));

        //2.d feladat
        Random random = new Random();
        int likeCount = bejegyzesekElso.size()*6;
        for (Bejegyzes e : bejegyzesekElso) {
            if (likeCount > 0) {
                int tmp = random.nextInt(likeCount);
                likeCount -= tmp;
                e.like(tmp);
            } else break;
        }

        //2.e feladat
        try {
            System.out.println("Kérem adjon meg egy szöveget");
            String tmpTart = bufferedReader.readLine();
            bejegyzesekElso.get(2).setTartalom(tmpTart);
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("2.e hiba??");
            System.out.println(e);
        }

        //2.f feladat
        for (Bejegyzes e : bejegyzesekElso) {
            System.out.println(e);
        }

        //3.a
        Collections.sort(bejegyzesekElso, new Comparator<Bejegyzes>() {
            @Override
            public int compare(Bejegyzes o1, Bejegyzes o2) {
                return o1.getLikeok() > o2.getLikeok() ? 0 : 1;
            }
        });
        System.out.println("3.a: Likeok száma: " + bejegyzesekElso.get(0).getLikeok());

        //3.b
        System.out.println(bejegyzesekElso.stream().filter((Bejegyzes e) -> {
            return e.getLikeok() > 35;
        }).count() > 0 ? "Igen volt ami 35 likeot kapott legalább" : "Nem volt olyan poszt ami 35 likeot kapott volna legalább");

        //3.c
        long tmp = bejegyzesekElso.stream().filter((Bejegyzes e) -> {
            return e.getLikeok() < 15;
        }).count();
        System.out.println(tmp == 0 ? "Minden poszt 15nél több likeot kapott" : "Volt " + tmp + " db poszt ami 15nél kevesebb likeot kapott");

        //3.d
        Collections.sort(bejegyzesekElso, new Comparator<Bejegyzes>() {
            @Override
            public int compare(Bejegyzes o1, Bejegyzes o2) {
                return o1.getLikeok() < o2.getLikeok() ? 0 : 1;
            }
        });
        System.out.println("3 feladat likeok szerint csökkenő sorrendben");
        for (Bejegyzes e : bejegyzesekElso) {
            System.out.println(e);
        }
        writeFile("bejegyzesek_rendezett.txt", bejegyzesekElso);
    }

    private static ArrayList<Bejegyzes> readFile(String path) {

        ArrayList<Bejegyzes> bejegyzesekMasodik = new ArrayList<Bejegyzes>();
        try {


            FileReader in = new FileReader(path);
            BufferedReader br = new BufferedReader(in);

            String line;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(";");
                bejegyzesekMasodik.add(new Bejegyzes(tmp[0], tmp[1]));
            }
            in.close();
        } catch (Exception e) {
        }
        return bejegyzesekMasodik;
    }

    private static void writeFile(String filename, ArrayList<Bejegyzes> lista) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Bejegyzes e : lista) {

                writer.write(e.toString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Writing error");
        }
    }

}