package modelo;

public class SingletonPerfis {
    private static SingletonPerfis singletonPerfis = null;
    private String[] perfis;
    
    private SingletonPerfis() {
    }

    public static SingletonPerfis getSingletonPerfis() {
        if (singletonPerfis == null) {
            singletonPerfis = new SingletonPerfis();
        }
        return singletonPerfis;
    }

    public String[] getPerfis() {
        return perfis;
    }

    public void setPerfis(String[] perfis) {
        this.perfis = perfis;
    }
    
}