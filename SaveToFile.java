import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveToFile {

    static FileInputStream in;
    static FileOutputStream out;
    static byte[] bt = new byte[2000];
    //Для перевода сохраняемого сообщения на следующюю сроку
    final static byte[] line = {13,10};
    static SaveToFile sa;

    SaveToFile(){
        try {
            out = new FileOutputStream("test.txt", true);
        } catch (IOException exception) { System.out.println("Exeption in document");}
        finally {
            try{out.close();}catch(IOException e){};
        }
    }

    void save(String msg) throws IOException {
        bt = msg.getBytes();
        out.write(bt);
        out.write(line);
    }

    void extract() throws  IOException{
        in = new FileInputStream("test.txt");
        for(int i =0;i<420;i++){ // не успел нормально реализовать через ArrayList, поэтому замечание уже понял
            bt[i] = (byte) in.read();
        }
        String st = new String(bt,"UTF-8");
        String[] s = st.split("\n");
        for(int i = (s.length-100); i<s.length; i++) {
            System.out.println(s[i]);
        }
    }
}

