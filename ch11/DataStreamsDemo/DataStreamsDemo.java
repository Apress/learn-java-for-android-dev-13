import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamsDemo
{
   final static String FILENAME = "values.dat";

   public static void main(String[] args)
   {
      DataOutputStream dos = null;
      DataInputStream dis = null;
      try
      {
         FileOutputStream fos = new FileOutputStream(FILENAME);
         dos = new DataOutputStream(fos);
         dos.writeInt(1995);
         dos.writeUTF("Saving this String in modified UTF-8 format!");
         dos.writeFloat(1.0F);
         dos.close(); // Close underlying file output stream.
         // The following null assignment prevents another close attempt on
         // dos (which is now closed) should IOException be thrown from
         // subsequent method calls.
         dos = null;
         FileInputStream fis = new FileInputStream(FILENAME);
         dis = new DataInputStream(fis);
         System.out.println(dis.readInt());
         System.out.println(dis.readUTF());
         System.out.println(dis.readFloat());
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: " + ioe.getMessage());
      }
      finally
      {
         if (dos != null)
            try
            {
               dos.close();
            }
            catch (IOException ioe2) // Cannot redeclare local variable ioe.
            {
               assert false; // shouldn't happen in this context
            }
         if (dis != null)
            try
            {
               dis.close();
            }
            catch (IOException ioe2) // Cannot redeclare local variable ioe.
            {
               assert false; // shouldn't happen in this context
            }
      }
   }
}