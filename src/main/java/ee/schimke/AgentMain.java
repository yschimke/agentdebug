package ee.schimke;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;

public class AgentMain {
  public static void main(String[] args)
      throws IOException, AttachNotSupportedException, AgentLoadException,
      AgentInitializationException {
    if (args.length == 0) {
      for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
        System.out.format("%8s\t%s\n", vmd.id(), vmd.displayName());
      }
    } else {
      VirtualMachine vm = VirtualMachine.attach(args[0]);

      String jar = AgentMain.class.getProtectionDomain().getCodeSource().getLocation().getFile();

      vm.loadAgent(jar, jar + ":" + EventListener.class.getName());
      vm.detach();
    }
  }
}
