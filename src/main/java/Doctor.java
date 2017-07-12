import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Doctor {
  private String name;
  private String specialty;
  private int id;

  public Doctor(String name){
    this.name = name;
    this.specialty = "undefined";
  }

  public String getName(){
    return name;
  }

  public String getSpecialty(){
    return specialty;
  }

  public int getId() {
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO doctors(name, specialty) VALUES (:name, :specialty)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("name", this.name)
        .addParameter("specialty", this.specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Doctor> all(){
    String sql = "SELECT id, name FROM  doctors";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }


  public boolean doctorsEqual(Object otherDoctor) {
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getName().equals(newDoctor.getName()) &&
            //  this.getSpecialty().equals(newDoctor.getSpecialty()) &&
             this.getId() == newDoctor.getId();
    }
  }

  public static Doctor find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM doctors where id=:id";
      Doctor doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
      return doctor;
    }
  }



}
