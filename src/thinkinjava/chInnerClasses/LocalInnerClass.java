package thinkinjava.chInnerClasses;

interface Counter {
    int next();
}
public class LocalInnerClass {
    private int count = 0;

    private void pass() {
        System.out.println("LocalInnerClass Pass!");
    }

    private static void passStatic() {
        System.out.println("LocalInnerClass Static Pass!");
    }

    Counter getCounter (final String name) {
        class LocalCounter implements Counter {
            public LocalCounter() {
                System.out.println("LocalCounter!");
            }
            @Override
            public int next() {
                System.out.println(name);
                pass();
                return count++;
            }
        }
        return new LocalCounter();
    }

   Counter getCounter2(final String name) {
        return new Counter() {
            {
                System.out.println("Counter2()");
            }
            @Override
            public int next() {
                System.out.println(name);
                pass();
                return count++;
            }
        };
   }

   private class InnerCounter implements Counter {
        private String name;
       public InnerCounter(String name) {
           name += "!!!";
           this.name = name;
           System.out.println("InnerClass Counter:" + name);
       }
       @Override
       public int next() {
           System.out.println(name);
           pass();
           return count++;
       }
   }

    Counter getCounter3(String name) {
        return new InnerCounter(name);
    }

    public static class NestCounter {
        public NestCounter() {
            System.out.println("NestCounter static class");
            passStatic();
        }
    }


    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local Inner ");
        Counter c2 = lic.getCounter2("Anonymous Inner ");
        Counter c3 = lic.getCounter3("Inner ");
        NestCounter nc = new NestCounter();

        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(c3.next());
        }
    }
}
