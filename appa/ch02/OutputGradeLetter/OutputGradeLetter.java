public class OutputGradeLetter
{
   public static void main(String[] args)
   {
      char gradeLetter = 'u'; // unknown
      int testMark = 100;

      if (testMark >= 90)
      {
         gradeLetter = 'A';
         System.out.println("You aced the test.");
      }
      else
      if (testMark >= 80)
      {
         gradeLetter = 'B';
         System.out.println("You did very well on this test.");
      }
      else
      if (testMark >= 70)
      {
         gradeLetter = 'C';
         System.out.println("Not bad, but you need to study more for future tests.");
      }
      else
      if (testMark >= 60)
      {
         gradeLetter = 'D';
         System.out.println("Your test result suggests that you need a tutor.");
      }
      else
      {
         gradeLetter = 'F';
         System.out.println("Your test result is pathetic; you need summer school.");
      }
   }
}