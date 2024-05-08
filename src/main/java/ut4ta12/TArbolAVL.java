package ut4ta12;

public class TArbolAVL<T> extends TArbolBB<T> {

    @Override
    public TElementoAVL<T> getRaiz() {
        return (TElementoAVL) super.getRaiz();
    }

    public void insertarAVL(TElementoAVL elementoAVL) {
        if (getRaiz() != null) {
            setRaiz(getRaiz().insertarAVL(elementoAVL));
        } else {
            setRaiz(elementoAVL);
        }
    }

    @Override
    public boolean insertar(TElementoAB elementoAB) {
        throw new UnsupportedOperationException("Método no disponible para Arbol AVL");
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (getRaiz() != null) {
            setRaiz(getRaiz().eliminar(unaEtiqueta));
        } else {
            System.out.println("arbol vacio");
        }
    }

    

}