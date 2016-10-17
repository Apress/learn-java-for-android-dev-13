import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

import java.nio.channels.FileChannel;

public class ChannelDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.out.println("usage: java ChannelDemo newfilespec");
         return;
      }
      FileOutputStream fos = new FileOutputStream(args[0]);
      FileChannel fc = fos.getChannel();
      System.out.println("position: " + fc.position());
      System.out.println("size: " + fc.size());
      String msg = "This is a test message.";
      ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length() * 2);
      buffer.asCharBuffer().put(msg);
      fc.write(buffer);    
      System.out.println("position: " + fc.position());
      System.out.println("size: " + fc.size());
      fc.truncate(24L);
      fc.close();
      FileInputStream fis = new FileInputStream(args[0]);
      fc = fis.getChannel();
      System.out.println("size: " + fc.size());
      buffer.clear();
      fc.read(buffer);
      buffer.flip();
      while (buffer.hasRemaining())
         System.out.print(buffer.getChar());
      System.out.println();
      System.out.println(buffer.getChar(0));
      System.out.println(buffer.getChar(1));
      System.out.println(buffer.getChar(2));
      MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, 4);
      System.out.println(mbb.getChar(0));
      System.out.println(mbb.getChar(2));
      System.out.println(mbb.getChar(4));
      fc.close();
   }
}