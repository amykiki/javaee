package Test.IO;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by Amysue on 2016/1/12.
 */
public class FileTest {
    public static void main(String[] args) {
        File dir1 = new File("D:/Java/ebook/test/debug/next");
        File dir2 = new File("D:/Java/ebook/test");
        File f1 = new File("D:/Java/ebook/test/test1.txt");
        File f2 = new File("D:/Java/ebook/test/debug/next/test2.txt");
        File f3 = new File("D:/Java/ebook/test3.txt");
        File dir3 = new File("D:\\Java\\java专区");
        File dir4 = new File("D:\\Java");
        FileTest ft = new FileTest();

        try {
            dir1.mkdirs();
            f1.createNewFile();
            f2.createNewFile();
            dir1.delete();
            String[] names = dir3.list(new FileTest.FilterByName("GUI"));
            File[] files = dir4.listFiles(new FileTest.FilterByPath("java"));
            System.out.println("---------------------------");
            for (String n : names) {
                System.out.println(n);
            }
            System.out.println("---------------------------");
            for (File f : files) {
                System.out.println(f.getName());
            }
//            System.out.println(dir2.getName());
//            System.out.println(dir2.getPath());
//            System.out.println(dir2.getAbsolutePath());
//            System.out.println(dir2.getCanonicalPath());
//            ft.deleteDir(dir2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(f1.exists());
        System.out.println(f2.exists());
        System.out.println(dir2.exists());

    }

    public static class FilterByName implements FilenameFilter {
        private String ext;

        public FilterByName(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            if (name.contains(this.ext)) {
                return true;
            }
            return false;
        }
    }

    public static class FilterByPath implements FileFilter {
        private String ext;

        public FilterByPath(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File pathname) {
            System.out.println("*"+pathname.getAbsolutePath() + "*");
            if (pathname.getPath().contains(this.ext)) {
                return true;
            }
            return false;
        }

    }
    public boolean deleteDir(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    System.out.println(f.getName());
                    boolean deleted = deleteDir(f);
                    if (deleted == false) {
                        System.out.println(file.getAbsoluteFile() + " deletion is failed");
                        return false;
                    }
                }

            }
            file.delete();
            System.out.println(file.getAbsoluteFile() + " has been deleted");
            return true;

        } else {
            System.out.println(file.getAbsoluteFile() + " is not exists");
            return true;
        }
    }
}
