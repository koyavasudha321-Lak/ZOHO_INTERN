package day1;

import java.util.Scanner;

public class UncommonSum {

    static void printUnCommon(int first[], int f, int second[], int s){
        int i = 0, j = 0, k = 0;
        int sum = 0;
        while(i <= f && j <= s){
            if(first[i] < second[j]){
                //System.out.print(first[i] + " ");
                sum += first[i];
                i++;
                k++;
            }
            else if(first[i] > second[j]){
                //System.out.print(second[j] + " ");
                sum += second[j];
                k++;
                j++;
            }else{
                i++;
                j++;
            }
        }

        while(i <= f){
            //System.out.print(first[i] + " ");
            sum += first[i];
            i++;
            k++;
        }

        while(j <= s){
            //System.out.print(second[j] + " ");
            sum += second[j];
            j++;
            k++;
        }

        System.out.println("Sum of " +k +" UnCommon Elements from two arrays is: " + sum);

    }



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter First array Size: ");
        int f = sc.nextInt();
        int[] first = new int[f];
        System.out.print("Enter elements in First Array ");
        for(int i = 0;i < f;i++)
            first[i] = sc.nextInt();


        System.out.print("Enter Second array Size: ");
        int s = sc.nextInt();
        int[] second = new int[s];
        System.out.print("Enter elements in Second Array ");
        for(int i = 0;i < s;i++)
            second[i] = sc.nextInt();

        printUnCommon(first, f - 1, second, s - 1);




    }
}
