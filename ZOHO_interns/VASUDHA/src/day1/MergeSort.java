package day1;

import java.util.Scanner;

public class MergeSort {


    /* Function for merging subarrays of a[] */
    void merge(int a[], int beg, int mid, int end)  {
        int i, j, k;

        /* h = end - beg + 1 gives no. of elements in current subarray*/
        int h = end - beg + 1;
        int[] temp = new int[h];


        i = beg;
        j = mid + 1;
        k = 0;  /* initial index of resultant temp[] */

        while (i <= mid && j <= end) {
            if(a[i] <= a[j]) {
                temp[k++] = a[i++];

            }
            else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= end) {
            temp[k++] = a[j++];

        }

        /* coping all sorted data from temp[] into a[] */
        for(int p = 0;p < h;p++) {
            a[beg + p] = temp[p];
        }
    }

    /*Function for divding subarrays from a[] and for merging*/
    void mergeSort(int a[], int beg, int end) {
        if (beg < end) {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merge(a, beg, mid, end);
        }
    }


    void printArray(int a[], int n) {
        for (int i = 0; i < n; i++){
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size : ");
        int n = sc.nextInt();

        int[] a = new int[n];
        System.out.print("Enter array elements: ");
        for(int i = 0;i < n;i++){
            a[i] = sc.nextInt();
        }
            

        /*creating an object ms for the class MergeSort to access methods*/
        MergeSort ms = new MergeSort();
        System.out.println("\nBefore performing MergeSort array elements are - ");
        ms.printArray(a, n);

        ms.mergeSort(a, 0, n - 1);

        System.out.println("\nAfter Performing MergeSort array elements are - ");
        ms.printArray(a, n);

    }
}

