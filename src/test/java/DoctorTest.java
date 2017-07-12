import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void doctor_instantiatesCorrectly_true(){
    Doctor testDoctor = new Doctor("Dr.Test1");
    assertEquals(true, testDoctor instanceof Doctor);
  }

  @Test
  public void getName_doctorInstantiates_withName_DrTest1(){
    Doctor testDoctor = new Doctor("Dr.Test1");
    assertEquals("Dr.Test1", testDoctor.getName());
  }

  @Test
  public void getId_doctorSavesWithId_0(){
    Doctor testDoctor = new Doctor("Dr.Test1");
    testDoctor.save();
    assertTrue(0 < testDoctor.getId());

  }

  @Test
  public void all_returnsAllInstancesOfDoctor_true(){
    Doctor testDoctor1 = new Doctor("Dr.Test1");
    testDoctor1.save();
    Doctor testDoctor2 = new Doctor("Dr.Test2");
    testDoctor2.save();
    assertEquals(true, Doctor.all().get(0).doctorsEqual(testDoctor1));
    assertEquals(true, Doctor.all().get(1).doctorsEqual(testDoctor2));
  }

  @Test
  public void find_returnsDoctorWithSameId_secondDoctor() {
    Doctor firstDoctor = new Doctor("Dr.Test1");
    firstDoctor.save();
    Doctor secondDoctor = new Doctor("Dr.Test2");
    secondDoctor.save();
    System.out.println("second doctor id: " + secondDoctor.getId());
    assertEquals(Doctor.find(secondDoctor.getId()), secondDoctor);
  }
}
