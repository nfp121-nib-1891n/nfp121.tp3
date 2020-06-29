package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile4 implements PileI, Cloneable {
    /** la liste des Maillons/Elements */
    private Maillon stk;
    /** la capacité de la pile */
    private int capacite;
    /** le nombre */
    private int nombre;

    /**
     * Classe interne "statique" contenant chaque élément de la chaine c'est une
     * proposition, vous pouvez l'ignorer !
     */
    private static class Maillon implements Cloneable {
        private Object element;
        private Maillon suivant;

        public Maillon(Object element, Maillon suivant) {
            this.element = element;
            this.suivant = suivant;
        }

        public Maillon suivant() {
            return this.suivant;
        }

        public Object element() {
            return this.element;
        }

        public Object clone() throws CloneNotSupportedException {
            Maillon m = (Maillon) super.clone();
            m.element = element;
            return m;
        }
    }

    /**
     * Création d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit être > 0
     */
    public Pile4(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.stk = null;
        this.capacite = taille;
    }

    public Pile4() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
        throw new PilePleineException();
        Maillon m = new Maillon(o,stk); 
        stk = m;    
        nombre++;
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        Object o = stk.element();	
        stk = stk.suivant;	
        nombre--;	
        return o;
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
         return stk.element();
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return stk==null;
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        return capacite == nombre;
    }

    /**
     * Retourne une représentation en String d'une pile, contenant la
     * représentation en String de chaque élément.
     * 
     * @return une représentation en String d'une pile
     */
    public String toString() {

        String s = "[";
        Maillon newM = stk;	
        while (newM !=null) {	
            s+=newM.element();	
            newM = newM.suivant();	
            if (newM != null)	
                s+=", ";	
        }	
        return s + "]";
    }

    public boolean equals(Object o) {
        if(o==null)return false;	
        PileI p = (PileI)o;	
        if(p instanceof Pile4){	
            if(p.taille()==this.taille() && p.capacite() == this.capacite() && p.toString().equals(this.toString()))	
            return true;	
        }	
        return false;
    }

    public int capacite() {
        return this.capacite;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return nombre;
    }
}