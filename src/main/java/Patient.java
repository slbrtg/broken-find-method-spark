import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Patient {
  private String name;
  private String illness;
  private int id;
  private int doctorId;

  public Patient(String name, String illness, int doctorId){
    this.name = name;
    this.illness = illness;
    this.doctorId = doctorId;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public String getIllness(){
    return illness;
  }

  public int getPatientId(){
    return doctorId;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO patients(name, illness, doctorid) VALUES (:name, :illness, :doctorid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("illness", illness)
        .addParameter("doctorid", doctorId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Patient> all(){
    String sql = "SELECT id, name, illness, doctorid FROM patients";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public boolean patientsEqual(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
             this.getId() == newPatient.getId() &&
             this.getPatientId() == newPatient.getPatientId();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }

}
