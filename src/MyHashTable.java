public class MyHashTable {
 node<node<MyLinkedList<String>>> r[];
    int size;
    MyHashTable(int a)
    {
        r=new node[a];
        size=a;

    }
    private int getHashIndex(Object b)
    {
        return Math.abs(b.hashCode()%150);
    }
    public void add(String w)
    {   int key=getHashIndex(w);
        node<node<MyLinkedList<String>>> n;

        {
            node<node<MyLinkedList<String>>> n1 = new node();
            n1.obj = w;
            if(r[key]==null) {

            r[key] = n1;
                }
            else
        { n=r[key];
            while(n.next!=null)
            {
                n=n.next;
            }
            n.next=n1;


        }
           }

    }
    public int hashSize()
    {
        int y=0;
        for(int i=0;i<150;i++)
        {
           node<node<MyLinkedList<String>>> n=r[i];
            int c=0;
            while(n!=null)
            {
                c++;
                n=n.next;
            }
            y=y+c;
        }
        return y;
    }
    public node<node<MyLinkedList<String>>> hashNode(String w)
    {
        node<node<MyLinkedList<String>>> o=r[getHashIndex(w)];
         while(o!=null)
        {   if(o.obj.equals(w))
            {return o;}
            o=o.next;
        }
        return null;
    }
    public MyLinkedList<String> allNodes()
    {
        MyLinkedList<String> y=new MyLinkedList<>();
        for(int i=0;i<150;i++)
        {
            if(r[i]!=null)
            {
                node<node<MyLinkedList<String>>> o=r[i];
                while(o!=null)
                {
                    y.Insert(o.obj);
                    o=o.next;
                }
            }
        }
        return y;
    }

}
