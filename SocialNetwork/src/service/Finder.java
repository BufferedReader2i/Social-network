package service;

import entity.Person;

import java.util.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class Finder {
    public static double[][] adjMatrix;
    public static List<String> total;
    public static ArrayList<ArrayList<String>> allPaths;
    public String s;
    public int []path;
    public List<Person> people;
    public  int standard;
    public Finder(Reader reader){
       this.adjMatrix=reader.adj;
       this.total=reader.total;
       this.people=reader.people;
       this.standard=25;
    }

    //联系路径
    public ArrayList<ArrayList<String>> AllPaths(String start, String target){

        this.allPaths=new ArrayList<>();
        ArrayList<String> path=new ArrayList<>();
        int begin=total.indexOf(start);
        int end=total.indexOf(target);
        dfs(begin,end,path);
        return allPaths;

    }

    private static void dfs(int cur, int target, ArrayList<String> path) {
        if (cur==target){
            allPaths.add(new ArrayList<>(path));


            return;
        }
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[cur][i]!=0&&!path.contains(total.get(i))){



                if (path.size()<6) {
                    path.add(total.get(i));

                dfs(i,target,path);
                path.remove(path.size()-1);}
            }
        }



    }
    public int MinPeople(String start,String target){
        int begin=total.indexOf(start);
        int end=total.indexOf(target);

        int min=bfs(begin,end);
        return min;

    }

    private int bfs(int begin, int end) {


        Queue<Integer> qu=new LinkedList<>();
        boolean []visited=new boolean[adjMatrix.length];
        int []distance=new int[adjMatrix.length];
        qu.offer(begin);
        path=new int[adjMatrix.length];
        path[begin]=begin;

        visited[begin]=true;
        distance[0]=0;
        while (!qu.isEmpty()){
            int cur=qu.poll();
            if (cur==end){
                s=""+total.get(cur);
                int tmp=path[cur];


                s=total.get(tmp)+"--"+s;
                while (tmp!=begin){
                    tmp=path[tmp];
                    s=total.get(tmp)+"--"+s;

                }


                if(distance[cur]==0){return 0;}

                return distance[cur]-1;
            }
            for (int i = 0; i <adjMatrix.length ; i++) {
                if (adjMatrix[cur][i]!=0&&!visited[i]){
                    qu.offer(i);


                    visited[i]=true;
                    distance[i]=distance[cur]+1;
                    path[i]=cur;



                }
            }

        }
        return -1;
    }
    public String getMinpath(String start,String end){
        path=new int[adjMatrix.length];
        int cur=total.indexOf(end);
        cur=path[cur];
        System.out.println(total.get(cur));
        String minpath="";

        while (true){
            if (cur==total.indexOf(start)){
                minpath=total.get(cur)+"-"+minpath;
                break;
            }
            minpath=total.get(cur)+"-"+minpath;

            cur=path[cur];

        }
        return minpath;
    }
public ArrayList<String> nearBy(String name){
    ArrayList<String> names=new ArrayList<>();
    for (Person person:people){

        Person cur=people.get(total.indexOf(name));
        int first=(int)Math.pow(cur.getX()-person.getX(),2);
        int second=(int)Math.pow(cur.getY()-person.getY(),2);

        int distance=(int)Math.sqrt(first+second);

        if (distance<=200&&name!=person.getName()){


            names.add(person.getName());
        }
    }
    names.remove(name);
    return names;
}
//经过一次中间人能联系到的人
public ArrayList<String> oneMiddleMan(String name){
        Queue<Integer> qu=new LinkedList<>();
        int cur=total.indexOf(name);
    ArrayList<String> names=new ArrayList<>();
    for (int i = 0; i < adjMatrix.length; i++) {
        if (adjMatrix[cur][i]!=0){
            qu.offer(i);
        }
    }
    while (!qu.isEmpty()){
        int mid= qu.poll();
        for (int i = 0; i <adjMatrix.length ; i++) {
            if (adjMatrix[mid][i]!=0&&!names.contains(total.get(i))){
                names.add(total.get(i));
            }
        }
    }
    return names;
}
public String corePerson(){
    int[] degrees = new int[adjMatrix.length];

    for (int i = 0; i < adjMatrix.length; i++) {
        for (int j = 0; j < adjMatrix.length; j++) {
            if (adjMatrix[i][j]!=0) {
                degrees[i]++;
                degrees[j]++;
            }
        }
    }

    // 找到核心人物、边缘人物和活跃人物
    List<String> corePeople = new ArrayList<>();


    List<String> edgePeople = new ArrayList<>();
    List<String> activePeople = new ArrayList<>();
    HashMap<String, Integer> hashMap1 = new HashMap<>();
    HashMap<String, Integer> hashMap2 = new HashMap<>();
    HashMap<String, Integer> hashMap3 = new HashMap<>();








   int sum=0;
    int minDegree=Integer.MAX_VALUE;
    int max=0;
    for (int i = 0; i < degrees.length; i++) {
        sum+=degrees[i];
        max=Math.max(max,degrees[i]);
        minDegree=Math.min(minDegree,degrees[i]);
    }
    double averageDegree = sum/115.00;
    System.out.println(max);



    for (int i = 0; i < adjMatrix.length; i++) {
        if (degrees[i] >this.standard) {
            corePeople.add(total.get(i));
            hashMap1.put(total.get(i),degrees[i]);
        }
        if (degrees[i] == minDegree ) {
            edgePeople.add(total.get(i));
            hashMap3.put(total.get(i),degrees[i]);
        }

        if (degrees[i] > averageDegree ) {
            activePeople.add(total.get(i));
            hashMap2.put(total.get(i),degrees[i]);
            if (corePeople.contains(total.get(i))){
                activePeople.remove(total.get(i));
                hashMap2.remove(total.get(i));
            }
        }
    }



    List<Map.Entry<String, Integer>> entryList1 = new ArrayList<>(hashMap1.entrySet());

    // 使用自定义比较器按值进行排序
    Collections.sort(entryList1, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    });


    List<Map.Entry<String, Integer>> entryList2 = new ArrayList<>(hashMap2.entrySet());

    // 使用自定义比较器按值进行排序
    Collections.sort(entryList2, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    });


    List<Map.Entry<String, Integer>> entryList3 = new ArrayList<>(hashMap3.entrySet());

    // 使用自定义比较器按值进行排序
    Collections.sort(entryList3, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    });
    int i=0;
    String s="当前核心人物阈值为:"+standard+"\n核心人物:\n";
    for (Map.Entry<String, Integer> entry : entryList1) {
        s+=entry.getKey()+" ("+entry.getValue()+")\n";

    }



    s+="\n";
    s+="活跃人物：\n";
    i=0;

    for (Map.Entry<String, Integer> entry : entryList2) {
        s+=entry.getKey()+" ("+entry.getValue()+")\n";

    }
    s+="\n";
    s+="边缘人物：\n";
    i=0;
    for (Map.Entry<String, Integer> entry : entryList3) {
        s+=entry.getKey()+" ("+entry.getValue()+")\n";

    }


    return s;
}
    public void setStandard(int standard){
        this.standard=standard;

    }
    public boolean contains(String name){
        return total.contains(name);
    }
    public double FindX(String name){
        return people.get(total.indexOf(name)).getX();
    }
    public double FindY(String name){
        return people.get(total.indexOf(name)).getY();
    }
    public void setX(String name,double X){
        people.get(total.indexOf(name)).setX(X);
    }
    public void setY(String name,double Y){
        people.get(total.indexOf(name)).setY(Y);
    }

}
