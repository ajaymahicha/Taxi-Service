class node<e>{
    e data;
    String obj;
    node<e> next;
    int weight;


}
public class MyLinkedList<E> {
    node<E> f;
    node<E> r;
    public void Insert(E a){
        node<E> n=new node();
        n.data=a;
        if(f==null)
        {
            f=n;
            r=n;

        }
        else
        { r.next=n;
            r=n;

        }
    }
    public void print()
    {node<E> n=f;
        while(n!=null)
        {
            System.out.print(n.data+"("+n.weight+") ");
            n=n.next;
        }
    }
    public void ininser(E a)
    {
        node<E> s=f;
        while(s.next!=null)
        {
            s=s.next;
        }
        node<E> n=new node();
        n.data=a;
        s.next=n;
    }
    public node<E> rr()
    { node<E> s=f;
        while(s.next!=null)
        {
            s=s.next;
        }
        return s;
    }
    public void Delete(MyLinkedList<E> a)
    {
        node<E> t=a.f;
        while(t!=null)
        {       if(this.ReturnNode(t.data)!=null)
        { this.Delete(t.data);}
            t=t.next;
        }
    }
    public void Delete(E a)
    {
        node<E> n=f;
        int p=0;
        if(n.data.equals(a))
        { f=f.next;
            p=1;
        }
        while(n.next!=null && p==0)
        {   if(n.next.data.equals(a))
        {  p=1;
            n.next=n.next.next;
        }
        else
            { n=n.next;}
            }
        if(n.next==null)
        {
            r=n;
        }
        if(p==0)
        {
            throw new IllegalAccessError("can't Delete,LinkedList is Empty");
        }
    }
    public MyLinkedList<E> Union(MyLinkedList<E> a) {
        MyLinkedList<E> b = new MyLinkedList();
        node<E> n1 = a.f;
        node<E> n2 = f;
        while (n1 != null) {
            b.Insert(n1.data);
            n1 = n1.next;
        }
        while (n2 != null)
        {
            node<E> n=a.f;
            int  t=0;
            while(n!=null)
            {
                if(n.data.equals(n2.data))
                {t=1;}
                n=n.next;
            }
            if(t==0)
            {b.Insert(n2.data);
            }
            n2=n2.next;
        }
        return b;
    }
    public int size()
    {
        node<E> a=f;
        int e=0;
        while(a!=null)
        {
            a=a.next;
            e++;
        }
        return e;
    }

    public node<E> ReturnNode(E a)
    { node<E> p=f;
        while(p!=null)
        {
             if(p.data.equals(a))
            { return p;}

             p=p.next;
        }
        return null;

    }
    public boolean IsEmpty()
    {
        if(f==null)
            return true;
        else
            return false;
    }

}
