import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_returnsAllInstancesOfPatient_true() {
    Patient firstPatient = new Patient("j", "sick", 1);
    firstPatient.save();
    Patient secondPatient = new Patient("2", "sick", 1);
    secondPatient.save();
    assertEquals(true, Patient.all().get(0).patientsEqual(firstPatient));
    assertEquals(true, Patient.all().get(1).patientsEqual(secondPatient));
  }

  @Test
  public void find_returnsPatientWithSameId_secondPatient() {
    Patient firstPatient = new Patient("j", "sick", 1);
    firstPatient.save();
    Patient secondPatient = new Patient("2", "sick", 1);
    secondPatient.save();
    assertEquals(Patient.find(secondPatient.getId()), secondPatient);
  }
}
