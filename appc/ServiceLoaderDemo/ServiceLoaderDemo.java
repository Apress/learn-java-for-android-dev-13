import ca.tutortutor.codec.CodecManager;
import ca.tutortutor.codec.Decoder;
import ca.tutortutor.codec.Encoder;

public class ServiceLoaderDemo
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.out.println("usage  : java ServiceLoaderDemo codecname");
         System.out.println("example: java ServiceLoaderDemo ALAC");
         return;
      }
      Encoder enc = CodecManager.getEncoder(args[0]);
      if (enc != null)
         System.out.println("Encoder name: " + enc.getName());
      Decoder dec = CodecManager.getDecoder(args[0]);
      if (dec != null)
         System.out.println("Decoder name: " + dec.getName());
   }
}