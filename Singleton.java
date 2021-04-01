class Singleton
{
    // static variable single_instance of type Singleton
    private static Singleton single_instance=null;

    // variable of type String
    public Ucet ucet;

    // private constructor restricted to this class itself
    private Singleton()
    {

    }

    // static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new Singleton();
        }
        return single_instance;
    }

    public void setUcet(Ucet ucet) {
        this.ucet = ucet;
    }


}