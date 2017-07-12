import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource{

  @Override
  protected void before(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hospital_test", null, null);
  }

  @Override
  protected void after(){
    try(Connection con = DB.sql2o.open()) {
      String deletePatientsQuery = "DELETE FROM patients *;";
      String deleteDoctorsQuery = "DELETE FROM doctors *;";
      con.createQuery(deletePatientsQuery).executeUpdate();
      con.createQuery(deleteDoctorsQuery).executeUpdate();
    }
  }
}
