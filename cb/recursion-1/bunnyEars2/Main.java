/*
Flying Sullen Actors: Salaj Rijal, Alif Rahman, Faiyaz Rafee
APCS
HW 64 -- Revisitation -- Coding Bat Practice
2022-2-14
time: 1 hour
*/

// https://codingbat.com/prob/p120015

public class Main {
    public static void main(String[] args) {
        System.out.println(bunnyEars2(0)); // 0
        System.out.println(bunnyEars2(1)); // 2
        System.out.println(bunnyEars2(2)); // 5
    }

    /*
     * We have bunnies standing in a line, numbered 1,
      2, ... The odd bunnies (1, 3, ..) have the normal
       2 ears. The even bunnies (2, 4, ..) we'll say have
        3 ears, because they each have a raised foot. Recursively
         return the number of "ears" in the bunny line 1, 2,
          ... n (without loops or multiplication).
     */

     public static int bunnyEars2(int bunnies) {
       if (bunnies == 0) {
         return 0;
       }
       if (bunnies % 2 == 0) {
         return 3 + bunnyEars2(bunnies -1);
       } else {
         return 2 + bunnyEars2(bunnies -1);
       }
     }
}
