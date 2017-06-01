import org.junit.jupiter.api.Test;
import com.packet.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Gast9ra on 04.05.2017.
 */
public class test {
   private static String name="output\\fileForTest.txt";
   private static String output="output\\TestResult";



    @Test
    void flagTest() throws IOException {
        File read=new File("output\\test1.txt");
        read.createNewFile();
        String[] args=new String[8];
        args[0]="-i";
        args[1]="-u";
        args[2]="-c";
        args[3]="-s";
        args[4]="5";
        args[5]="-o";
        args[6]="C:\\tre";
        args[7]="output\\test1.txt";
        Flag executor=new Flag(args);
        assertTrue(executor.isC());
        assertTrue(executor.isI());
        assertTrue(executor.isO());
        assertTrue(executor.isU());
        assertEquals(5,executor.getsCount());
        assertEquals("output\\test1.txt",executor.getInput());
        assertEquals("C:\\tre",executor.getOutput());
        read.delete();
    }

    @Test
    void mainTest() throws FileNotFoundException {
        String text="q\nq\nQ\nq\n";
        String textRes="\"3\"q\n";
        File outWay=new File(name);
        FileWork get=new FileWork();
        PrintWriter testFile=new PrintWriter(outWay);
        testFile.write(text);
        testFile.close();
        String[] args=new String[7];
        args[0]="-i";
        args[1]="-c";
        args[2]="-s";
        args[3]="0";
        args[4]="-o";
        args[5]=output;
        args[6]=name;
        Main.command(args);
        assertEquals(textRes,get.read(args[5]));
        delete();
        text="wer\nwew\nwer\nred\nred\nchikens\nmir\nchikens\nchikens\nvnvn\nvhg\n";
        textRes="wer\nwew\nwer\nred\nchikens\nmir\nchikens\nvnvn\nvhg\n";
        testFile=new PrintWriter(outWay);
        testFile.write(text);
        testFile.close();
        args=new String[8];
        args[0]="-i";
        args[1]=" ";
        args[2]="";
        args[3]="-s";
        args[4]="1";
        args[5]="-o";
        args[6]=output;
        args[7]=name;
        Main.command(args);
        assertEquals(textRes,get.read(output));
        delete();
        String textResU="wer\nwew\nwer\nchikens\nmir\nvnvn\nvhg\n";
        testFile=new PrintWriter(outWay);
        testFile.write(text);
        testFile.close();
        args=new String[7];
        args[0]="-i";
        args[1]="-u";
        args[2]="-s";
        args[3]="0";
        args[4]="-o";
        args[5]=output;
        args[6]=name;
        Main.command(args);
        assertEquals(textResU,get.read(output));
        delete();
    }

    private static void delete(){
        new File(name).delete();
        new File(output).delete();
    }
}
