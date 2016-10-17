import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;

import plugins.Disassembler;

public class FD // File Disassembler
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage  : java FD filespec");
         System.err.println("example: java FD FD.class");
         return;
      }
      if (args[0].equals("."))
         return;
      try
      {
         int index = args[0].indexOf('.');
         if (index == -1)
         {
            dumpInHexFormat(args[0]);
            return;
         }
         String ext = Character.toUpperCase(args[0].charAt(index + 1)) + 
                      args[0].substring(index + 2);
         Class<?> clazz = Class.forName("plugins" + "." + ext);
         Disassembler d = (Disassembler) clazz.newInstance();
         List<String> lines = d.disassemble(args[0]);
         for (String line: lines)
            System.out.println(line); 
      }
      catch (ClassNotFoundException cnfe)
      {
         dumpInHexFormat(args[0]);
      }
      catch (IllegalAccessException iae)
      {
         System.err.println("Illegal access: " + iae.getMessage());
      }
      catch (InstantiationException ie)
      {
         System.err.println("Cannot instantiate loaded class");
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: " + ioe.getMessage());
      }
   }

   static void dumpInHexFormat(String filename)
   {
      FileInputStream fis = null;
      try
      {
         fis = new FileInputStream(filename);
         StringBuilder sb = new StringBuilder();
         int offset = 0;
         int ch;
         while ((ch = fis.read()) != -1)
         {
            if ((offset % 16) == 0)
               System.out.printf("%08X ", offset);
            System.out.printf("%02X ", ch);
            if (ch < 32 || ch > 126)
               sb.append('.');
            else
               sb.append((char) ch);
            if ((++offset % 16) == 0)
            {
               System.out.println(sb);
               sb.setLength(0);
            }
         }
         if (sb.length() != 0)
         {
            for (int i = 0; i < 16 - sb.length(); i++)
               System.out.print("   ");
            System.out.println(sb);
         }
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: " + ioe.getMessage());
      }
      finally
      {
         if (fis != null)
            try
            {
               fis.close();
            }
            catch (IOException ioe)
            {
               assert false; // shouldn't happen in this context
            }
      }
   }
}