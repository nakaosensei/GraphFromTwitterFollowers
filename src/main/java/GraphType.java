public enum GraphType {
    ListaAdjacencia(0),MatrizIncidencia(1),MatrizAdjacencia(2);

    int tipo;

    private GraphType(int tipo){
        this.tipo=tipo;
    }
}