package ut4ta12;

public class TElementoAVL<T> extends TElementoAB<T> {

    private int balance = 0;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAVL(Comparable unaEtiqueta, T unosDatos) {
        super(unaEtiqueta, unosDatos);
    }

    @Override
    public TElementoAVL<T> getHijoDer() {
        return (TElementoAVL<T>) super.getHijoDer();
    }

    @Override
    public TElementoAVL<T> getHijoIzq() {
        return (TElementoAVL<T>) super.getHijoIzq();
    }

    @Override
    public int obtenerBalance() {
        return this.balance = super.obtenerBalance();
    }

    public TElementoAB<T> insertarAVL(TElementoAB unElemento) {

        if (unElemento.getEtiqueta().compareTo(this.getEtiqueta()) < 0) {
            if (this.getHijoIzq() != null) {
                setHijoIzq(getHijoIzq().insertarAVL(unElemento));
            } else {
                this.setHijoIzq(unElemento);
            }

        } else if (unElemento.getEtiqueta().compareTo(this.getEtiqueta()) > 0) {

            if (this.getHijoDer() != null) {
                setHijoDer(getHijoDer().insertarAVL(unElemento));
            } else {
                this.setHijoDer(unElemento);
            }

        }

        return this.balancear();
    }

    @Override
    public TElementoAB<T> eliminar(Comparable etiqueta) {
        if (etiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (getHijoIzq() != null) {
                setHijoIzq(getHijoIzq().eliminar(etiqueta));
            }
            return this.balancear();
        } else if (etiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (getHijoDer() != null) {
                setHijoDer(getHijoDer().eliminar(etiqueta));
            }
            return this.balancear();
        }

        return quitaElNodo();
    }

    @Override
    protected TElementoAB<T> quitaElNodo() {
        if (this.getHijoIzq() == null) {
            return this.getHijoDer();
        }

        if (this.getHijoDer() == null) {
            return this.getHijoIzq();
        }

        TElementoAB<T> elHijo = this.getHijoIzq();
        TElementoAB<T> elPadre = this;

        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }

        TElementoAVL reemplazo = new TElementoAVL<>(elHijo.getEtiqueta(), elHijo.getDatos());
        this.setHijoIzq(getHijoIzq().eliminar(elHijo.getEtiqueta()));

        reemplazo.setHijoIzq(this.getHijoIzq());
        reemplazo.setHijoDer(this.getHijoDer());
        return reemplazo.balancear();
    }

    public TElementoAB<T> rotacionSimpleRR() {
        TElementoAB<T> hijoDer = getHijoDer();
        this.setHijoDer(hijoDer.getHijoIzq());
        hijoDer.setHijoIzq(this);
        return hijoDer;
    }

    public TElementoAB<T> rotacionSimpleLL() {
        TElementoAB<T> hijoIzq = getHijoIzq();
        this.setHijoIzq(hijoIzq.getHijoDer());
        hijoIzq.setHijoDer(this);
        return hijoIzq;
    }

    public TElementoAB<T> rotacionDobleRL() {
        this.setHijoDer(getHijoDer().rotacionSimpleLL());
        return this.rotacionSimpleRR();
    }

    public TElementoAB<T> rotacionDobleLR() {
        this.setHijoIzq(getHijoIzq().rotacionSimpleRR());
        return this.rotacionSimpleLL();
    }

    public TElementoAB<T> balancear() {

        this.obtenerBalance();

        if (this.balance == 2) {
            if (this.getHijoDer().obtenerBalance() == -1) {
                return rotacionDobleRL();
            }
            return rotacionSimpleRR();

        } else if (this.balance == -2) {
            if (this.getHijoIzq().obtenerBalance() == 1) {
                return rotacionDobleLR();
            }
            return rotacionSimpleLL();
        }

        return this;
    }

}
