import java.io.File;

public class Permissions
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Permissions filespec");
         return;
      }
      File file = new File(args[0]);
      System.out.printf("Checking permissions for %s%n", args[0]);
      System.out.printf("  Execute = %b%n", file.canExecute());
      System.out.printf("  Read = %b%n", file.canRead());
      System.out.printf("  Write = %b%n", file.canWrite());
   }
}