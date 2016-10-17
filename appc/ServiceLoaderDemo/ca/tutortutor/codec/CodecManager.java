package ca.tutortutor.codec;

import java.util.ServiceLoader;

import ca.tutortutor.codec.CodecService;
import ca.tutortutor.codec.Decoder;
import ca.tutortutor.codec.Encoder;

public class CodecManager
{
   private static ServiceLoader<CodecService> serviceLoader = 
      ServiceLoader.load(CodecService.class);

   public static Encoder getEncoder(String encodingName) 
   {
      for (CodecService cs: serviceLoader) 
      {
         Encoder enc = cs.getEncoder(encodingName);
         if (enc != null)
            return enc;
      }
      return null;
   }

   public static Decoder getDecoder(String encodingName) 
   {
      for (CodecService cs: serviceLoader) 
      {
         Decoder dec = cs.getDecoder(encodingName);
         if (dec != null)
            return dec;
      }
      return null;
   }
}