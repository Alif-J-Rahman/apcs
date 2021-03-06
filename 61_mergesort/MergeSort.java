/***
  class MergeSort
  Implements mergesort on array of ints.
  Summary of Algorithm: MergeSort merges two array given that input arrays are in ascending order and sort sorts array in ascending order
  ***/

  public class MergeSort
  {
    /******************************************************
     * int[] merge(int[],int[])
     * Merges two input arrays
     * Precond:  Input arrays are sorted in ascending order
     * Postcond: Input arrays unchanged, and
     * output array sorted in ascending order.
     ******************************************************/
    private static int[] merge( int[] a, int[] b )
    {
      int[] output = new int[a.length + b.length];
      int a_marker = 0;
      int b_marker = 0;
      
      while (a_marker < a.length && b_marker < b.length) {
         if (a[a_marker] < b[b_marker]) {
             output[a_marker + b_marker] = a[a_marker];
             a_marker += 1;
         }
         else {
             output[a_marker + b_marker] = b[b_marker];
             b_marker += 1;
         }
     }
     while (a_marker < a.length) {
         output[a_marker + b_marker] = a[a_marker];
         a_marker += 1;
     }
     while (b_marker < b.length) {
         output[a_marker + b_marker] = b[b_marker];
         b_marker += 1;
     }
  
     return output;
    }//end merge()
  
  
    /******************************************************
     * int[] sort(int[])
     * Sorts input array using mergesort algorithm
     * Returns sorted version of input array (ascending)
     ******************************************************/
    public static int[] sort( int[] arr )
    {
      if (arr.length == 1) {
          return arr;
      }
  
      int index = arr.length / 2;
      int[] a = new int[index];
      int[] b = new int[arr.length - index];
  
      for (int i = 0; i < a.length; i++) {
        a[i] = arr[i];
      }
  
      for (int i = 0; i < b.length; i++) {
        b[i] = arr[index + i];
      }
  
      int [] x = sort(a);
      int [] y = sort(b);
  
      int[] sorted = merge(x, y);
  
      return sorted;
    }//end sort()
  
  
  
    //-------------------HELPERS-------------------------
    //tester function for exploring how arrays are passed
    //usage: print array, mess(array), print array. Whaddayasee?
    public static void mess( int[] a ) {
      for( int i = 0 ; i<a.length; i++ )
        a[i] = 0;
    }
  
    //helper method for displaying an array
    public static void printArray( int[] a ) {
      System.out.print("[");
      for( int i : a )
        System.out.print( i + ",");
      System.out.println("]");
    }
    //---------------------------------------------------
  
  
    //main method for testing
    public static void main( String [] args )
    {
        int[] arr0 = {0};
        int[] arr1 = {1};
        int[] arr2 = {1,2};
        int[] arr3 = {3,4};
        int[] arr4 = {1,2,3,4};
        int[] arr5 = {4,3,2,1};
        int[] arr6 = {9,42,17,63,0,512,23};
        int[] arr7 = {9,42,17,63,0,9,512,23,9};
        printArray(merge(arr0, arr1));
      
        System.out.println("\nTesting mess-with-array method...");
        printArray( arr3 );
        mess(arr3);
        printArray( arr3 );
        System.out.println("\nMerging arr1 and arr0: ");
        printArray( merge(arr1,arr0) );
        System.out.println("\nMerging arr4 and arr6: ");
        printArray( merge(arr4,arr6) );
        System.out.println("\nSorting arr4-7...");
        printArray( sort( arr4 ) );
        printArray( sort( arr5 ) );
        printArray( sort( arr6 ) );
        printArray( sort( arr7 ) );
        /*~~~~~~~~~~~~~~ Ye Olde Tester Bar ~~~~~~~~~~~~~~
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }//end main()
  
  }//end class MergeSort
  
