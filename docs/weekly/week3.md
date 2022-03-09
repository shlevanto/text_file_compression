# Weekly report 3

## My activities this week
Focused BWT. Decided to do a slow / inefficient solution first and then try to optimize it by using a suffix array instead of rotation. The creation of the suffix array is O(n) time complex. Then it has to be sorted O(n log n). But this is more effective than the rotation because it loops throug the entire string as an array for each string so it is O(n^2 log n). 

## Progress
Slow version of encoding and decoding done. BWT and RLE can also be used in combination.
Implemented suffix array for encoding.
Implemented optimized BWT encoding using suffix array and decoding using LF mapping. The decoding had problems handling spaces, because space has lower numerical ASCII value than the beginning of string character $ that I use. I fixed it bny using char 0 as the denominator of the string starting.

## Lessons learned
- Having to switch between String and char[] types can get confusing.
- Characters are confusing...
- It seems the encoding works even if the starting denominator is repeated in the code.
- Memory issues, BWT can't handle large text files (even 1.1MB is too much). Probably reading it all into a String is too much...

## Issues, unclarities and questions


## Plan for next steps
- Implement LZSS.
- Teacher suggested using ByteStream for file handling, have to look into that. So the idea is to encode and then save the encoded as bytes? I decided to postpone this and focus on getting the compression algorithms to run first.
- Make BWT an option for RLE encoding and implement a method for this.

## Time used
13