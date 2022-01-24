public class Main {

  public static void main(String[] args){
      String filepath = null;
      String method = null;
      String enhance = null;
      
      try {
          filepath = args[0];
      }
      catch (Exception e) {}

      try {
          method = args[1];
      }
      catch (Exception e) {}

      try {
          enhance = args[2];
      }
      catch (Exception e) {}
      
      if (filepath == null) {
          String s = "";
        
          System.out.println("instructions");
          RLE rle = new RLE(s);
          rle.encode();
      }

      else if (filepath.equals("demo")) {
          System.out.print("Running demo ");
      }

      else {
          System.out.print("Compressing file " + filepath + " ");
      }

      if (method != null) {
          if (method.equals("lzss")) {
             System.out.print("using LZSS ");
          }
      
          else if (method.equals("rle")) {
              System.out.print("using RLE ");
          }
      
          else {
              System.out.println("what? ");
          }
        } 
        
        if (enhance != null) {
            System.out.println("enhanced with BWT");
        }
    }
}