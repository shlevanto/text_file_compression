# Weekly report 3

## My activities this week
Got started on BWT. Decided to do a slow / inefficient solution first and then try to optimize it by using a suffix array instead of rotation. The creation of the suffix array is O(n) time complex. Then it has to be sorted O(n log n). But this is more effective than the rotation because it loops throug the entire string as an array for each string so it is O(n^2 log n).

## Progress
Slow version of encoding and decoding done. BWT and RLE can also be used in combination.
Implemented suffix array for encoding.

## Lessons learned
Having to switch between String and char[] types can get confusing.

## Issues, unclarities and questions

## Plan for next steps
Optimize BWT, implement LZSS.
Teacher suggested using ByteStream for file handling, have to look into that. So the idea is to encode and then save the encoded as bytes?
Make BWT an option for RLE encoding and implement a method for this.

## Time used
7