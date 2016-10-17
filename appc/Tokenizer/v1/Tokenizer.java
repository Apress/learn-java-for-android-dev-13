import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Tokenizer
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Tokenizer filename");
         return;
      }

      FileReader fr = null;
      try
      {
         fr = new FileReader(args[0]);
         StreamTokenizer st = new StreamTokenizer(fr);
         while (st.nextToken() != StreamTokenizer.TT_EOF)
            switch (st.ttype)
            {
               case '"':
                    System.out.printf("String: %s%n", st.sval);
                    break;

               case StreamTokenizer.TT_EOL:
                    System.out.println("end of line");
                    break;

               case StreamTokenizer.TT_NUMBER:
                    System.out.printf("number: %f%n", st.nval);
                    break;

               case StreamTokenizer.TT_WORD:
                    System.out.printf("word: %s%n", st.sval);
                    break;

               default:
                    System.out.printf("other: %c%n", st.ttype);
            }
      }
      catch (FileNotFoundException fnfe)
      {
         System.err.println("cannot find file: "+args[0]);
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: "+ioe.getMessage());
      }
      finally
      {
         if (fr != null)
            try
            {
               fr.close();
            }
            catch (IOException ioe)
            {
               ioe.printStackTrace();
            }
      }
   }
}