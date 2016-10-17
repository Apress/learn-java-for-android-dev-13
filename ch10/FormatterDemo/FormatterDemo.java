import java.util.Formatter;

public class FormatterDemo
{
   public static void main(String[] args)
   {
      Formatter formatter = new Formatter();
      formatter.format("%05d %x", 2380, 2830);
      System.out.println(formatter.toString()); // Output: 02380 b0e
   }
}