import org.junit.jupiter.api.Test;
import com.packet.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Gast9ra on 04.05.2017.
 */
public class test {
    @Test
    void flagTest(){
        String[] args=new String[8];
        args[0]="-i";
        args[1]="-u";
        args[2]="-c";
        args[3]="-s";
        args[4]="5";
        args[5]="-o";
        args[6]="C:\\tre";
        args[7]="output\\test.txt";
        Flag executor=new Flag(args);
        assertTrue(executor.isC());
        assertTrue(executor.isI());
        assertTrue(executor.isO());
        assertTrue(executor.isU());
        assertEquals(5,executor.getsCount());
        assertEquals("output\\test.txt",executor.getInput());
        assertEquals("C:\\tre",executor.getOutput());
    }

    @Test
    void mainTest(){

    }

}
