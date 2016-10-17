package plugins;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Class extends Disassembler
{
   @Override
   public List<String> disassemble(String filename) throws IOException
   {
      DataInputStream dis = null;
      try
      {
         FileInputStream fis = new FileInputStream(filename);
         dis = new DataInputStream(fis);
         List<String> lines = new ArrayList<String>();
         StringBuilder sb = new StringBuilder();
         if (dis.read() != 0xca || dis.read() != 0xfe ||
             dis.read() != 0xba || dis.read() != 0xbe)
         {
            lines.add(filename + " is not a classfile");
            return lines;
         }
         lines.add("00000000  Signature            CAFEBABE");
         lines.add("00000004  Minor Version        " + 
                   (short) dis.readUnsignedShort());
         lines.add("00000006  Major Version        " + 
                   dis.readUnsignedShort());
         lines.add("00000008  Constant Pool Count  " + 
                   dis.readUnsignedShort());
         return lines;
      }
      finally
      {
         if (dis != null)
            try
            {
               dis.close();
            }
            catch (IOException ioe)
            {
               assert false; // shouldn't happen in this context
            }
      }
   }
}