import java.io.File;

public class PartitionSpace
{
   public static void main(String[] args)
   {
      File[] roots = File.listRoots();
      for (File root: roots)
      {
         System.out.printf("Partition: %s%n", root);
         System.out.printf("Free space on this partition = %d%n",
                            root.getFreeSpace());
         System.out.printf("Usable space on this partition = %d%n",
                            root.getUsableSpace());
         System.out.printf("Total space on this partition = %d%n",
                            root.getTotalSpace());
         System.out.println("***");
      }
   }
}