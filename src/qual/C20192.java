package qual;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class C20192 {

    /*

            Note: considering inputs as int instead of BigInteger gives runtime error even for the smaller test case




            consider first two numbers a and b
            a = x*y and b = y*z (x,y,z being all primes)
            if we find the hcf of a and b, we can get y
            once we get y we can easily get x=(a/y) and z=(b/y)

            now we can just find rest of the numbers in linear as

            c = z*p we have z and c, we find p
            d = p*q we have d and p, we find q.

            so on and so forth

            Now we have x,y,z,p.... we sort them and map them each with the letters starting from A..Z

            *Note:  we should also cache the values a=x*y and reuse if we have "a" as again
            -------------------------------------------------------------------
            Time complexity : O(L) + time for GCD

            -------------------------------------------------------------------
            Time estimation with constraints : N=10^(100)

            -------------------------------------------------------------------

            Mile stone time stamps:
            Far from final solution
            15mins : to read/understand the question and come up with a solution.
            15mins : solution was flawed missed edges cases where the numbers repeated, ABABABAB..will be same as BABABA..

            *****final solution*******:
            find the index(k) of first non-equal product(5 if ABABAA..)
            decrypt the seq from k-1th product as stated above
            for 0 to k-1 go similarly in backwards seq.

            -------------------------------------------------------------------
            Top submission stats : (submitted correct solution (small and large) in 13 mins!!!)
         */

    public static void main(String[] args) {
        /*createTest("ZAZYXWVUTSRQRQRPONMLKJIHGFEDCCBA");//NOT SOLVED
        createTest("ZAZAZYXWVUTSRQPONMLKJIHGFEDCCBA");//NOT Solved
        createTest("AZAYXWVUTSRQPONMLKJIHGFEDCCBA"); //not solved
        createTest("AAAAAAABBBBBBBBABABABCDFEGHIJKLMNOPQRSTUVWZYX");
        createTest("AZBZAZYXWVUTSRQPONMLKJIHGFEDCCBA");

        createTest("Twodrivenjocococockshelpfaxmybigquiz".trim().toUpperCase());
         createTest("Twodrivenjocococockshelpfaxmybigquiz".trim().toUpperCase());
        createTest("Thefiveboxingwizardsjumpquickly".trim().toUpperCase());
        createTest("Brightvixensjumpdozyfowlquack".trim().toUpperCase());
        createTest("Jackdawslovemybigsphinxofquartz".trim().toUpperCase());
        createTest("Johnquicklyextemporizedfivetowbags".trim().toUpperCase());
        createTest("WaltznymphforquickjigsvexBud".trim().toUpperCase());
        createTest("QuickwaftingzephyrsvexboldJim".trim().toUpperCase());
        createTest("Brownjarspreventedthemixturefromfreezingtooquickly".trim().toUpperCase());
        createTest("Fredspecializedinthejobofmakingveryquaintwaxtoys".trim().toUpperCase());
        createTest("NEWJOBFIXMQRGLUCKSHAZYTVPD");
        createTest("Sixtyzipperswerequicklypickedfromthewovenjutebag".trim().toUpperCase());
        createTest("Wepromptlyjudgedantiqueivorybucklesforthenextprize".trim().toUpperCase());
        createTest("BKBKKKKBBBBKBKBKKKKACDEFGHIJMKLMNOPQRSTUVWXZY".trim().toUpperCase());
        createTest("Viewingquizzicalabstractsmixedupheftyjocks".trim().toUpperCase());
        createTest("Farmerjackrealizedthatbigyellowquiltswereexpensive".trim().toUpperCase());
        createTest("Mygirlwovesixdozenplaidjacketsbeforeshequit".trim().toUpperCase());

        createTest("BKBKBKKKBBBBKBKBKKKKACDEFGHIJKLMNOPQRSTUVWXZY");
        createTest("BKBKBKXKBBBBKBKBKKKKACDEFGHIJKLMNOPQRSTUVWXZY");
        createTest("NJNJNJFBFBFBERERABCDEFGHIJKLMNOPQRSTUVWZYX");*/

        solveSmall();
    }
    private static void solveSmall(){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=0;i<t;i++){
            BigInteger n = in.nextBigInteger();
            int producListSize=in.nextInt();
            TreeSet<BigInteger> primeSet = new TreeSet<>();
            ArrayList<BigInteger> primeList= new ArrayList<>();
            BigInteger[] products = new BigInteger[producListSize];
            for(int j=0;j<producListSize;j++){
                products[j]=in.nextBigInteger();
            }



            int k =1;
            //decryption
            BigInteger pr;
            while(products[0].equals(products[k])){
                k++;
            }
            int ik=k;

            pr=products[k-1].gcd(products[k]);
            primeList.add(products[k-1].divide(pr));
            primeList.add(pr);
            primeSet.add(products[k-1].divide(pr));
            primeSet.add(pr);



            for(;k<producListSize;k++){
                pr=products[k].divide(pr);
                primeList.add(pr);
                primeSet.add(pr);
            }
            //init prime list
            ArrayList<BigInteger> initPrimeList = new ArrayList<>();
            ik-=2;
            BigInteger temp=primeList.get(0);
            for(;ik>=0;ik--){
                initPrimeList.add(products[ik].divide(temp));
                temp=products[ik].divide(temp);
            }


            //decryption

            //decrypt from numbers
            HashMap<BigInteger,Character> map = new HashMap<>();
            int v=0;
            for(BigInteger p:primeSet){
                map.put(p,(char)(65+v));
                v++;
            }
            StringBuilder ans = new StringBuilder();
            for(int p=initPrimeList.size()-1;p>=0;p--){
                ans.append(map.get(initPrimeList.get(p)));
            }
            for(BigInteger p:primeList){
                ans.append(map.get(p));
            }

            System.out.println("Case #"+(i+1)+": "+ans);
        }
    }

    private static void createTest(String s){
        int[] primes = new int[]{3769,3833,3877,4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147};
//26
        HashMap<Character,Integer> map = new HashMap<>();
        int k=0;
        for(int i:primes){
            map.put((char)(65+k),i);
            k++;
        }
        System.out.println(5148 +" "+ (s.length()-1));

        for(int i=0;i<s.length()-1;i++){
            int a =map.get(s.charAt(i))*map.get(s.charAt(i+1));
            System.out.print(a+" ");
        }
        System.out.println();

    }

}
