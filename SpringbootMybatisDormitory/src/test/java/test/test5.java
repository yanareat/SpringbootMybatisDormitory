package test;

import java.util.ArrayList;

public class test5 {
    public static void sort(ArrayList<Integer> a){
        Integer temp;
        for(Integer i=1;i<a.size();i++){
            for(Integer j=0;j<a.size()-1;j++){
                if(a.get(j)<a.get(i)&&i-j>1){
                    temp=a.get(i);
                    a.remove(i);
                    a.add(j+1,temp);
                }
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> a=new ArrayList<>();
        for (Integer i=9;i>-1;i++){
            a.add(i);
        }
        System.out.println(a.toString());
        sort(a);
        System.out.println(a.toString());
    }
}
