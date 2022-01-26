# Weekly report 2

## My activities this week
Coding the RLE encoding. It seems pretty useless for natural language text files as expected. 

## Progress
RLE encoding and decoding done.
 
## Lessons learned
The instructions for RLE seem simple, just parse a string with count and character. AABBA becomes 2A2B1A. However I expect the text data to contain numbers in the text and have to take into account encoding for example 9113 which would end up as a string 192113. There is no way of knowing if this decodes to 9113 or 222222222222222222233333333333. So I decide to do two strings. One is the counts, separated by commas and the other one is the characters. The program will handle these as arrays. This is obviously very ineffective if there are no long runs and will end up increasing file size. Testing with a text sample showed that in English factual text there are very few runs at all. I hope that BWT will turn this around. 

I decided to use arrays for this first attempt. It caused some confusion that the size of the compressed file was always equal to or larger than the source file. Turned out I had left empty characters in the encoded array because the encoded array length matched the length of the original string to be decompressed.

Getting the read and written files to match proved somewhat tricky. To achieve maximum compression, I do not write the objects to a file. Instead I write the information as strings / characters. This proved to be tricky with line changes and whitespaces becaus some file reading methods seem to ignore them. After some frustration, I got the read and written files to match.

## Issues, unclarities and questions
Probably the RLE + BWT require the text to be represented as binary numbers for the characters in the text. I don't see how I could get enough runs from natural language input otherwise.

## Plan for next steps
Getting started on BWT.

## Time used
2 + 7 