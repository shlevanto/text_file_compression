## Description
This project concerns lossless compression methods for text data. I will be comparing two different compression methods on a variety of text files with different properties (ie. actual readable text, generated text content and compression benchmark data). I chose compression as a topic because I have not studied it previously and it seems like a straight-forward problem to try to solve.

## Languages used
The project will be done in Java. I am open for peer reviewing projects coded with both Java and Python. All documentation will be in English but I am happy to both give and receive feedback and peer reviews in Finnish, English and Swedish.

## Algorithms and data structures used
I will be implementing the Lempel–Ziv–Storer–Szymanski (LZSS) algorithm and Run Length Encoding (RLE) algorithm enhanced with a Burrows–Wheeler Transform (BWT) algorithm. I chose LZSS because it is only a slight optimization of the original Lempel-Ziv algorithm and I would like to get to know the classics in this field. As a comparision I chose RLE because it is not very effective as such and I want to see how much BWT can enhance its performance. For BTW I suppose a suffix array data structure is needed soI will try to implement it myself the project.

## Specification of the program
The program will work from the command line using flags / args. In addition to the flags, the user will input a path. The flags will tell the program which compression(s) to use for commpressing the file in the given path and report the compression rate and time used. The program will also decompress the file and check that the compression was flawless. The program will include a demo mode that goes through a predefined set of text files and generates a report of the compression performance. The predefined set of files can be configured by the user.

## Time and space complexity
I think the time complexity of each of the algorithms used is expected to be O(n) or perhaps O(n log n)? 