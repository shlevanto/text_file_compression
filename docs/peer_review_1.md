Repository cloned: Feb 18th 13:20
Project: https://github.com/KirillosTY/Tiralabra

General remarks
- Initially there were some problems with gradle to build the project. Luckily I found a way to make it work. I would recommend to specify in documentation which gradle version was used for the build.
- I'm doing my project on compression too (not Huffman but Lempel-Ziv and another method) and notice that you mentioned in the documentation that the file handling with byte operations posed some challenges. I can relate :) I think you've done a very thorough job with this.
- The implied structure of the project seems clear. There are separate classes for encoding and file handling and they are divided into packages. However I find it a bit confusing that the encoders and decoders are under file handling. Also it seems to me that the class that does the Huffman encoding is called WordCounter. Maybe you could consider the naming of the classes.
- Use of interface for Node makes the structure more clear and easier to understand. Good job.
- The code is nicely written. Obviously checkstyle has to be appaeased a bit at some point, but the code is readable now and I can get an idea of how it works.
- The unit tests looks good.
- The program seems to work very effectively on the 500kb file and I gave it a 3MB file and it compressed nicely. Very good job! 

Specifics
- In Encoder.loadingTextFile the scanner is left open at the end of the operation --> reader.close() is needed. The same issue is in Decoder.writeTextToFile --> writeOut.close().
- In DecoderTest you have the method same(). I think this should be implemented as an equals() method for Node objects.
- DecoderTest and EncoderTest are under LogicTest even though they are in the FileHandler package.
- The program is currently restricted to ASCII, but I think you can use UTF-8 encoded characters. Bytesize is 8, but it is signed so you have -127 - 128 to work with. I used byte[] a = string.getBytes("UTF-8") to do this. Each UFT-8 character fits into one byte.

