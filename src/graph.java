import java.util.Date;
import java.util.HashMap;

class dijkr{
    MyLinkedList<String> list=new MyLinkedList<>();
    int total;
    dijkr(MyLinkedList<String> t,int a )
    { list=t;
        total=a;
    }
}

public class graph {
    private MyHashTable n;
    long i= new Date().getTime();
    graph( )
    {
        n=new MyHashTable(150);
    }

    public void addVertex(String h)
    { try {
        if (n.hashNode(h) == null) {

            n.add(h);
            n.hashNode(h).data=new node();
            n.hashNode(h).data.data=new MyLinkedList<>();
          } else {
            throw new IllegalAccessError("Vertex with name " + h + " already present in graph");
        }
    }
    catch (IllegalAccessError ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public void addEdge(String a,String b,int w)
    {
        if(n.hashNode(a)==null)
        {  addVertex(a);

        }
        if(n.hashNode(b)==null)
        {
            addVertex(b);
              }
        node<MyLinkedList<String>> p=n.hashNode(a).data;
        p.data.Insert(b);
        p.data.ReturnNode(b).weight=w;
        node<MyLinkedList<String>> q=n.hashNode(b).data;
        q.data.Insert(a);
        q.data.ReturnNode(a).weight=w;


    }
    public int vertexCount() {
        {
            return n.hashSize();
        }
    }
    public MyLinkedList<String> allVertex()
    {
        return n.allNodes();
    }
    public  MyLinkedList<String> neighbour(String h)
    {  MyLinkedList<String> i=new MyLinkedList<>();
        node<String> ff=n.hashNode(h).data.data.f;
        while(ff!=null)
        {     i.Insert(ff.data);
                i.ReturnNode(ff.data).weight=ff.weight;

            ff=ff.next;
        }
        return i ;
    }
    public int edgeWeight(String a,String b)
    {//  System.out.println(a+" "+b);
        if(a.equals(b))
            return 0;
        else
       return n.hashNode(a).data.data.ReturnNode(b).weight;

    }

    public int edgeCount()
    {
        int u=0;
        for(int i=0;i<150;i++)
        {  if(n.r[i]!=null)
        { u=u+n.r[i].data.data.size();}
        }
        return u/2;
    }
    public boolean containsVertex(String v)
    {

        if(n.hashNode(v)!=null)
        {
            return true;
        }
        return false;
    }
    public void Print()
    { for(int i=0;i<150;i++)
    {
        if(n.r[i]!=null)
        {
            System.out.print(n.r[i].obj+" :-- ");
            n.r[i].data.data.print();
        }

    }
        System.out.println(" ");

    }
    public dijkr dijk(String a,String b)
    {   if(a.equals(b))
    {
        MyLinkedList<String> x=new MyLinkedList<>();
        x.Insert(a);
        dijkr n=new dijkr(x,0);
        return n;
    }
        String srt=b;
        String end=a;
        HashMap<String,Integer> dis=new HashMap<>();
        MyLinkedList<String> set=new MyLinkedList<>();
        MyLinkedList<String> unset=allVertex();
        set.Insert(srt);
        node<String> f=unset.f;
        while(f!=null)
        {
            if(!f.data.equals(srt))
            { dis.put(f.data,Integer.MAX_VALUE);
            f.weight=Integer.MAX_VALUE;}
            f=f.next;
        }
        dis.put(srt, 0);
        unset.Delete(srt);
        MyLinkedList<String> neg = neighbour(srt);
        neg.Delete(set);
        node<String> g = neg.f;
        while (g != null) {
            if (g.weight <dis.get(g.data))
            {
              dis.replace(g.data,dis.get(g.data),g.weight);
                unset.ReturnNode(g.data).obj=srt;
                unset.ReturnNode(g.data).weight=g.weight;
              }
                g = g.next;
        }

        while(unset.f!=null) {
             srt=findMin(unset).data;
            set.Insert(srt);
            set.ReturnNode(srt).obj=unset.ReturnNode(srt).obj;
            set.ReturnNode(srt).weight=unset.ReturnNode(srt).weight;
            if(srt.equals(end))
            {
                break;
            }
            unset.Delete(srt);
            neg = neighbour(srt);
            neg.Delete(set);
             g = neg.f;
            while (g != null) {
                if (g.weight+dis.get(srt) <dis.get(g.data))
                {
                    dis.replace(g.data,dis.get(g.data),g.weight+dis.get(srt));
                    unset.ReturnNode(g.data).obj=srt;
                    unset.ReturnNode(g.data).weight=g.weight+dis.get(srt);
                }
                    g = g.next;
            }
        }
        MyLinkedList<String> q=new MyLinkedList<>();
        node<String> d=set.ReturnNode(a);
        while(!d.data.equals(b))
        {
            q.Insert(d.data);
            d=set.ReturnNode(d.obj);
        }
        q.Insert(d.data);
        dijkr x=new dijkr(q,set.ReturnNode(a).weight);
        return  x;

    }


    private node<String> findMin(MyLinkedList<String> l)
    {
        int i=Integer.MAX_VALUE;
        node<String> e=new node();
        node<String> g=l.f;
        while(g!=null)
        {  if(g.weight<i)
            {
                i=g.weight;
                e=g;
            }
            g=g.next;
        }
        return e;
    }









}
