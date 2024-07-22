import java.util.*;
import java.io.*;

public class KansupadaA20 {

    public static void main(String[] args) throws IOException {
        KansupadaTree tree = new KansupadaTree();
        Scanner input = new Scanner(new File("TWords.txt"));
        while (input.hasNextLine()) {
            String w = input.nextLine();
            tree.add(w);
        }

        Scanner input2 = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word: ");
            String w2 = input2.nextLine();
            if (w2.equals("exit")) {
                break;
            }
            if (tree.validWord(w2)) {
                System.out.println(w2 + " was found");
            } else {
                System.out.println(w2 + " was not found");
            }
        }
    }
}
    class KansupadaTree{
        private KansupadaTreeNode head;

        public KansupadaTree() {
            head = new KansupadaTreeNode();
        }

        public void add(String w) {
            KansupadaTreeNode c = head;
            w = w.toLowerCase();
            char[] e = w.toCharArray();
            for (int i = 0; i < e.length; i++) {
                if(Character.isLowerCase(e[i])) {
                    int pos = e[i] - 97;
                    if (c.child[pos] == null) {
                        //System.out.println(c.child[pos]);
                        c.child[pos] = new KansupadaTreeNode();
                    }
                    c = c.child[pos];
                }
            }
            c.done = true;
        }

        public boolean validWord(String w) {
            KansupadaTreeNode c = head;
            w = w.toLowerCase();
            char[] e = w.toCharArray();
            for (int i = 0; i < e.length; i++) {
                if(Character.isLowerCase(e[i])) {
                    int pos = e[i] - 97;
                    if (c.child[pos] == null) {
                        return false;
                    }
                    c = c.child[pos];
                }
            }
            if(c.done && c != null ){
                return true;
            }
            return false;
        }
    }


    class KansupadaTreeNode {
        KansupadaTreeNode[] child;
        boolean done;

        KansupadaTreeNode() {
            child = new KansupadaTreeNode[26];
            done = false;
        }
    }

