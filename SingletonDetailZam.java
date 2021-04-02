class SingletonDetailZam {

    private static SingletonDetailZam single_instance = null;

    public Ucet ucet;

    private SingletonDetailZam() {

    }

    public static SingletonDetailZam getInstance() {
        if (single_instance == null) {
            single_instance = new SingletonDetailZam();
        }
        return single_instance;
    }

    public void setUcet(Ucet ucet) {
        this.ucet = ucet;
    }

}