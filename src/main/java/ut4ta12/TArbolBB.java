package ut4ta12;



public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB() {
        raiz = null;
    }

    public TElementoAB getRaiz() {
        return this.raiz;
    }

    public void setRaiz(TElementoAB elementoAB) {
        this.raiz = elementoAB;
    }

    /**
     * @param unElemento
     * @return
     */
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }

    public String preOrden() {
        if (this.raiz == null) {
            return "";
        }
        return this.raiz.preOrden();
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    @Override
    public String inOrden() {
        if (esVacio()) {
            return "";
        } else {
            return raiz.inOrden();
        }
    }

    public String postOrden() {
        // TODO Auto-generated method stub
        if (raiz == null) {
            return "";
        }
        return raiz.postOrden();
    }

    /**
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    /**
     * @return True si habían elementos en el árbol, false en caso contrario
     */
    public boolean vaciar() {
        if (!esVacio()) {
            raiz = null;
            return true;
        }
        return false;
    }

    @Override
    public Lista<T> inorden() {
        Lista<T> listaInorden = null;
        if (!esVacio()) {
            listaInorden = new Lista<>();
            raiz.inOrden(listaInorden);
        }
        return listaInorden;
    }

    @Override
    public int obtenerAltura() {
        if (this.raiz != null) {
            return raiz.obtenerAltura();
        }
        return -1;
    }

    @Override
    public int obtenerTamanio() {
        if (this.raiz != null) {
            return raiz.obtenerTamanio();
        }
        return 0;
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (raiz != null) {
            return raiz.obtenerNivel(unaEtiqueta);
        }
        return -1;
    }

    @Override
    public int obtenerCantidadHojas() {
        if (raiz != null) {
            return raiz.obtenerCantidadHojas();
        }
        return -1;
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        } else {
            System.out.println("arbol vacio");
        }
    }

    public boolean estaBalanceado() {
        if (raiz != null) {
            return raiz.estaBalanceado();
        }
        return true;
    }

    public Comparable obtenerInmediataAnterior(Comparable etiqueta) {
        if (raiz == null) {
            return null;
        }

        Lista claves = new Lista<>();
        raiz.inOrden(claves);

        Nodo actual = claves.getPrimero();

        if (actual.getEtiqueta().compareTo(etiqueta) >= 0) {
            return null;
        }

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getEtiqueta().compareTo(etiqueta) >= 0) {
                return actual.getEtiqueta();
            }
            actual = actual.getSiguiente();
        }

        return actual.getEtiqueta();
    }

    public int sumaDeClaves(int nivel) {
        if (nivel < 0) {
            throw new IllegalArgumentException();
        } else if (this.obtenerAltura() < nivel) {
            throw new IndexOutOfBoundsException();
        }

        if (raiz != null) {
            return raiz.sumaDeClaves(nivel, 0);
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean esIdentico(TArbolBB otroArbol) {
        if (this.raiz != null && otroArbol.raiz != null) {
            return this.raiz.esIdentico(otroArbol.raiz);
        }
        return false;
    }

}