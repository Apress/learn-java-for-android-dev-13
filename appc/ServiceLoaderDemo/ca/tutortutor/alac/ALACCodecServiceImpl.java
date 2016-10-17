package ca.tutortutor.alac;

import ca.tutortutor.codec.CodecService;
import ca.tutortutor.codec.Decoder;
import ca.tutortutor.codec.Encoder;

public class ALACCodecServiceImpl implements CodecService
{
   @Override
   public Encoder getEncoder(String name)
   {
      if (name.equalsIgnoreCase("ALAC"))
         return new ALACEncoder();
      return null;
   }

   @Override
   public Decoder getDecoder(String name)
   {
      if (name.equalsIgnoreCase("ALAC"))
         return new ALACDecoder();
      return null;
   }
}

class ALACEncoder implements Encoder
{
   @Override
   public String getName()
   {
      return "ALAC encoder";
   }
}

class ALACDecoder implements Decoder
{
   @Override
   public String getName()
   {
      return "ALAC decoder";
   }
}