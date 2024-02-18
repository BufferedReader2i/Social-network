package service;

import entity.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Reader {
    public double [][]adj=new double[115][115];
    public List<Person> people=new ArrayList<>();
    public List<String> total;
    public Reader() {


        try {
            List<List<String>> data = new ArrayList<>();
            String file = "stackoverflow_edges.csv";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                List<String> lineData = Arrays.asList(line.split(","));
                data.add(lineData);
                line = br.readLine();
            }
            Set set=new HashSet<String>();


            for (List<String> list : data) {
                set.add(list.get(0));
                set.add(list.get(1));

            }

             total = new ArrayList<String>(set);
            Collections.sort(total);

            total.remove("source");
            total.remove("target");




            for (List<String> list : data) {
                set.add(list.get(0));
                if (list.get(0).equals("source"))continue;
                int i=total.indexOf(list.get(0));
                int j=total.indexOf(list.get(1));
                double value=Double.valueOf(list.get(2));
                adj[i][j]=value;
            }
            for (String name:total){

                Random random=new Random();
                int x= random.nextInt(2000);
                int y= random.nextInt(2000);

                people.add(new Person(name,x,y));
            }


            br.close();

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}