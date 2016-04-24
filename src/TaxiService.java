import java.util.Arrays;

class taxi{
	int t1;
	int t2;
	String name;
	int aval;
	MyLinkedList<String> curr=new MyLinkedList<>();
}


public class TaxiService{
  graph g;

	MyLinkedList<taxi> taxi;
	public TaxiService() {
		 g=new graph();
		taxi=new MyLinkedList<>();
	}

	private node<taxi> ReturnTaxi(String s)
	{
		node<taxi> a=taxi.f;
		while(a!=null)
		{
			if(a.data.name.equals(s)) {
				return a;
			}
			a=a.next;
		}
		return null;
	}
	private String[] sorted()
	{
		String[] t=new String [g.vertexCount()];
		node<String > n=g.allVertex().f;
		int i=0;
		while(n!=null)
		{ t[i]=n.data;
			n=n.next;i++;

		}
		Arrays.sort(t);
		return t;

	}
	private void fillTime(int t)
	{
		node<taxi> w=taxi.f;
		String sort[]=sorted();
		int v=g.vertexCount();
		while(w!=null) {	if(w.data.aval==0) {
				String as = "";
				int j =0;
				if (w.data.curr.f.next == null) {
					as = w.data.curr.f.data;

				} else
				{
					as=w.data.curr.rr().data;
					j=w.data.curr.rr().weight;
				}


				int i;
				for (i = 0; i < v; i++) {
					if (sort[i].equals(as)) {
							break;
					}
				}

				while(j<=t) {
					dijkr s = g.dijk(sort[i % v], sort[(i + 1) % v]);
					int x = s.total;
					node<String> z=s.list.f;
					System.out.println("At time " + j + ", Taxi" + w.data.name + " chose a new destination vertex " + sort[(i + 1) % v]+".");
					while (z.next != null) {
						j = j + g.edgeWeight(z.data, z.next.data);
						z = z.next;
						w.data.curr.ininser(z.data);
							w.data.curr.rr().weight = j;
						}
					i++;
				}
				w.data.t1 = w.data.t2;
				w.data.t2 = w.data.curr.rr().weight;
			}
			else{
				if(w.data.t2<t)
				{node<String> d = w.data.curr.f;
					while (d.next.next != null) {
						d = d.next;
					}
					int we=d.weight;
					int bd=d.next.weight;
					int ed=w.data.t2-we;
					String as=" ";
					if(ed<((bd-we)/2))
					{
					 as=d.data;
						w.data.t2=w.data.t2+ed;
					}
					else{
					as=d.next.data;
						w.data.t2=w.data.t2+bd-we-ed;
				}
					int i;
					for (i = 0; i < v; i++) {
						if (sort[i].equals(as)) {
							break;
						}
					}
					int j = w.data.t2;
					while(j<=t) {
						dijkr s = g.dijk(sort[i % v], sort[(i + 1) % v]);
						int x = s.total;
						node<String> z=s.list.f;
						System.out.println("At time " + j + ", Taxi" + w.data.name + " chose a new destination vertex " + sort[(i + 1) % v] + ".");
						while (z.next != null) {

							j = j + g.edgeWeight(z.data, z.next.data);
							z = z.next;
							w.data.curr.ininser(z.data);
							w.data.curr.rr().weight = j;

						}


						i++;
					}
					w.data.t1 = w.data.t2;
					w.data.t2 = w.data.curr.rr().weight;
				}

			}
			multidel(w.data.curr);
			w=w.next;
		}

	}
	private dijkr bestpath(String a,String b,int t1,String c,String d,int t2)
	{
		dijkr ad=g.dijk(a,d);
		dijkr ac=g.dijk(a, c);
		dijkr bd=g.dijk(b, d);
		dijkr bc=g.dijk(b,c);
		Integer[] yu=new Integer[4];
		int asd=ad.total=yu[0] =t1+ad.total+g.edgeWeight(c,d)-t2;
		int asc=ac.total=yu[1] =t1+ac.total+t2;
		int bsd=bd.total=yu[2]=g.edgeWeight(a,b)-t1+bd.total+g.edgeWeight(c,d)-t2;
		int bsc=bc.total=yu[3]=g.edgeWeight(a,b)-t1+t2+bc.total;
		Arrays.sort(yu);

		if(yu[0]==asd)
		{ node<String> q;
			q=ad.list.f;
				q.weight=t1;
				node<String>  p=q;
				q=q.next;
				while(q!=null)
				{
					q.weight=g.edgeWeight(p.data,q.data)+p.weight;
					p=q;
					q=q.next;
               }
			return ad;
		}
		if(yu[0]==asc)
		{
			node<String> q=new node();

				q=ac.list.f;
				q.weight=t1;
				node<String>  p=q;
				q=q.next;
				while(q!=null)
				{
					q.weight=g.edgeWeight(p.data,q.data)+p.weight;
					p=q;
					q=q.next;
				}
			return ac;
		}
		if(yu[0]==bsd)
		{ node<String> q = new node();
			q = bd.list.f;
			q.weight = g.edgeWeight(a, b) - t1;
			node<String> p = q;
			q = q.next;
			while (q != null) {
				q.weight = g.edgeWeight(p.data, q.data)+p.weight;
				p = q;
				q = q.next;

		}
			return bd;
		}
			node<String> q = new node();
				q = bc.list.f;
				q.weight = g.edgeWeight(a, b) - t1;
				node<String> p = q;
				q = q.next;
				while (q != null) {
					q.weight = g.edgeWeight(p.data, q.data)+p.weight;
					p = q;
					q = q.next;
				}

			return bc;
	}
	private void addTaxi(String s)
	{ taxi d=new taxi();

		d.name=s;
		taxi.Insert(d);
	}

	private void multidel(MyLinkedList<String> a)
	{
		node<String> h=a.f;
		while(h.next!=null)
		{
			if(h.data.equals(h.next.data)&&h.weight==h.next.weight)
			{
				h.next=h.next.next;
			}

			else
			{
				h=h.next;
			}
		}
	}


	public void performAction(String actionMessage) {

		String[] s=actionMessage.split(" ");
  try{
		switch(s[0]) {
			case "edge":
				System.out.println("action to be performed: " + actionMessage);
				g.addEdge(s[1], s[2], Integer.valueOf(s[3]));
				break;
			case "taxi":
				System.out.println("action to be performed: " + actionMessage);
				if (g.containsVertex(s[2]) && ReturnTaxi(s[1]) == null) {
					addTaxi(s[1]);
					ReturnTaxi(s[1]).weight = 0;
					ReturnTaxi(s[1]).data.t1=0;
					ReturnTaxi(s[1]).data.t2=0;
				   ReturnTaxi(s[1]).data.curr.Insert(s[2]);
					ReturnTaxi(s[1]).data.curr.rr().weight=0;
				}
				else {
					if (!g.containsVertex(s[2])) {
						throw new IllegalAccessError("There is no such position with name " + s[2]);
					}
					if (ReturnTaxi(s[1]) != null) {
						throw new IllegalAccessError("There is already taxi available with name " + s[1]);
					}
				}
				break;
			case "customer":
				String a1 = s[1];
				String b1 = s[2];
				int t1=Integer.valueOf(s[3]);
				String a2=s[4];
				String b2=s[5];
				int t2=Integer.valueOf(s[6]);
				int time=Integer.valueOf(s[7]);

				fillTime(time);
				System.out.println("action to be performed: " + actionMessage);
			if(g.containsVertex(s[1])&&g.containsVertex(s[2])&&g.containsVertex(s[4])&&g.containsVertex(s[5])&&taxi.size()!=0) {
					node<taxi> h = taxi.f;
					System.out.println("Available taxis:");
					MyLinkedList<String> arr=new MyLinkedList<>();
					int r=0;
					int y=0;
					while (h != null) {
						dijkr x=null;

						if(h.data.aval==0)
						{
							node<String> a=h.data.curr.f;
							node<String> op=new node();
							node<String> c;
							while(a!=null)
							{ if(a.weight>time)
								{
									break;
								}
								op=a;
								a=a.next;
							}
							c=a;
							int v=op.weight;
							x=bestpath(op.data,c.data,time-v,a1,b1,t1);
							r=0;
						}
						else
						{
							node<String> op=new node();
							node<String> c=h.data.curr.f;
							while(c.next!=null)
							{ op=c;
								c=c.next;

							}
							 x=bestpath(op.data,c.data,h.data.t2-op.weight,a1,b1,t1);
							 r=h.data.t2-time;
							}
						MyLinkedList<String> d = x.list;
						y = x.total;
						if(d!=null) {
							node<String> b = d.f;
							System.out.print("Path of taxi" + h.data.name + ":");
							while (b.next != null) {
								System.out.print(b.data + ", ");
								b = b.next;
							}
                       		System.out.println(b.data + ". time taken is " + (y+r) + "A units");
						}

						else{
							System.out.println("Path of taxi" + h.data.name + ": current location is source.time taken is 0A units ");
						}
						arr.Insert(h.data.name);
						arr.ReturnNode(h.data.name).weight=y+r;

						h=h.next;
					}
				System.out.print("** Chose taxi" + min(arr).data + " to service the customer request ***\n" +
						"Path of customer:");
				dijkr z = bestpath(a1, b1, t1, a2, b2, t2);
				int yy = z.total;
				MyLinkedList<String> m = z.list;

				node<String> b = m.f;
				while (b.next != null) {
					System.out.print(b.data + ", ");
					b = b.next;
				}
				System.out.println(b.data + ". time taken is " + yy + "A units");
					node<taxi> t=ReturnTaxi(min(arr).data);
					t.data.t1=time+r;
					node<String> zs = t.data.curr.f;
					node<String> qw = zs;
					if(t.data.aval==0) {

						while (zs.next != null) {
							if (zs.weight > time) {
								break;
							}
							qw = zs;
							zs = zs.next;
						}
					}
					else{
						while(zs.next!=null)
						{
							qw=zs;
							zs=zs.next;
						}


					}	dijkr e=bestpath(qw.data,zs.data,time-qw.weight,a1,b1,t1);
						node<String> d=e.list.f;
					int u=0;
					if(t.data.aval==0)
					{
						 u=time;
											}
					else
					{   u=t.data.t2;

						}

					while(d!=null)
					{ d.weight=d.weight+u;
						qw.next=d;
						qw=qw.next;
						d=d.next;
					}	t.data.t2=t.data.t1+e.total;

						d=bestpath(a1, b1, t1, a2, b2, t2).list.f;

					 zs = t.data.curr.f;
			         qw = zs;
					while(zs!=null)
					{
						qw=zs;
						zs=zs.next;
					}
					u=t.data.t2;
					while(d!=null)
					{
						d.weight=d.weight+u;
						qw.next=d;
						qw=qw.next;
						d=d.next;
					}


					t.data.t2=t.data.t2+bestpath(a1, b1, t1, a2, b2, t2).total;
					t.data.aval=1;
					if(t.data.curr.rr().data.equals(a2))
					{	node<String> lm=new node();
						lm.data=b2;
						lm.weight=t.data.curr.rr().weight+g.edgeWeight(a2,b2);
						t.data.curr.rr().next=lm;
					}
					else if(t.data.curr.rr().data.equals(b2)) {
						node<String> lm = new node();
						lm.data = a2;
						lm.weight = t.data.curr.rr().weight + g.edgeWeight(a2, b2);
						t.data.curr.rr().next = lm;
					}

					multidel(t.data.curr);

				}
				else {
						if(taxi.size()==0)
					{
						throw new IllegalAccessError("No Taxi added yet");
					}

					else {
						throw new IllegalAccessError("There is some problem with location name or time in input");
					}
				}


				break;
			case "printTaxiPosition":
       fillTime(Integer.parseInt(s[1]));
				System.out.println("action to be performed: " + actionMessage);
				node<taxi> t=taxi.f;
				if(t==null)
				{
					throw new IllegalAccessError("No taxi available");
				}
				int ti=Integer.parseInt(s[1]);
				while(t!=null) {
					node<String> c=new node();
					node<String> op=c.next;
					int v=0;
					node<String> a=t.data.curr.f;
					node<String> h=a;
					while(a!=null)
					{
						if(a.weight>=ti)
						{
							break;
						}
						h=a;
						a=a.next;
					}
					op=h;
					c=a;
v=op.weight;
					System.out.println("taxi"+t.data.name+": ("+op.data+","+c.data+","+(ti-v)+")");
					t=t.next;
				}
				break;
			default:System.out.println("action to be performed: " + actionMessage);
				System.out.println("INVALID INPUT");
		}
  }
  catch(IllegalAccessError ex)
  {
	  System.out.println(ex.getMessage());
  }

	}
	public node<String> min(MyLinkedList<String> a)
	{
		node<String> k=a.f;
		node<String> f=a.f;
		while(f!=null)
		{
			if(f.weight<k.weight)
			{
				k=f;
			}
			f=f.next;
		}


		return k;
	}
}

