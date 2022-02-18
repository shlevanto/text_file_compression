# Weekly report 5

## My activities this week
- LZSS
- BWT in chunks can now handle bigger strings, file handling needs to be updated.
- Updating file operations.

## Progress
- LZ77 achieved. Encoding and decoding works. Now to add the SS element ie. check token is smaller than the part that is replaced.
- The chunked BWT can now be saved to file, but there are some issues.

## Lessons learned
- My file handling has some issues. Now the compression rate is highly negative... 

## Issues, unclarities and questions

## Plan for next steps
- The Pair Class gets too messy. I should probably make it an interface and have separate data structures for RLE and LZSS encoded text to hold the encoded result.

## Time used
12