package backend_estudiante_curso.modelo;

public class matricula {

    private int estudiante_id;
    private int curso_idcurso;

    public matricula() {
    }

    public matricula(int estudiante_id, int curso_idcurso) {
        this.estudiante_id = estudiante_id;
        this.curso_idcurso = curso_idcurso;
    }

    @Override
    public String toString() {
        return "matricula{" + "estudiante_id=" + estudiante_id + ", curso_idcurso=" + curso_idcurso + '}';
    }

    public int getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public int getCurso_idcurso() {
        return curso_idcurso;
    }

    public void setCurso_idcurso(int curso_idcurso) {
        this.curso_idcurso = curso_idcurso;
    }

}
