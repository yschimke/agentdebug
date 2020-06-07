package ee.schimke;

import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Agent {
  public static void agentmain(String agentArgs, Instrumentation inst) {
    try {
      //System.out.println("Loading replacement classes");

      String[] args = agentArgs.split(":");

      String jarFile = args[0];
      ZipFile zipFile = new ZipFile(jarFile);

      ClassDefinition[] replacements = new ClassDefinition[args.length - 1];

      ClassLoader classLoader = ClassLoader.getSystemClassLoader();

      for (int i = 1; i < args.length; i++) {
        String name = args[i];
        String zipName = name.replace('.', '/') + ".class";

        ZipEntry z = zipFile.getEntry(zipName);
        InputStream is = zipFile.getInputStream(z);
        byte[] bytes = is.readAllBytes();

        Class clazz = classLoader.loadClass(name);
        replacements[i - 1] = new ClassDefinition(clazz, bytes);
      }

      inst.redefineClasses(replacements);

      //System.out.println("Loaded " + clazz + " " + bytes.length);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
