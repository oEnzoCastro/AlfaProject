public class Aula {
    private int numAula;
    private String id_Aula;
    private String moduloAula;
    private boolean checkAula;
    
    @Override
    public String toString() {
        return "Aula [numAula=" + numAula + ", id_Aula=" + id_Aula + ", moduloAula=" + moduloAula + ", checkAula="
                + checkAula + "]";
    }
    public Aula(int numAula, String id_Aula, String moduloAula, boolean checkAula) {
        this.numAula = numAula;
        this.id_Aula = id_Aula;
        this.moduloAula = moduloAula;
        this.checkAula = checkAula;
    }
    public int getNumAula() {
        return numAula;
    }
    public void setNumAula(int numAula) {
        this.numAula = numAula;
    }
    public String getId_Aula() {
        return id_Aula;
    }
    public void setId_Aula(String id_Aula) {
        this.id_Aula = id_Aula;
    }
    public String getModuloAula() {
        return moduloAula;
    }
    public void setModuloAula(String moduloAula) {
        this.moduloAula = moduloAula;
    }
    public boolean isCheckAula() {
        return checkAula;
    }
    public void setCheckAula(boolean checkAula) {
        this.checkAula = checkAula;
    }
}
