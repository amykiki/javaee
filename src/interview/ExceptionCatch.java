package interview;

class Annoyance extends Exception{}
class Sneeze extends Annoyance {}
public class ExceptionCatch {
    public static void testException() throws Exception{
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Cought Annoyance Exception");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Cought Snneze Exception");
            throw s;
//            return;
        } finally {
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) throws Exception{
        try {
            testException();
        } catch (Annoyance annoyance) {
            System.out.println("Catch Annoyance in Outter!");
        }


    }
}
