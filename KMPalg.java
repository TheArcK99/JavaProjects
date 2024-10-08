import java.util.*;
import java.util.Arrays;

public class KMPalg {
    public static void main(String args[]) {
    		Scanner e = new Scanner(System.in);
    		String txt = e.nextLine();
        	String pat = "bill";
        	new KMP_String_Matching().KMPSearch(pat, txt);
    	}
}

class KMP_String_Matching {
    void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
        int[] indexes= new int[0];

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while ((N - i) >= (M - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                                + "at index " + (i - j));
                indexes = add(indexes, (i-j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        rePrint(indexes, txt, pat);
    }

    public static int[] add(int[] a, int c){
	int[] new2 = new int[a.length+1];
	for(int i = 0; i < a.length; i++){
		new2[i] = a[i];
	}
	new2[a.length] = c;
	return new2;
	}

	public static void rePrint(int[] a, String t, String p){
		int h = 0;
		for(int i = 0; i < a. length; i++){
			System.out.print(t.substring(h,a[i]));
			for(int g = 0; g < p.length(); g++){
			System.out.print("0");
			}
			h = a[i]+p.length();
		}
		System.out.print(t.substring(h, t.length()));
		System.out.println();
	}

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}



