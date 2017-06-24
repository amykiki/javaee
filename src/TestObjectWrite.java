import java.io.*;

public class TestObjectWrite {
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\Java\\ebook\\test.dat")));
            Person p1 = new Person(1, "amy");
            Person p2 = new Person(2, "kevin");
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.close();
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("D:\\Java\\ebook\\test.dat")));
            Person oP1 = (Person) ois.readObject();
            System.out.println(oP1.toString());
            oP1 = (Person) ois.readObject();
            System.out.println(oP1.toString());
            oP1 = (Person) ois.readObject();
            System.out.println(oP1.toString());
            oP1 = (Person) ois.readObject();
            System.out.println(oP1.toString());
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\Java\\ebook\\test.dat", true)));
            Person p3 = new Person(3, "jay");
            Person p4 = new Person(4, "bruce");
            oos.writeObject(p3);
            oos.writeObject(p4);
//            oos.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
