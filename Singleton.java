class Singleton {

    private static Singleton single_instance = null;

    public Ucet ucet;

    private Singleton() {

    }

    public static Singleton getInstance() {

        if (single_instance == null) {
            single_instance = new Singleton();
        }
        return single_instance;
    }

    public void setUcet(Ucet ucet) {
        this.ucet = ucet;
    }

}