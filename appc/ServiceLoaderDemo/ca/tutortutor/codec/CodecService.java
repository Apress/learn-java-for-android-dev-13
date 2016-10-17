package ca.tutortutor.codec;

public interface CodecService 
{
   Encoder getEncoder(String charset);
   Decoder getDecoder(String charset);
}