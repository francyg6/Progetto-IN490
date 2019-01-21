
import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federicogiulianelli
 */
//costruttore
public class Cfc {
    public LinkedList<Integer> cfc;
    public Grafo g;
    
    public Cfc(Grafo g){
        this.cfc=new LinkedList<Integer>();
        this.g=g;
    }
 
// Scorre il ramo da base a nodo e trasforma base in una componente connessa   
public void collapsePath(Nodo base,Nodo nodo) {
		Nodo collapse=null;
		Nodo bnodo=nodo;
			collapse=this.rootOf(base,bnodo);
			while (collapse!=null) {
				base.addTocomponent(collapse);
				base.removeNodo(collapse);
				g.getListaNodi().remove(collapse);
				collapse=this.rootOf(base, bnodo);
			}	
		System.out.println("trovata componente fortemente connessa" +base.getCfc());
		
		this.g.controllaPointers(base);
	} 

private Nodo rootOf(Nodo base,Nodo nodo) {
		Nodo y=null;
		Nodo n=null;
		Iterator<Nodo> x=base.getOutLink().descendingIterator();
		while(y==null && x.hasNext()) {
			n=x.next();
			if(threadPrecedes(n,nodo))
				y=n;				
		}
		return y;
	}
//dati i nodi v e w verifica se v>w (vero se v Ã© piÃ¹ giovane di w (v>=w)) 
	public boolean threadPrecedes(Nodo w,Nodo v) {
		int a=g.getListaNodi().indexOf(v);
		int b=g.getListaNodi().indexOf(w);
		if(a<0 || b<0) return false;
		else return a>=b;
	}
    
}
