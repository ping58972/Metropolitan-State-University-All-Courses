package interview;

class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
        // this.next = null;
    }
}
// class LinkedList{
// public LinkedListy(Node node) {
//
// }
// }


/**
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        Node node = mindleNode(n1);
        System.out.println(node.data);
        // while (node != null) {
        // System.out.println(node.data);
        // node = node.next;
        // }

        // System.out.println("Hello word");

    }

    public static Node mindleNode(Node n) {
        Node ans = null;
        while (n.next != null) {
            ans = n;
            if (n.next.next == null) {
                break;
            }
            n = n.next.next;
        }

        return ans;
    }

    public String testString() {
        return "test";
    }

}
